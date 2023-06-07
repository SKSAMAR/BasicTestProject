package sk.samar.testproject.data.di.module


import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import sk.samar.testproject.data.remote.ApiServices
import sk.samar.testproject.data.remote.db.AppDatabase
import sk.samar.testproject.data.remote.db.dao.PostDao
import sk.samar.testproject.data.repositories.RepositoryImpl
import sk.samar.testproject.domain.repositories.Repository
import sk.samar.testproject.util.Constants
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BasicModule {

    @Provides
    @Singleton
    fun interceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().also { it.level = HttpLoggingInterceptor.Level.BODY }

    @Provides
    @Singleton
    fun getClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(interceptor).build()


    @Provides
    @Singleton
    fun getApiServices(client: OkHttpClient): ApiServices = Retrofit
        .Builder()
        .baseUrl(Constants.BASE_URL)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .addConverterFactory(ScalarsConverterFactory.create())
        .client(client)
        .build()
        .create(ApiServices::class.java)

    @Provides
    @Singleton
    fun getRepository(apiServices: ApiServices, dao: PostDao): Repository = RepositoryImpl(apiServices, dao)


    @Provides
    @Singleton
    fun getAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "app_db")
            .build()


    @Provides
    @Singleton
    fun getDao(database: AppDatabase): PostDao = database.postDao()

}