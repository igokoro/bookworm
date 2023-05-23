package dev.igokoro.bookworm.data

import kotlinx.coroutines.flow.Flow

interface LibraryCardsRepo {

    fun cards(): Flow<List<LibraryCard>>

    suspend fun addCard(card: LibraryCard)

    suspend fun removeCard(card: LibraryCard)
}
