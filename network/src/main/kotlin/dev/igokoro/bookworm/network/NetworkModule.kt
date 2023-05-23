package dev.igokoro.bookworm.network

import android.util.Log
import com.squareup.anvil.annotations.ContributesTo
import dev.igokoro.bookworm.anvil.AppScope
import dev.igokoro.bookworm.anvil.SingleIn
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

@ContributesTo(AppScope::class)
@Module
class NetworkModule {

    @Provides
    @SingleIn(AppScope::class)
    fun providesOkHttp(): OkHttpClient {
        Log.d("GGGGGG", "providesOkHttp")
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    @Provides
    @SingleIn(AppScope::class)
    fun providesMoshi(): Moshi {
        return Moshi.Builder()
            .build()
    }

    @Provides
    @SingleIn(AppScope::class)
    fun providesRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit {
        Log.d("GGGGGG", "providesRetrofit")
        return  Retrofit.Builder()
            .baseUrl("https://webservices.capiramobile.com")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @SingleIn(AppScope::class)
    internal fun providesNetworkLibraryApi(
        retrofit: Retrofit
    ): NetworkLibraryApi {
        return retrofit.create()
    }

    @Provides
    @SingleIn(AppScope::class)
    internal fun providesLibraryApi(
        api: NetworkLibraryApi,
    ): LibraryApi {
        return LibraryApiImpl(
            networkLibraryApi = api,
        )
    }

}
