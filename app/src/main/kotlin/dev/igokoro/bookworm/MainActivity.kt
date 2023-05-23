// Copyright (C) 2022 Slack Technologies, LLC
// SPDX-License-Identifier: Apache-2.0
package dev.igokoro.bookworm

import android.app.Activity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.CompositionLocalProvider
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.CircuitConfig
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.push
import com.slack.circuit.foundation.rememberCircuitNavigator
import com.slack.circuit.overlay.ContentWithOverlays
import com.slack.circuit.runtime.Screen
import com.squareup.anvil.annotations.ContributesMultibinding
import dev.igokoro.bookworm.anvil.ActivityKey
import dev.igokoro.bookworm.anvil.AppScope
import dev.igokoro.bookworm.cards.add.AddLibraryCardScreen
import dev.igokoro.bookworm.features.library.assets.LibraryAssetsScreen
import dev.igokoro.bookworm.theme.BookwormTheme
import dev.igokoro.bookworm.ui.LocalWindowWidthSizeClass
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import javax.inject.Inject

@ContributesMultibinding(
    AppScope::class,
    boundType = Activity::class
)
@ActivityKey(MainActivity::class)
class MainActivity @Inject constructor(
    private val circuitConfig: CircuitConfig,
) :
    AppCompatActivity() {

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var backStack: ImmutableList<Screen> = persistentListOf(AddLibraryCardScreen)
//        if (intent.data != null) {
//            val httpUrl = intent.data.toString()
//                .toHttpUrl()
//            val animalId = httpUrl.pathSegments[1].substringAfterLast("-")
//                .toLong()
//            val petDetailScreen = PetDetailScreen(
//                animalId,
//                null
//            )
//            backStack = persistentListOf(
//                HomeScreen,
//                petDetailScreen
//            )
//        }

        setContent {
            BookwormTheme {
                // TODO why isn't the windowBackground enough so we don't need to do this?
                Surface(color = MaterialTheme.colorScheme.background) {
                    val backstack = rememberSaveableBackStack { backStack.forEach { screen -> push(screen) } }
                    val navigator = rememberCircuitNavigator(backstack)
//                    val navigator =
//                        remember(circuitNavigator) {
//                            AndroidSupportingNavigator(
//                                circuitNavigator,
//                                this::goTo
//                            )
//                        }
                    val windowSizeClass = calculateWindowSizeClass(this)
                    CompositionLocalProvider(
                        LocalWindowWidthSizeClass provides windowSizeClass.widthSizeClass
                    ) {
                        CircuitCompositionLocals(circuitConfig) {
                            ContentWithOverlays {
                                NavigableCircuitContent(
                                    navigator,
                                    backstack
                                )
                            }
                        }
                    }
                }
            }
        }
    }

//    private fun goTo(screen: AndroidScreen) =
//        when (screen) {
//            is AndroidScreen.CustomTabsIntentScreen -> goTo(screen)
//            is AndroidScreen.IntentScreen -> TODO()
//        }

//    private fun goTo(screen: AndroidScreen.CustomTabsIntentScreen) {
//        val scheme = CustomTabColorSchemeParams.Builder()
//            .setToolbarColor(0x000000)
//            .build()
//        CustomTabsIntent.Builder()
//            .setColorSchemeParams(
//                COLOR_SCHEME_LIGHT,
//                scheme
//            )
//            .setColorSchemeParams(
//                COLOR_SCHEME_DARK,
//                scheme
//            )
//            .setShowTitle(true)
//            .build()
//            .launchUrl(
//                this,
//                Uri.parse(screen.url)
//            )
//    }
}
