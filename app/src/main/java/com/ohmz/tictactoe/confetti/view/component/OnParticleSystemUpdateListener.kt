package com.ohmz.tictactoe.confetti.view.component

import com.ohmz.tictactoe.confetti.design.PartySystem

interface OnParticleSystemUpdateListener {
    fun onParticleSystemEnded(
        system: PartySystem,
        activeSystems: Int,
    )
}
