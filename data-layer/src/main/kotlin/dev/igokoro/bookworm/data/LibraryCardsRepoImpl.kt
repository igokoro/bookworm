package dev.igokoro.bookworm.data

import com.squareup.anvil.annotations.ContributesBinding
import dev.igokoro.bookworm.anvil.AppScope
import dev.igokoro.bookworm.cards.storage.CardsStorage
import dev.igokoro.bookworm.cards.storage.LibraryCardProto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ContributesBinding(AppScope::class)
class LibraryCardsRepoImpl @Inject constructor(
    private val storage: CardsStorage,
) : LibraryCardsRepo {

    override fun cards(): Flow<List<LibraryCard>> {
        return storage.cards.map {
            it.cards.map { storedCard ->
                LibraryCard(
                    id = LibraryAccountId(storedCard.id),
                    owner = storedCard.owner!!.toModel(),
                    expiration = storedCard.expiration
                )
            }
        }
    }

    override suspend fun addCard(card: LibraryCard) {
        storage.addCard(
            LibraryCardProto(
                id = card.id.id,
                owner = card.owner.toDto(),
                expiration = card.expiration,
            )
        )
    }

    override suspend fun removeCard(card: LibraryCard) {
        storage.remove(card.id.id)
    }
}
