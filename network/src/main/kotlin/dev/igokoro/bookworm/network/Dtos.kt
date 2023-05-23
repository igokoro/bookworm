package dev.igokoro.bookworm.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

enum class OperationType {
    checkouts,
    holds,
    bills
}

sealed class AccountResponse {
    @JsonClass(generateAdapter = true)
    data class CheckoutsAccountResponse(
        @Json(name = "patron")
        val patron: CheckoutPatronData,
    )
    @JsonClass(generateAdapter = true)
    data class BillsAccountResponse(
        @Json(name = "patron")
        val patron: BillsPatronData,
    )
}

@JsonClass(generateAdapter = true)
data class CheckoutPatronData(
    @Json(name = "checkouts")
    val checkouts: List<Checkout>,
)

@JsonClass(generateAdapter = true)
data class BillsPatronData(
    @Json(name = "fines")
    val fines: Fines,
    @Json(name = "bills")
    val bills: List<Bill>,
)

@JsonClass(generateAdapter = true)
data class Checkout(
    @Json(name = "recordId")
    val recordId: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "status")
    val status: String,
    @Json(name = "dueDate")
    val dueDate: String,
    @Json(name = "overdue")
    val overdue: Int,
    @Json(name = "dueTimestamp")
    val dueTimestamp: Long,
)

@JsonClass(generateAdapter = true)
data class Fines(
    @Json(name = "minimum")
    val minimum: Int,
    @Json(name = "blocked")
    val blocked: Int, // boolean?
    @Json(name = "amount")
    val amount: Double,
)

@JsonClass(generateAdapter = true)
data class Bill(
    @Json(name = "description")
    val description: String,
    @Json(name = "amount")
    val amount: String,
)

@JsonClass(generateAdapter = true)
data class AuthenticateResponse(
    @Json(name = "message")
    val message: String,
    @Json(name = "patron")
     val patron: AuthenticatePatron,
)

@JsonClass(generateAdapter = true)
data class AuthenticatePatron(
    @Json(name = "address1")
    val address1: String,
    @Json(name = "barcode")
    val barcode: String,
    @Json(name = "recordId")
    val recordId: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "emailAddress")
    val emailAddress: String,
    @Json(name = "expirationDate")
    val expirationDate: String,
    @Json(name = "homeLibrary")
    val homeLibrary: String,
    @Json(name = "createdDate")
    val createdDate: String,
    @Json(name = "updatedDate")
    val updatedDate: String,
    @Json(name = "telephone1")
    val telephone1: String,
    @Json(name = "pickupLibrary")
    val pickupLibrary: String,
    @Json(name = "isBlocked")
    val isBlocked: Boolean,
    @Json(name = "isExpired")
    val isExpired: Boolean,
    @Json(name = "isLinked")
    val isLinked: Boolean,
)
