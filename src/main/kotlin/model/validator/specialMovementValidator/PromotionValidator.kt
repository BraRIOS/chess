package model.validator.specialMovementValidator

import model.Movement
import model.Position
import model.board.Board
import model.enums.Color
import model.enums.PieceType

class PromotionValidator{
    fun canPromote(board: Board, movement: Movement): Boolean {
        return movement.piece.type == PieceType.PAWN && ((movement.end.y == 0 && movement.piece.color == Color.BLACK)
                || (board.isInside(movement.end) && board.isInside(Position(movement.end.x, movement.end.y + 1)) && movement.piece.color == Color.WHITE))
    }
}
