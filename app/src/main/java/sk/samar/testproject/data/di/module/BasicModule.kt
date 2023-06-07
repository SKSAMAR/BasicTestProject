package sk.samar.testproject.data.di.module


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import sk.samar.testproject.data.remote.ApiServices
import sk.samar.testproject.data.repositories.RepositoryImpl
import sk.samar.testproject.domain.repositories.Repository
import sk.samar.testproject.util.Constants
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BasicModule {

    @Provides
    @Singleton
    fun interceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().also { it.level = HttpLoggingInterceptor.Level.BODY }

    @Provides
    @Singleton
    fun getClient(interceptor: HttpLoggingInterceptor): OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()


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
    fun getRepository(apiServices: ApiServices): Repository = RepositoryImpl(apiServices)

}