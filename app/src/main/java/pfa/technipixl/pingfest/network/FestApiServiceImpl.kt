package pfa.technipixl.pingfest.network

import okhttp3.OkHttpClient
import pfa.technipixl.pingfest.model.FestResponse
import pfa.technipixl.pingfest.model.FestResult
import pfa.technipixl.pingfest.model.Participator
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

public final class FestApiServiceImpl : BaseApiService {
    private fun getRetrofit(): Retrofit {

        val okBuilder = OkHttpClient().newBuilder().apply {
            connectTimeout(120, TimeUnit.SECONDS)
            callTimeout(120, TimeUnit.SECONDS)
            readTimeout (120, TimeUnit.SECONDS)
            writeTimeout(120, TimeUnit.SECONDS)
        }
        return Retrofit.Builder()
            .baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okBuilder.build())
            .build()
    }

    override suspend fun getFest(): Response<FestResponse> {
        return getRetrofit().create(BaseApiService::class.java).getFest()
    }

    override suspend fun getParticipator(): Response<Participator> {
        return getRetrofit().create(BaseApiService::class.java).getParticipator()
    }

}