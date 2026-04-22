package com.example.sa_ca2_frontend.Model

data class TeamResponse(
    val id: Int,
    val name: String,
    val wins: Int,
    val draws: Int,
    val losses: Int,
    val points: Int,
    val createdAt: String
)