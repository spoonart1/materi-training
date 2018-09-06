package com.spoonart.training.network

import android.content.Context
import com.google.gson.GsonBuilder
import com.spoonart.training.BuildConfig
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

//example API: http://www.recipepuppy.com/about/api/

class ApiConfig {

    companion object {
        val BASE_URL = "http://www.recipepuppy.com/api/"

        fun getService(context: Context) : ApiRequest{
            return ApiConfig().create(context)
        }
    }

    private fun create(context: Context): ApiRequest {
        val loggingLevelIterceptor = HttpLoggingInterceptor()
        loggingLevelIterceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

        val okhttpBuilder = OkHttpClient().newBuilder()

        okhttpBuilder.connectTimeout(60, TimeUnit.SECONDS)
        okhttpBuilder.writeTimeout(60, TimeUnit.SECONDS)
        okhttpBuilder.readTimeout(60, TimeUnit.SECONDS)

        val cacheSize = 10 * 1024 * 1024 // 10 MiB
        val cache = Cache(context.cacheDir, cacheSize.toLong())

        okhttpBuilder.cache(cache)
        okhttpBuilder.addInterceptor(HeaderInterceptor())
                .addInterceptor(LoggingInterceptor())
                .addInterceptor(loggingLevelIterceptor)


        val gson = GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okhttpBuilder.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        return retrofit.create(ApiRequest::class.java)
    }
}

internal class LoggingInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val t1 = System.nanoTime()
        println(String.format("Sending request %s on %s%n%s",
                request.url(), chain.connection(), request.headers()))

        val response = chain.proceed(request)

        val t2 = System.nanoTime()
        println(String.format("Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6, response.headers()))

        return response
    }
}

internal class HeaderInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain?): Response {
        val original = chain!!.request()

        // Request customization: add request headers
        val requestBuilder = original.newBuilder()
                .addHeader("Content-Type", "application/json")

        val request = requestBuilder.build()
        return chain.proceed(request)
    }

}