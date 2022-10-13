package model.validator.movementValidator

import model.Movement
import model.board.Board

class VerticalMovementValidator : MovementValidator {
    override fun validateMovement(movement: Movement, board: Board): Boolean =
        movement.start.x == movement.end.x && movement.start.y != movement.end.y && board.isInside(movement.end) && !board.isOccupied(movement.end)
}
