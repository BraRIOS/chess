package model.validator

import model.Movement
import model.board.Board
import model.validator.movementValidator.*

class KnightValidator : MovementValidator{
    private val distanceMovementValidator = DistanceMovementValidator(1)

    override fun validateMovement(movement: Movement, board: Board): Boolean {
        return distanceMovementValidator.validateDistanceXWithLimit(movement, board, 2) &&
                distanceMovementValidator.validateDistanceYWithLimit(movement, board, 1) ||
                distanceMovementValidator.validateDistanceXWithLimit(movement, board, 1) &&
                distanceMovementValidator.validateDistanceYWithLimit(movement, board, 2) &&
                board.isInside(movement.end) && !board.isOccupiedBySameColor(movement.end)
    }
}
