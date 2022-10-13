package model.validator.movementValidator

import model.Movement
import model.Position
import model.board.Board

class DiagonalMovementValidator : MovementValidator, PieceBetweenValidator {
    override fun validateMovement(movement: Movement, board: Board): Boolean {
        val distanceX = movement.start.distanceTo(movement.end)
        val distanceY = movement.start.distanceTo(movement.end)
        return distanceX == distanceY && distanceX != 0 && board.isInside(movement.end) && !board.isOccupiedBySameColor(movement.end)
    }

    override fun isPieceBetween(movement: Movement, board: Board): Boolean{
        var bool = false
        for (i in movement.start.x + 1 until movement.end.x) {
            for (j in movement.start.y + 1 until movement.end.y) {
                if (board.isOccupiedBySameColor(Position(i, j))) bool = true
            }
        }
        return bool
    }
}
