package model.validator

import model.Movement
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

    fun getValidMovements(movement: Movement, board: Board): List<Movement> {
        val positions = board.getPositions().keys
        return positions.filter {
            this.validateMovement(Movement(movement.start,it, board.getPiece(it)),board)
        }.map { Movement(movement.start, it, board.getPiece(it)) }
    }
}
