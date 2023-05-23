package dev.igokoro.bookworm.network

interface LibraryApi {

    suspend fun authenticate(
        libraryNetwork: String,
        accountId: String,
        password: String,
    ): AuthenticateResponse

    suspend fun checkouts(
        libraryNetwork: String,
        accountId: String,
    ): AccountResponse.CheckoutsAccountResponse

    suspend fun bills(
        libraryNetwork: String,
        accountId: String,
    ): AccountResponse.BillsAccountResponse
}
