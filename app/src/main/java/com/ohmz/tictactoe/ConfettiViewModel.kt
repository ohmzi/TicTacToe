package com.ohmz.tictactoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ohmz.tictactoe.core.Presets
import com.ohmz.tictactoe.core.Party
import com.ohmz.tictactoe.core.models.Shape

class ConfettiViewModel : ViewModel() {
    private val _state = MutableLiveData<State>(State.Idle)
    val state: LiveData<State> = _state

    fun festive(drawable: Shape.DrawableShape) {
        /**
         * See [Presets] for this configuration
         */
        _state.value = State.Started(Presets.festive(drawable))
    }

    fun explode() {
        /**
         * See [Presets] for this configuration
         */
        _state.value = State.Started(Presets.explode())
    }

    fun parade() {
        /**
         * See [Presets] for this configuration
         */
        _state.value = State.Started(Presets.parade())
    }

    fun rain() {
        /**
         * See [Presets] for this configuration
         */
        _state.value = State.Started(Presets.rain())
    }

    fun ended() {
        _state.value = State.Idle
    }

    sealed class State {
        class Started(val party: List<Party>) : State()

        object Idle : State()
    }
}
