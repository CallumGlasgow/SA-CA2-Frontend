package com.example.sa_ca2_frontend.leagueTable

import com.example.sa_ca2_frontend.network.ApiClient
import com.example.sa_ca2_frontend.Model.TeamResponse
import retrofit2.Response
import retrofit2.http.GET

interface LeagueTableApi {
    @GET("api/Team")
    suspend fun getTeams(): Response<List<TeamResponse>>
}

object LeaderboardApiService {
    val api: LeagueTableApi by lazy {
        ApiClient.retrofit.create(LeagueTableApi::class.java)
    }
}