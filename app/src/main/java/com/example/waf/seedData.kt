package com.example.waf

import com.example.waf.common.modules.Match
import com.example.waf.common.modules.Pitch
import com.example.waf.common.modules.PitchTypes
import com.example.waf.common.modules.Player
import com.example.waf.common.modules.Positions
import com.example.waf.common.modules.Team

val fakeProfile: Player = Player(
    id = "1",
    name = "Mike",
    age = 26,
    favoritePosition = Positions.AttackingMidfielder,
    mostPlayedPosition = Positions.AttackingMidfielder,
    goals = 10,
    assists = 12,
    rating = 87,
    skill1 = "Dribbling",
    url = null,
    favoriteTeam = null,
    favoritePlayer= null,
    skill2= null,
    skill3= null,
    skill4= null,
    skill5= null,
    gamesPlayed = 20
)

val fakePitch = Pitch(
    id= "1",
    name = "Vigor",
    location = "Coimbra",
    url = null,
    type = PitchTypes.Grass,
    pitchLength = null,
    pitchWidth = null,
    pitchDimensions = "5x5"
)



val fakeTeam = Team(
    id = "1",
    name = "Team 1",
    players = listOf(fakeProfile,
        fakeProfile,
        fakeProfile,
        fakeProfile,
        fakeProfile,
        fakeProfile,
        fakeProfile,
        fakeProfile,
        fakeProfile,
        fakeProfile,
        fakeProfile,
        fakeProfile,
        fakeProfile,
        fakeProfile,
        fakeProfile,
        fakeProfile,
        fakeProfile,
        fakeProfile,
        fakeProfile,
        fakeProfile,
        fakeProfile,
        fakeProfile,
        fakeProfile,
        fakeProfile,
        fakeProfile,
        fakeProfile,
        fakeProfile,),
    overallRating = 87
)

val fakeTeam2 = Team(
    id = "2",
    name = "Team 2",
    players = listOf(fakeProfile),
    overallRating = 85
)


val fakeMatch = Match(
    id = "1",
    pitch = fakePitch,
    result = "0 : 0",
    date = "22/07/2025",
    time = "18:30h",
    team1 = fakeTeam,
    team2 = fakeTeam2
)
