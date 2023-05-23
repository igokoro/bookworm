// Copyright (C) 2022 Slack Technologies, LLC
// SPDX-License-Identifier: Apache-2.0
package dev.igokoro.bookworm.di

import android.app.Activity
import android.app.Application
import android.content.Intent
import androidx.annotation.Keep
import dev.igokoro.bookworm.BookwormApp
import javax.inject.Provider

@Keep
class BookwormAppComponentFactory : androidx.core.app.AppComponentFactory() {

  private inline fun <reified T> getInstance(
    cl: ClassLoader,
    className: String,
    providers: Map<Class<out T>, @JvmSuppressWildcards Provider<T>>,
  ): T? {
    val clazz = Class.forName(className, false, cl).asSubclass(T::class.java)
    val modelProvider = providers[clazz] ?: return null
    return modelProvider.get() as T
  }

  override fun instantiateActivityCompat(
    cl: ClassLoader,
    className: String,
    intent: Intent?
  ): Activity {
    return getInstance(cl, className, activityProviders)
      ?: super.instantiateActivityCompat(cl, className, intent)
  }

  override fun instantiateApplicationCompat(cl: ClassLoader, className: String): Application {
    val app = super.instantiateApplicationCompat(cl, className)
    activityProviders = (app as BookwormApp).appComponent().activityProviders
    return app
  }

  // AppComponentFactory can be created multiple times
  companion object {
    private lateinit var activityProviders: Map<Class<out Activity>, Provider<Activity>>
  }
}
