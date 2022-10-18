package model.validator.specialMovementValidator

import model.Movement
import model.board.Board
import model.enums.Color
import model.enums.PieceType
import model.validator.CheckValidator
import model.validator.KingValidator
import model.validator.RookValidator

class CastlingValidator{
    private val rookValidator = RookValidator()
    private val kingValidator = KingValidator()
    private val checkValidator = CheckValidator()

    fun canCastling(board: Board, color: Color): Boolean {
        val rooks = board.getRooks(color)
        val rookPosition = board.getPiecePosition(PieceType.ROOK, color)
        val kingPosition = board.getPiecePosition(PieceType.KING, color)

        return board.getKing(color).hasMoved && !checkValidator.isCheck(board, color) && rooks.all { !it.hasMoved } &&
                rooks.all { rook -> !rookValidator.isPieceBetween(Movement(rookPosition, kingPosition, rook), board) } &&
                rooks.all { rook -> !kingValidator.isPieceBetween(Movement(rookPosition, kingPosition, rook), board) }
    }
}
