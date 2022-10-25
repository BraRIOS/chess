package model.validator.pieceValidator

import model.Movement
import model.board.Board
import model.validator.movementValidator.DistanceMovementValidator
import model.validator.movementValidator.MovementValidator

class KnightValidator : MovementValidator {
    private val distanceMovementValidator = DistanceMovementValidator(1)

    override fun validateMovement(movement: Movement, board: Board): Boolean {
        return ((distanceMovementValidator.validateExactDistanceXWithLimit(movement, 2) &&
                distanceMovementValidator.validateExactDistanceYWithLimit(movement, 1)) ||
                (distanceMovementValidator.validateExactDistanceXWithLimit(movement, 1) &&
                distanceMovementValidator.validateExactDistanceYWithLimit(movement, 2))) &&
                board.isInside(movement.end) && !board.isOccupiedBySameColor(movement)
    }

    override fun isPieceBetween(movement: Movement, board: Board): Boolean {
        return false
    }
}
