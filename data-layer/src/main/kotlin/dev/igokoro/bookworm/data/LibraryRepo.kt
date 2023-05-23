package dev.igokoro.bookworm.data

interface LibraryRepo {

    suspend fun checkouts(accountId: LibraryAccountId): List<CheckedOutItem>

    suspend fun holds(accountId: LibraryAccountId)
}
