package model.validator.pieceValidator

import model.Movement
import model.board.Board
import model.validator.movementValidator.DiagonalMovementValidator
import model.validator.movementValidator.DistanceMovementValidator
import model.validator.movementValidator.VerticalMovementValidator

class PawnValidator : PieceValidator {
    private val distanceMovementValidator = DistanceMovementValidator(1)
    private val verticalMovementValidator = VerticalMovementValidator()
    private val diagonalMovementValidator = DiagonalMovementValidator()

    override fun validateMovement(movement: Movement, board: Board): Boolean {
        return validatePawnMovement(movement, board) && !isPieceBetween(movement, board)
    }


    private fun validatePawnMovement(movement: Movement, board: Board): Boolean {
        return validatePawnMovementForward(movement, board) ||
                validatePawnMovementDiagonal(movement, board) ||
                validatePawnMovementFirstMove(movement, board)
    }

    private fun validatePawnMovementFirstMove(movement: Movement, board: Board): Boolean {
        return distanceMovementValidator.validateDistanceYWithLimit(movement, 2) &&
                movement.isFirstMove() && verticalMovementValidator.validateMovement(movement, board)
                && !board.isOccupied(movement.end)
    }

    private fun validatePawnMovementDiagonal(movement: Movement, board: Board): Boolean {
        return diagonalMovementValidator.validateMovement(movement, board) &&
                distanceMovementValidator.validateMovement(movement, board)
    }

    private fun validatePawnMovementForward(movement: Movement, board: Board): Boolean {
        return verticalMovementValidator.validateMovement(movement, board) &&
                distanceMovementValidator.validateDistanceY(movement) &&
                !board.isOccupied(movement.end)
    }

    override fun isPieceBetween(movement: Movement, board: Board): Boolean {
        return verticalMovementValidator.isPieceBetween(movement, board)
    }
}