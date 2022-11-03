package model.validator

import model.Movement
import model.Position
import model.board.Board
import model.enums.PieceType
import model.validator.movementValidator.MovementValidator
import model.validator.pieceValidator.*

class ValidatorProvider {
    fun getPieceValidator(pieceType: PieceType): MovementValidator {
        return when (pieceType) {
            PieceType.PAWN -> PawnValidator()
            PieceType.ROOK -> RookValidator()
            PieceType.KNIGHT -> KnightValidator()
            PieceType.BISHOP -> BishopValidator()
            PieceType.QUEEN -> QueenValidator()
            PieceType.KING -> KingValidator()
        }
    }

    fun getValidMovements(initialPosition: Position, board: Board): List<Movement> {
        val positions = board.getPositions().keys
        val piece = board.getPiece(initialPosition)
        val pieceValidator = getPieceValidator(piece!!.type)
        return positions.filter {
            pieceValidator.validateMovement(Movement(initialPosition,it, piece),board)
        }.map { Movement(initialPosition, it, piece) }
    }
}
