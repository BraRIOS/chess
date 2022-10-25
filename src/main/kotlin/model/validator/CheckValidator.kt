package model.validator

import model.Movement
import model.board.Board
import model.enums.Color
import model.enums.PieceType

class CheckValidator {
    private val validatorProvider = ValidatorProvider()

    fun isCheck(board: Board, color: Color): Boolean {
        val kingPosition = board.getPiecePosition(PieceType.KING, color)
        val provider = ValidatorProvider()
        return board.getOpponentPieces(color).any { piece ->
            provider.getPieceValidator(piece.type)
                .validateMovement(Movement(board.getPiecePosition(piece), kingPosition, piece), board)
        }
    }

    fun canUncheck(board: Board, color: Color): Boolean {
        val allyPieces = board.getAllyPieces(color)
        return allyPieces.any { piece ->
            val piecePosition = board.getPiecePosition(piece)
            val validMovements = validatorProvider.getValidMovements(piecePosition, board)
            validMovements.any { movement ->
                board.movePiece(movement)
                val isCheck = isCheck(board, color)
                board.undoLastMovement()
                !isCheck
            }
        }
    }
}