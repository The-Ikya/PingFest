package pfa.technipixl.pingfest.network

import pfa.technipixl.pingfest.model.FestResult
import pfa.technipixl.pingfest.model.Participator
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface BaseApiService {
  @GET("Fest")
  suspend fun getFest():Response<FestResult>

    @GET("qqch/unePersonne")
    suspend fun getParticipator():Response<Participator>
}