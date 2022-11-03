package model.validator.specialMovementValidator

import model.Movement
import model.Piece
import model.Position
import model.board.Board
import model.enums.Color
import model.enums.PieceType
import model.validator.CheckValidator
import model.validator.pieceValidator.KingValidator
import model.validator.pieceValidator.RookValidator

class CastlingValidator{
    private val rookValidator = RookValidator()
    private val checkValidator = CheckValidator()
    private val kingValidator = KingValidator()

    fun canCastlingWithRook(board: Board, king: Piece, rook: Piece): Boolean {
        val color = rook.color
        return king.type == PieceType.KING && rook.type == PieceType.ROOK && king.hasMoved.not() &&
                !checkValidator.isCheck(board, color) && !isCastlingThroughCheck(board, color, rook)
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
                kingMovement.end = Position(kingPosition.x + 2, kingPosition.y)
                rookMovement.end = Position(kingMovement.end.x - 1, rookPosition.y)
                isCastlingThroughCheck(board, kingMovement, rookMovement, color)
            }
            else -> {
                kingMovement.end = Position(kingPosition.x - 2, kingPosition.y)
                rookMovement.end = Position(kingMovement.end.x + 1, rookPosition.y)
                isCastlingThroughCheck(board, kingMovement, rookMovement, color)
            }
        }
    }

    private fun isCastlingThroughCheck(board: Board, kingMovement: Movement, rookMovement: Movement, color: Color): Boolean {
        if (!kingValidator.isPieceBetween(kingMovement, board) && !rookValidator.isPieceBetween(rookMovement, board)) {
            val newBoard = board.clone()
            newBoard.movePiece(kingMovement)
            newBoard.movePiece(rookMovement)
            return checkValidator.isCheck(newBoard, color)
        }
        return true
    }

    fun castlingWithRook(pieceStart: Piece, board: Board, pieceEnd: Piece) {
        val rookPosition = board.getPiecePosition(pieceEnd)
        val kingPosition = board.getPiecePosition(pieceStart)
        val rookMovement = Movement(rookPosition, rookPosition, pieceEnd)
        val kingMovement = Movement(kingPosition, kingPosition, pieceStart)
        when {
            kingPosition.x < rookPosition.x -> {
                kingMovement.end = Position(kingPosition.x + 2, kingPosition.y)
                rookMovement.end = Position(kingMovement.end.x - 1, rookPosition.y)
            }
            else -> {
                kingMovement.end = Position(kingPosition.x - 2, kingPosition.y)
                rookMovement.end = Position(kingMovement.end.x + 1, rookPosition.y)
            }
        }
        board.movePiece(kingMovement)
        board.movePiece(rookMovement)
    }
}
