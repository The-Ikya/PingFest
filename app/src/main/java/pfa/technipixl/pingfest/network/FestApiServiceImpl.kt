package pfa.technipixl.pingfest.network

import okhttp3.OkHttpClient
import pfa.technipixl.pingfest.model.Fests
import pfa.technipixl.pingfest.model.ParticipatorResult

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

public final class FestApiServiceImpl : BaseApiService {

    companion object {
        private const val BASEURL = "http://10.0.2.2:3001/"
    }

    private fun getRetrofit(): Retrofit {

        val okBuilder = OkHttpClient().newBuilder().apply {
            connectTimeout(120, TimeUnit.SECONDS)
            callTimeout(120, TimeUnit.SECONDS)
            readTimeout (120, TimeUnit.SECONDS)
            writeTimeout(120, TimeUnit.SECONDS)
        }
        return Retrofit.Builder()
            .baseUrl(Companion.BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okBuilder.build())
            .build()
    }

    override suspend fun getFest(): Response<Fests> {
        return getRetrofit().create(BaseApiService::class.java).getFest()
    }

    override suspend fun getParticipator(): Response<ParticipatorResult> {
        return getRetrofit().create(BaseApiService::class.java).getParticipator()
    }



}