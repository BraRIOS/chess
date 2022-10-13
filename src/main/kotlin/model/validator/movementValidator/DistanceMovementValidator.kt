package model.validator.movementValidator

import model.Movement
import model.board.Board

class DistanceMovementValidator(val limit:Int) : MovementValidator {
    override fun validateMovement(movement: Movement, board: Board): Boolean =
        movement.start.distanceTo(movement.end) <= limit
}
