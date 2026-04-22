package com.example.sa_ca2_frontend.auth

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import com.example.sa_ca2_frontend.network.ApiClient


data class LoginResponse(
    val message: String,
    val user: User
)
data class User(
    val id: Int,
    val email: String
)
data class SignupResponse(
    val message: String
)


interface AuthApi {

    @POST("api/Auth/signup")
    suspend fun signup(@Body request: AuthRequest): Response<SignupResponse>

    @POST("api/Auth/login")
    suspend fun login(@Body request: AuthRequest): Response<LoginResponse>
}
data class AuthRequest(
    val email: String,
    val password: String
)

object AuthApiService {

    val api: AuthApi by lazy {
        ApiClient.retrofit.create(AuthApi::class.java)
    }
}