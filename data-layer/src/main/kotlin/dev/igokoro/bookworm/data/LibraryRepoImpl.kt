package dev.igokoro.bookworm.data

import dev.igokoro.bookworm.network.LibraryApi
import javax.inject.Inject

class LibraryRepoImpl @Inject constructor(
    private val libraryApi: LibraryApi,
) : LibraryRepo {

    override suspend fun checkouts(accountId: LibraryAccountId): List<CheckedOutItem> {
        return libraryApi.checkouts(
            libraryNetwork = "cclspa",
            accountId = accountId.id
        ).toModel()
    }

    override suspend fun holds(accountId: LibraryAccountId) {
        TODO("Not yet implemented")
    }
}
