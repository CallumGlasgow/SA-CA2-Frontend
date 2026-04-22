package com.example.sa_ca2_frontend.team

import com.example.sa_ca2_frontend.network.ApiClient
import com.example.sa_ca2_frontend.Model.TeamResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

data class CreateTeamRequest(
    val name: String,
)

object TeamApiService {

    val api: TeamApi by lazy {
        ApiClient.retrofit.create(TeamApi::class.java)
    }
}

interface TeamApi {
    @POST("api/Team")
    suspend fun createTeam(
        @Body request: CreateTeamRequest
    ): Response<TeamResponse>
}