package com.example.sa_ca2_frontend.upcomingFixtures

import com.example.sa_ca2_frontend.network.ApiClient
import retrofit2.Response
import retrofit2.http.GET

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

interface FixtureApi {
    @GET("api/Fixture")
    suspend fun getFixtures(): Response<List<FixtureResponse>>
}

object FixtureApiService {

    val api: FixtureApi by lazy {
        ApiClient.retrofit.create(FixtureApi::class.java)
    }
}