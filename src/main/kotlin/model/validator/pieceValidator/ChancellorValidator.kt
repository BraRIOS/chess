package model.validator.pieceValidator

import model.Movement
import model.board.Board
import model.validator.movementValidator.MovementValidator

class ChancellorValidator: MovementValidator {
    private val rookValidator = RookValidator()
    private val knightValidator = KnightValidator()

    override fun validateMovement(movement: Movement, board: Board): Boolean {
        return rookValidator.validateMovement(movement, board) ||
                knightValidator.validateMovement(movement, board)

    }
}