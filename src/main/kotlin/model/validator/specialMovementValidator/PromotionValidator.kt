package model.validator.specialMovementValidator

import model.Movement
import model.board.Board
import model.enums.PieceType
import model.validator.movementValidator.MovementValidator

class PromotionValidator : MovementValidator{
    override fun validateMovement(movement: Movement, board: Board): Boolean =
        movement.piece.type == PieceType.PAWN && board.getBoardShape().isInLastRows(movement.end, movement.piece.color)
}
