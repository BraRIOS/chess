package model

import kotlin.math.abs
import kotlin.math.hypot

data class Position(val x: Int, val y: Int) {
    fun distanceTo(end: Position): Int =
        hypot((end.x - x).toDouble(), (end.y - y).toDouble()).toInt()

    fun distanceToX(end: Position): Int {
        return abs(end.x - x)
    }

    fun distanceToY(end: Position): Int {
        return abs(y - end.y)
    }
}
