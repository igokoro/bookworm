package dev.igokoro.bookworm.data

import com.squareup.anvil.annotations.ContributesTo
import dagger.Binds
import dagger.Module
import dev.igokoro.bookworm.anvil.AppScope
import dev.igokoro.bookworm.anvil.SingleIn
import javax.inject.Singleton

@ContributesTo(AppScope::class)
@Module
interface DataLayerModule {

    @Binds
    @SingleIn(AppScope::class)
    fun LibraryRepoImpl.bindsLibraryRepo(): LibraryRepo

}
