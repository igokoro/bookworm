// Copyright (C) 2022 Slack Technologies, LLC
// SPDX-License-Identifier: Apache-2.0
package dev.igokoro.bookworm.anvil

import android.content.Context
import javax.inject.Qualifier

/** Qualifier to denote a [Context] that is specifically an Application context. */
@Qualifier annotation class ApplicationContext
