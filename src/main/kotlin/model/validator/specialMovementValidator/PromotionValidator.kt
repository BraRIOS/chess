package model.validator.specialMovementValidator

import model.Movement
import model.board.Board
import model.enums.PieceType

class PromotionValidator{
    fun canPromote(board: Board, movement: Movement): Boolean =
        movement.piece.type == PieceType.PAWN && board.getBoardShape().isInLastRows(movement.end, movement.piece.color)
}
