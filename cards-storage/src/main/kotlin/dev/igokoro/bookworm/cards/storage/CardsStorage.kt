package dev.igokoro.bookworm.cards.storage

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CardsStorage @Inject constructor(
    private val context: Context,
) {

    val cards: Flow<StoredCards> = context.cardsDataStore.data

    suspend fun addCard(card: LibraryCardProto) {
        context.cardsDataStore.updateData { storedCards ->
            Log.d("GGGGGG", "adding a new card: $card")
            storedCards.copy(
                cards = storedCards.cards.toMutableList()
                    .apply {
                        add(card)
                    }
            )
        }
    }

    suspend fun remove(cardId: String) {
        context.cardsDataStore.updateData { storedCards ->
            storedCards.copy(
                cards = storedCards.cards.filterNot { it.id == cardId }
            )
        }
    }
}

internal val Context.cardsDataStore: DataStore<StoredCards> by dataStore(
    fileName = "cards.pb",
    serializer = StoredCardsSerializer
)
