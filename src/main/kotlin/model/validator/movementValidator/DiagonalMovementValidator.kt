package model.validator.movementValidator

import model.Movement
import model.Position
import model.board.Board
import kotlin.math.abs

class DiagonalMovementValidator : MovementValidator, PieceBetweenValidator {
    override fun validateMovement(movement: Movement, board: Board): Boolean {
        val distanceX = movement.start.distanceTo(movement.end)
        val distanceY = movement.start.distanceTo(movement.end)
        return distanceX == distanceY && distanceX != 0 && board.isInside(movement.end) && !board.isOccupiedBySameColor(movement)
    }

    override fun isPieceBetween(movement: Movement, board: Board): Boolean{
        if(abs(movement.start.x-movement.end.x) == abs(movement.start.y - movement.end.y)) {
            //check the directions
            val directionX = if (movement.end.x < movement.start.x)
                -1
            else 1

            val directionY = if (movement.end.y < movement.start.y)
                -1
            else 1

            var y = movement.start.y

            if (directionX < 0) {
                for (x in movement.start.x - 1 downTo movement.end.x + 1) {
                    y += directionY
                    if (board.isOccupied(Position(x, y))) {
                        return true
                    }
                }
            } else {
                for (x in movement.start.x + 1 until movement.end.x) {
                    y += directionY
                    if (board.isOccupied(Position(x, y))) {
                        return true
                    }
                }
            }
        }
        return false
    }
}
