package com.example.waf.landingPage

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.waf.common.modules.Match
import com.example.waf.common.modules.Player
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MatchesMVVM : ViewModel() {

    private val _matchesList = MutableStateFlow<List<Match>>(emptyList())
    var matchesList: StateFlow<List<Match>> = _matchesList

    init {
        Log.d("MatchesMVVM", "I am here in Match MVVM")
    }



}