// Copyright (C) 2022 Slack Technologies, LLC
// SPDX-License-Identifier: Apache-2.0
package dev.igokoro.bookworm

import android.app.Application
import dev.igokoro.bookworm.di.AppComponent

class BookwormApp : Application() {

  private val appComponent by lazy { AppComponent.create(this) }

  fun appComponent() = appComponent
}
