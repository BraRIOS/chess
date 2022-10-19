package model.validator

import model.Movement
import model.board.Board
import model.enums.Color
import model.enums.PieceType

class CheckValidator {
    fun isCheck(board: Board, color: Color): Boolean {
        val kingPosition = board.getPiecePosition(PieceType.KING, color)
        val provider = ValidatorProvider()
        return board.getOpponentPieces(color).any { piece ->
            provider.getValidator(piece.type).validateMovement(Movement(board.getPiecePosition(piece), kingPosition, piece), board)
        }
    }
}