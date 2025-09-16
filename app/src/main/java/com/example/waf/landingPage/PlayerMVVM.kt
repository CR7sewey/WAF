package com.example.waf.landingPage

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.waf.common.modules.Player
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PlayerMVVM : ViewModel() {


    private val _player = MutableStateFlow<Player?>(null)
    var player: StateFlow<Player?> = _player

    init {
        Log.d("PlayerMVVM", "I am here in Player MVVM")
    }

}