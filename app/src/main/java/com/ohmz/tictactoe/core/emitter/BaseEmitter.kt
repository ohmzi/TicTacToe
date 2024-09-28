package com.ohmz.tictactoe.core.emitter

import com.ohmz.tictactoe.core.Party
import com.ohmz.tictactoe.core.models.CoreRect

/**
 * An abstract class for creating a custom emitter
 * The emitter decides if a particle should be created and when the emitter is finished
 */
abstract class BaseEmitter {
    /**
     * This function is called on each update when the [RenderSystem] is active
     * Keep this function as light as possible otherwise you'll slow down the render system
     */
    abstract fun createConfetti(
        deltaTime: Float,
        party: Party,
        drawArea: CoreRect,
    ): List<Confetti>

    /**
     * @return true if the emitter is no longer creating any particles
     *         false if is still busy
     */
    abstract fun isFinished(): Boolean
}
