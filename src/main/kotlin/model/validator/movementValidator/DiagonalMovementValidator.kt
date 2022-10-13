package model.validator.movementValidator

import model.Movement
import model.board.Board

class DiagonalMovementValidator : MovementValidator {
    override fun validateMovement(movement: Movement, board: Board): Boolean {
        val distanceX = movement.start.distanceTo(movement.end)
        val distanceY = movement.start.distanceTo(movement.end)
        return distanceX == distanceY && distanceX != 0 && board.isInside(movement.end) && !board.isOccupied(movement.end)
    }
}
