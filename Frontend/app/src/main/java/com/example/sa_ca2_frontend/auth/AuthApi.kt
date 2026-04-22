package com.example.sa_ca2_frontend.auth

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


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

object ApiClient {

    private const val BASE_URL = "http://10.0.2.2:5007/"

    val authApi: AuthApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApi::class.java)
    }
}