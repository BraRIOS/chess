package model.validator.pieceValidator

import model.Movement
import model.board.Board
import model.validator.movementValidator.MovementValidator

class ArchbishopValidator: MovementValidator {
    private val bishopValidator = BishopValidator()
    private val knightValidator = KnightValidator()

    override fun validateMovement(movement: Movement, board: Board): Boolean {
        return bishopValidator.validateMovement(movement, board) ||
                knightValidator.validateMovement(movement, board)
    }
}