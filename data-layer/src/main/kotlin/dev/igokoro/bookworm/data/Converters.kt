package dev.igokoro.bookworm.data

import dev.igokoro.bookworm.cards.storage.Owner
import dev.igokoro.bookworm.network.AccountResponse
import dev.igokoro.bookworm.network.AuthenticateResponse
import dev.igokoro.bookworm.network.Checkout

internal fun Owner.toModel(): LibraryCardOwner {
    return LibraryCardOwner(
        firstName = firstName,
        lastName = lastName,
    )
}

internal fun LibraryCardOwner.toDto(): Owner {
    return Owner(
        firstName = firstName,
        lastName = lastName,
    )
}

internal fun AccountResponse.CheckoutsAccountResponse.toModel(): List<CheckedOutItem> {
    return patron.checkouts
        .map {
            it.toModel()
        }
}

internal fun Checkout.toModel(): CheckedOutItem {
    return CheckedOutItem(
        id = recordId,
        title = title,
        status = status,
        dueDate = dueDate,
        dueTimestamp = dueTimestamp,
    )
}

internal fun AuthenticateResponse.toModel(): AccountInfo {
    val name = patron.name.split(", ")
    return AccountInfo(
        id = LibraryAccountId(patron.recordId),
        firstName = name[1],
        lastName = name[0],
        emailAddress = patron.emailAddress,
        address = patron.address1,
        telephone = patron.telephone1,
        expirationDate = patron.expirationDate,
        isBlocked = patron.isBlocked,
        isExpired = patron.isExpired,
    )
}
