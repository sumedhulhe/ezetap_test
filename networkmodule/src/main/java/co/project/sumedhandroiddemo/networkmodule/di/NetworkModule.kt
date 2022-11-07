package co.project.sumedhandroiddemo.networkmodule.di


import androidx.annotation.NonNull
import co.project.sumedhandroiddemo.networkmodule.network.APIinterface
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


@dagger.Module
@dagger.hilt.InstallIn(dagger.hilt.components.SingletonComponent::class)
object NetworkModule {
    @dagger.Provides
    @javax.inject.Singleton
    fun provideHttpClient(): okhttp3.OkHttpClient {
        val httpClient = okhttp3.OkHttpClient.Builder()
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val request: okhttp3.Request.Builder = original.newBuilder()
            request.header("Content-Type", "application/json")

            chain.proceed(request.build())
        }

                .connectTimeout(100, TimeUnit.SECONDS)
                .writeTimeout(100, TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS)

        return httpClient.build()
    }

    @dagger.Provides
    @javax.inject.Singleton
    fun provideRetrofit(@NonNull okHttpClient: okhttp3.OkHttpClient): retrofit2.Retrofit {
        return retrofit2.Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://demo.ezetap.com/")
                .addCallAdapterFactory(retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory.create())
                .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                .build()
    }

    @dagger.Provides
    @javax.inject.Singleton
    fun provideAPIinterface(@NonNull retrofit: retrofit2.Retrofit): APIinterface =
            retrofit.create(APIinterface::class.java)



}