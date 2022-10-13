package model.validator.movementValidator

import model.Movement
import model.board.Board

class HorizontalMovementValidator : MovementValidator {
    override fun validateMovement(movement: Movement, board: Board): Boolean =
        movement.start.y == movement.end.y && movement.start.x != movement.end.x && board.isInside(movement.end) && !board.isOccupied(movement.end)
}
