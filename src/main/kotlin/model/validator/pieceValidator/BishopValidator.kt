package model.validator.pieceValidator

import model.Movement
import model.board.Board
import model.validator.movementValidator.DiagonalMovementValidator
import model.validator.movementValidator.MovementValidator
import model.validator.movementValidator.PieceBetweenValidator

class BishopValidator : MovementValidator, PieceBetweenValidator {
    private val diagonalMovementValidator = DiagonalMovementValidator()
    override fun validateMovement(movement: Movement, board: Board): Boolean {
        return diagonalMovementValidator.validateMovement(movement, board) &&
                !isPieceBetween(movement, board)
    }

    override fun isPieceBetween(movement: Movement, board: Board): Boolean{
        return diagonalMovementValidator.isPieceBetween(movement, board)
    }
}
