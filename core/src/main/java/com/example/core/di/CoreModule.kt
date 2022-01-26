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
import com.example.core.utils.SSLCertificateConfigurator
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.net.ssl.X509TrustManager

var networkModule = module{
    single {
        val trustManagerFactory = SSLCertificateConfigurator.getTrustManager(context = androidContext())
        val trustManagers = trustManagerFactory.trustManagers
        if(trustManagers.size != 1 || trustManagers[0] !is X509TrustManager) {
            throw IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers))
        }
        val trustManager = trustManagers[0] as X509TrustManager
        OkHttpClient.Builder()
            .sslSocketFactory(SSLCertificateConfigurator.getSSLConfiguration(androidContext()).socketFactory, trustManager)
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
        val passphrase: ByteArray = SQLiteDatabase.getBytes("moviecatalogue".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            CatalogueDatabase::class.java, "MovieCatalogue.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val repositoryModule = module {
    single<CatalogueRepositoryImpl> { CatalogueRepository(get(), get()) }
    single<RemoteDataSourceImpl> { RemoteDataSource(get()) }
    single<LocalDataSourceImpl>{ LocalDataSource(get())  }
}