package com.example.core.di

import androidx.room.Room
import com.example.core.data.CatalogueRepository
import com.example.core.data.source.local.LocalDataSource
import com.example.core.data.source.local.LocalDataSourceImpl
import com.example.core.data.source.local.room.CatalogueDatabase
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.data.source.remote.RemoteDataSourceImpl
import com.example.core.data.source.remote.network.ApiService
import com.example.core.domain.repository.CatalogueRepositoryImpl
import com.example.core.utils.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

var networkModule = module{
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

var databaseModule = module {
    factory { get<CatalogueDatabase>().catalogueDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            CatalogueDatabase::class.java, "MovieCatalogue.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val repositoryModule = module {
    single<CatalogueRepositoryImpl> { CatalogueRepository(get(), get()) }
    single<RemoteDataSourceImpl> { RemoteDataSource(get()) }
    single<LocalDataSourceImpl>{ LocalDataSource(get())  }
}