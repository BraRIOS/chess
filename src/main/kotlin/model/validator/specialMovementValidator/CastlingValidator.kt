package model.validator.specialMovementValidator

import model.Movement
import model.Position
import model.board.Board
import model.enums.Color
import model.enums.PieceType
import model.piece.Piece
import model.validator.CheckValidator
import model.validator.KingValidator
import model.validator.RookValidator

class CastlingValidator{
    private val rookValidator = RookValidator()
    private val checkValidator = CheckValidator()
    private val kingValidator = KingValidator()

    fun canCastlingWithRook(board: Board, color: Color, rook:Piece): Boolean {
        val king = board.getPiece(PieceType.KING, color)
        return king.hasMoved.not() && !checkValidator.isCheck(board, color) && !isCastlingThroughCheck(board, color, rook)
    }

    private fun isCastlingThroughCheck(board: Board, color: Color, rook: Piece): Boolean {
        val king = board.getPiece(PieceType.KING, color)
        val kingPosition = board.getPiecePosition(king)
        val rookPosition = board.getPiecePosition(rook)
        val kingMovement = Movement(kingPosition, kingPosition, king)
        val rookMovement = Movement(rookPosition, rookPosition, rook)
        return when {
            rook.hasMoved -> true
            kingPosition.x < rookPosition.x -> {
                kingMovement.end = Position(kingPosition.x + 1, kingPosition.y)
                rookMovement.end = Position(rookPosition.x - 1, rookPosition.y)
                isCastlingThroughCheck(board, kingMovement, rookMovement, color)
            }
            else -> {
                kingMovement.end = Position(kingPosition.x - 1, kingPosition.y)
                rookMovement.end = Position(rookPosition.x + 1, rookPosition.y)
                isCastlingThroughCheck(board, kingMovement, rookMovement, color)
            }
        }
    }

    private fun isCastlingThroughCheck(board: Board, kingMovement: Movement, rookMovement: Movement, color: Color): Boolean {
        val isCheck = checkValidator.isCheck(board, color)
        board.movePiece(kingMovement)
        board.movePiece(rookMovement)
        return isCheck || !kingValidator.validateMovement(kingMovement, board) || !rookValidator.validateMovement(rookMovement, board)
    }
}
