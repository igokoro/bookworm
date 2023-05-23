package dev.igokoro.bookworm.data

@JvmInline
value class LibraryAccountId(
    val id: String
)

data class LibraryCard(
    val id: LibraryAccountId,
    val owner: LibraryCardOwner,
    val expiration : String,
)

data class LibraryCardOwner(
    val firstName: String,
    val lastName: String,
)

data class LibraryEvent(
    val id: String,
    val title: String,
    val date: String,
    val time : String,
    val location: String,
    val library: String,
    val summary: String,
    val link: String,
    val type: String,
    val presenter: String,
    val start: String,
    val end: String,
)


//            {
//                "recordId": "23014067",
//                "title": "Just breathe",
//                "status": "DUE: May 22 2023 -- RENEWED 0 TIMES",
//                "dueDate": "DUE: May 22 2023 -- RENEWED 0 TIMES",
//                "overdue": 0,
//                "dueTimestamp": 1684771066
//            }
// rename to loaded item?
data class CheckedOutItem(
    val id: String,
    val title: String,
    val status: String,
    val dueDate: String,
    val dueTimestamp: Long,
)

data class AccountInfo(
    val id: LibraryAccountId,
    val firstName: String,
    val lastName: String,
    val emailAddress: String,
    val address: String,
    val telephone: String,
    val expirationDate: String,
    val isBlocked: Boolean,
    val isExpired: Boolean,
)
