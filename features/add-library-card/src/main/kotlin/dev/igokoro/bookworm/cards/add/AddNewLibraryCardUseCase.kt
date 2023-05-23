package dev.igokoro.bookworm.cards.add

import dev.igokoro.bookworm.data.AuthenticationRepo
import dev.igokoro.bookworm.data.LibraryAccountId
import dev.igokoro.bookworm.data.LibraryCard
import dev.igokoro.bookworm.data.LibraryCardOwner
import dev.igokoro.bookworm.data.LibraryCardsRepo
import javax.inject.Inject

class AddNewLibraryCardUseCase @Inject constructor(
    private val cardsRepo: LibraryCardsRepo,
    private val authenticationRepo: AuthenticationRepo,
) {

    suspend fun execute(
        accountId: LibraryAccountId,
        password: String,
    ) {
        val accountInfo = authenticationRepo.authenticate(
            accountId = accountId,
            password = password
        )
        cardsRepo.addCard(
            LibraryCard(
                id = accountId,
                owner = LibraryCardOwner(
                    firstName = accountInfo.firstName,
                    lastName = accountInfo.lastName,
                ),
                expiration = accountInfo.expirationDate,
            )
        )
    }
}
