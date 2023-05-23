// Copyright (C) 2022 Slack Technologies, LLC
// SPDX-License-Identifier: Apache-2.0
package dev.igokoro.bookworm.anvil

import android.app.Activity
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.multibindings.Multibinds
import dev.igokoro.bookworm.anvil.AppScope

@ContributesTo(AppScope::class)
@Module
interface BaseUiModule {
  @Multibinds fun provideActivityProviders(): Map<Class<out Activity>, Activity>
}
