package com.example.waf.common.modules

import com.example.waf.fakeMatch


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
    val age: Int,
    val url: String?,
    val favoritePosition: Positions?,
    val favoriteTeam: String?,
    val favoritePlayer: String?,
    val mostPlayedPosition: Positions?,
    val gamesPlayed: Int,
    val goals: Int,
    val assists: Int,
    val rating: Int,
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

data class Match(
    val id: String,
    val pitch: Pitch,
    val result: String,
    val date: String,
    val time: String,
    val team1: Team,
    val team2: Team,
    val closed: Boolean = false
)

data class Team(
    val id: String,
    val name: String,
    val players: List<Player>,
    val overallRating: Int,

)