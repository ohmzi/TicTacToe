package com.ohmz.tictactoe.compose

import com.ohmz.tictactoe.core.PartySystem

interface OnParticleSystemUpdateListener {
    fun onParticleSystemEnded(
        system: PartySystem,
        activeSystems: Int,
    )
}
