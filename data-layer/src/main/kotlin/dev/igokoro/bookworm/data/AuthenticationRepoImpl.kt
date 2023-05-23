package dev.igokoro.bookworm.data

import com.squareup.anvil.annotations.ContributesBinding
import dev.igokoro.bookworm.anvil.AppScope
import dev.igokoro.bookworm.network.AuthenticateResponse
import dev.igokoro.bookworm.network.LibraryApi
import javax.inject.Inject

@ContributesBinding(AppScope::class)
class AuthenticationRepoImpl @Inject constructor(
    private val libraryApi: LibraryApi,
) : AuthenticationRepo {

    override suspend fun authenticate(
        accountId: LibraryAccountId,
        password: String,
    ): AccountInfo {
        return libraryApi.authenticate(
            accountId = accountId.id,
            password = password,
            libraryNetwork = "cclspa"
        )
            .validate()
            .toModel()
    }
}

internal fun AuthenticateResponse.validate(): AuthenticateResponse {
    when {
        message == "Invalid Credentials." -> throw InvalidCredentials()
    }
    return this
}
