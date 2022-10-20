package model.validator

import model.Movement
import model.Piece
import model.board.Board
import model.enums.Color
import model.enums.PieceType

class CheckValidator {
    val checkers = listOf<Piece>()
    private val validatorProvider = ValidatorProvider()

    fun isCheck(board: Board, color: Color): Boolean {
        return getCheckers(board, color).isNotEmpty()
    }

    fun canUncheck(board: Board, color: Color): Boolean {
        return getCheckers(board, color).any { canAttackChecker(board, color, it) } || canMoveKing(board, color)
    }

    private fun canMoveKing(board: Board, color: Color): Boolean {
        val king = board.getPiece(PieceType.KING, color)
        val kingPosition = board.getPiecePosition(king)
        val kingValidator = validatorProvider.getPieceValidator(PieceType.KING)
        val positions= board.getPositions().keys.filter { kingValidator.validateMovement(Movement(kingPosition, it, king), board) }
        val movements = positions.map { Movement(kingPosition, it, king) }
        val newBoard = board.clone()
        return movements.any { move ->
            newBoard.movePiece(move)
            !isCheck(newBoard, color)
        }
    }

    private fun canAttackChecker(board: Board, color: Color, piece: Piece): Boolean {
        return board.getAllyPieces(color).any {
            validatorProvider.getPieceValidator(it.type).
            validateMovement(Movement(board.getPiecePosition(it), board.getPiecePosition(piece), it), board)}
    }

    fun getCheckers(board: Board, color: Color): List<Piece> {
        val kingPosition = board.getPiecePosition(PieceType.KING, color)
        val provider = ValidatorProvider()
        return board.getOpponentPieces(color).filter { piece ->
            provider.getPieceValidator(piece.type)
                .validateMovement(Movement(board.getPiecePosition(piece), kingPosition, piece), board)
        }
    }
}