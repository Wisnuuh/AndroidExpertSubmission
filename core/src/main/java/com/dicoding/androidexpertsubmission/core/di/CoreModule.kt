package com.dicoding.androidexpertsubmission.core.di

import androidx.room.Room
import com.dicoding.androidexpertsubmission.core.data.GamesRepository
import com.dicoding.androidexpertsubmission.core.data.source.local.LocalDataSource
import com.dicoding.androidexpertsubmission.core.data.source.local.room.GamesDatabase
import com.dicoding.androidexpertsubmission.core.data.source.remote.RemoteDataSource
import com.dicoding.androidexpertsubmission.core.data.source.remote.network.ApiService
import com.dicoding.androidexpertsubmission.core.domain.repository.IGamesRepository
import com.dicoding.androidexpertsubmission.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val moduleNetwork = module {
    single {
        val hostname = "api.rawg.io"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/b0kr6GUvjgiM8He9NBD+PpV3XaC8gCh9D+sTVN/HAbU=")
            .add(hostname, "sha256/kIdp6NNEd8wsugYyyIYFsi1ylMCED3hZbSR8ZFsa/A4=")
            .add(hostname, "sha256/mEflZT5enoR1FuXLgYYGqnVEoZvmf9c2bVBpiOjYQ0c=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.rawg.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val moduleDatabase = module {
    factory { get<GamesDatabase>().gamesDao() }
    single {
        val passPhrase: ByteArray = SQLiteDatabase.getBytes("games".toCharArray())
        val factory = SupportFactory(passPhrase)
        Room.databaseBuilder(
            androidContext(),
            GamesDatabase::class.java, "games.db"
        ).fallbackToDestructiveMigration().openHelperFactory(factory).build()
    }
}

val moduleRepository = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IGamesRepository> { GamesRepository(get(), get(), get()) }
}