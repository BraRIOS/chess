package model

import kotlin.math.abs

class Position(val x: Int, val y: Int) {
    fun distanceTo(position: Position): Int =
        abs(x - position.x) + abs(y - position.y)
}
