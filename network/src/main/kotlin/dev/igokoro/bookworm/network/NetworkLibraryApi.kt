package dev.igokoro.bookworm.network

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface NetworkLibraryApi {

    @FormUrlEncoded
    @POST("v1/authentication")
    suspend fun authenticate(
        @Field("code") libraryNetwork: String,
        @Field("barcode") accountId: String,
        @Field("password") password: String,
    ): AuthenticateResponse

    @FormUrlEncoded
    @POST("v1/account")
    suspend fun checkouts(
        @Field("code") libraryNetwork: String,
        @Field("barcode") accountId: String,
        @Field("type") operationType: OperationType,
    ): AccountResponse.CheckoutsAccountResponse

    @FormUrlEncoded
    @POST("v1/account")
    suspend fun bills(
        @Field("code") libraryNetwork: String,
        @Field("barcode") accountId: String,
        @Field("type") operationType: OperationType,
    ): AccountResponse.BillsAccountResponse
}
