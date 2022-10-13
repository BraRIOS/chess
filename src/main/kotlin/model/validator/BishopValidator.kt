package model.validator

import model.Movement
import model.board.Board
import model.validator.movementValidator.DiagonalMovementValidator
import model.validator.movementValidator.DistanceMovementValidator
import model.validator.movementValidator.MovementValidator

class BishopValidator : MovementValidator {
    override fun validateMovement(movement: Movement, board: Board): Boolean {
        val distanceMovementValidator = DistanceMovementValidator()
        distanceMovementValidator.limit = 7
        val diagonalMovementValidator = DiagonalMovementValidator()

        return distanceMovementValidator.validateMovement(movement, board) && diagonalMovementValidator.validateMovement(movement, board)
    }
}
