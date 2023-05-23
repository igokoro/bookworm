package dev.igokoro.bookworm.network

import javax.inject.Inject

class LibraryApiImpl @Inject constructor(
    private val networkLibraryApi: NetworkLibraryApi,
) : LibraryApi {

    override suspend fun authenticate(
        libraryNetwork: String,
        accountId: String,
        password: String,
    ): AuthenticateResponse {
        return networkLibraryApi.authenticate(
            libraryNetwork,
            accountId,
            password
        )
    }

    override suspend fun checkouts(
        libraryNetwork: String,
        accountId: String,
    ): AccountResponse.CheckoutsAccountResponse {
        return networkLibraryApi.checkouts(
            libraryNetwork,
            accountId,
            OperationType.checkouts
        )
    }

    override suspend fun bills(
        libraryNetwork: String,
        accountId: String,
    ): AccountResponse.BillsAccountResponse {
        return networkLibraryApi.bills(
            libraryNetwork,
            accountId,
            OperationType.bills
        )
    }
}
