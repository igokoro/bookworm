package dev.igokoro.bookworm.cards.storage

import androidx.datastore.core.Serializer
import java.io.InputStream
import java.io.OutputStream

object StoredCardsSerializer : Serializer<StoredCards> {

    override val defaultValue: StoredCards
        get() = StoredCards()

    override suspend fun readFrom(input: InputStream): StoredCards {
        return StoredCards.ADAPTER.decode(input)
    }

    override suspend fun writeTo(
        t: StoredCards,
        output: OutputStream,
    ) {
        StoredCards.ADAPTER.encode(
            output,
            t
        )
    }
}
