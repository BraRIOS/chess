package model

import kotlin.math.abs

data class Position(val x: Int, val y: Int) {
    fun distanceTo(end: Position): Int =
        distanceToX(end) + distanceToY(end)

    fun distanceToX(end: Position): Int {
        return abs(x - end.x)
    }

    fun distanceToY(end: Position): Int {
        return abs(y - end.y)
    }
}
