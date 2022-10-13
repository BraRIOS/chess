package model.validator

import model.Movement
import model.board.Board
import model.validator.movementValidator.*

class PawnValidator : MovementValidator, PieceBetweenValidator {
    private val distanceMovementValidator = DistanceMovementValidator(1)
    private val distanceMovementValidator2 = DistanceMovementValidator(2)
    private val verticalMovementValidator = VerticalMovementValidator()
    private val diagonalMovementValidator = DiagonalMovementValidator()

    override fun validateMovement(movement: Movement, board: Board): Boolean {
        return validatePawnMovement(movement, board) && !isPieceBetween(movement, board)
    }

    /**
     * REVISAR
     */
    private fun validatePawnMovement(movement: Movement, board: Board): Boolean {
        return validatePawnMovementForward(movement, board) ||
                validatePawnMovementDiagonal(movement, board) ||
                validatePawnMovementFirstMove(movement, board)
    }

    private fun validatePawnMovementFirstMove(movement: Movement, board: Board): Boolean {
        TODO("Not yet implemented")
    }

    private fun validatePawnMovementDiagonal(movement: Movement, board: Board): Boolean {
        TODO("Not yet implemented")
    }

    private fun validatePawnMovementForward(movement: Movement, board: Board): Boolean {

    }
}
