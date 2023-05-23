package dev.igokoro.bookworm.data

interface LibraryEventsRepo {

    suspend fun events(): List<LibraryEvent>
}
