package pfa.technipixl.pingfest.network


import pfa.technipixl.pingfest.model.Fests
import pfa.technipixl.pingfest.model.ParticipatorResult
import retrofit2.Response
import retrofit2.http.GET

interface BaseApiService {
  @GET("fest")
  suspend fun getFest():Response<Fests>
  @GET("participator")
  suspend fun getParticipator():Response<ParticipatorResult>

}