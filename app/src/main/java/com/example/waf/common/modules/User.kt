package com.example.waf.common.modules


data class User(
    val id: String,
    val username: String,
    val name: String?,
    val password: String,
    val location: String,
    val age: Double,
    val email: String
)

data class Player(
    val id: String,
    val name: String,
    val age: Double,
    val url: String?,
    val favoritePosition: Positions?,
    val favoriteTeam: String?,
    val favoritePlayer: String?,
    val mostPlayedPosition: Positions?,
    val goals: Int,
    val assists: Int,
    val rating: Double,
    val skill1: String?,
    val skill2: String?,
    val skill3: String?,
    val skill4: String?,
    val skill5: String?
)

data class Pitch(
    val id: String,
    val name: String,
    val location: String,
    val url: String?,
    val type: PitchTypes,
    val pitchLength: Double?,
    val pitchWidth: Double?,
    val pitchDimensions: String
)