package com.example.sa_ca2_frontend.upcomingFixtures

import com.example.sa_ca2_frontend.network.ApiClient
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

data class FixtureResponse(
    val id: Int,
    val matchDate: String,
    val homeTeam: TeamInfo,
    val awayTeam: TeamInfo,
    val pitch: PitchMini
)
data class TeamInfo(
    val id: Int,
    val name: String
)

data class PitchMini(
    val id: Int,
    val name: String
)

data class GenerateFixturesResponse(
    val count: Int,
    val fixtures: List<FixtureResponse>
)

interface FixtureApi {
    @GET("api/Fixture")
    suspend fun getFixtures(): Response<List<FixtureResponse>>

    @POST("api/Fixture/generate-multiple/{days}")
    suspend fun generateFixtures(@Path("days") days: Int): Response<GenerateFixturesResponse>
}

object FixtureApiService {

    val api: FixtureApi by lazy {
        ApiClient.retrofit.create(FixtureApi::class.java)
    }
}