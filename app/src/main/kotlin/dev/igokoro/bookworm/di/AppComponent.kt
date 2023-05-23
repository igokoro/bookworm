// Copyright (C) 2022 Slack Technologies, LLC
// SPDX-License-Identifier: Apache-2.0
package dev.igokoro.bookworm.di

import android.app.Activity
import android.content.Context
import dev.igokoro.bookworm.anvil.AppScope
import dev.igokoro.bookworm.anvil.ApplicationContext
import dev.igokoro.bookworm.anvil.BaseUiModule
import dev.igokoro.bookworm.anvil.SingleIn
import com.squareup.anvil.annotations.MergeComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Provider

@MergeComponent(
  scope = AppScope::class,
//  modules =
//    [
//      BaseUiModule::class,
//      CircuitModule::class,
////      DataModule::class,
//    ]
)
@SingleIn(AppScope::class)
interface AppComponent {
  val activityProviders: Map<Class<out Activity>, @JvmSuppressWildcards Provider<Activity>>

  @Component.Factory
  interface Factory {
    fun create(@ApplicationContext @BindsInstance context: Context): AppComponent
  }

  companion object {
    fun create(context: Context): AppComponent = DaggerAppComponent.factory().create(context)
  }
}
