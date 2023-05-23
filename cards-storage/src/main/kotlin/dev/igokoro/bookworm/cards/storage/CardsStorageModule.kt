package dev.igokoro.bookworm.cards.storage

import android.content.Context
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides
import dev.igokoro.bookworm.anvil.AppScope
import dev.igokoro.bookworm.anvil.ApplicationContext
import dev.igokoro.bookworm.anvil.SingleIn

@ContributesTo(AppScope::class)
@Module
class CardsStorageModule {

    @Provides
    @SingleIn(AppScope::class)
    fun providesCardsStorage(
        @ApplicationContext context: Context,
    ): CardsStorage {
        return CardsStorage(
            context = context
        )
    }
}
