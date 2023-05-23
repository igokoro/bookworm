package dev.igokoro.bookworm.data

interface AuthenticationRepo {
    suspend fun authenticate(
        accountId: LibraryAccountId,
        password: String,
    ): AccountInfo
}
