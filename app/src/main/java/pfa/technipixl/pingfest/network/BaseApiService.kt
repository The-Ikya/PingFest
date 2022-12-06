package pfa.technipixl.pingfest.network


import pfa.technipixl.pingfest.model.FestResults
import pfa.technipixl.pingfest.model.Fests
import pfa.technipixl.pingfest.model.Participator
import retrofit2.Response
import retrofit2.http.GET

interface BaseApiService {
  @GET("fest")
  suspend fun getFest():Response<Fests>

    @GET("qqch/unePersonne")
    suspend fun getParticipator():Response<Participator>
}