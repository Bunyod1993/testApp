package com.example.patient.dependencyInjection

import android.content.Context
import android.content.SharedPreferences
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.patient.BuildConfig
import com.example.patient.networking.interceptors.AuthorizationInterceptor
import com.example.patient.networking.interceptors.LiveNetworkMonitor
import com.example.patient.networking.UrlProvider
import com.example.patient.repositories.auth.AuthApi
import com.example.patient.repositories.register.RegisterApi
import com.example.patient.utils.Constants.PREF_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @AppScope
    @Provides
    fun retrofit(urlProvider: UrlProvider,client: OkHttpClient):Retrofit{
        return Retrofit.Builder()
            .client(client)
            .baseUrl(urlProvider.getBaseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @AppScope
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        }
    }
    @Provides
    @Singleton
    fun provideChuckerInterceptor(@ApplicationContext context: Context): ChuckerInterceptor {
        return ChuckerInterceptor
            .Builder(context)
            .collector(ChuckerCollector(context))
            .maxContentLength(250000L).redactHeaders(emptySet())
            .alwaysReadResponseBody(false)
            .build()
    }
    @AppScope
    @Provides
    fun provideAuthInterceptorOkHttpClient(authInterceptor: AuthorizationInterceptor,
                                           chuckerInterceptor: ChuckerInterceptor,
                                           loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .callTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(authInterceptor)
            .addInterceptor(chuckerInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }


    @AppScope
    @Provides
    fun getAuthorizationInterceptor(prefs:SharedPreferences,liveNetworkMonitor: LiveNetworkMonitor): AuthorizationInterceptor {
        return AuthorizationInterceptor(prefs,liveNetworkMonitor)
    }

    @AppScope
    @Provides
    fun urlProvider() = UrlProvider()

    @AppScope
    @Provides
    fun getNetworkMonitor(@ApplicationContext context: Context) = LiveNetworkMonitor(context)



    @AppScope
    @Provides
    fun authApi(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)


    @AppScope
    @Provides
    fun registerApi(retrofit: Retrofit): RegisterApi = retrofit.create(RegisterApi::class.java)

    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(
            PREF_NAME, Context.MODE_PRIVATE
        )
}