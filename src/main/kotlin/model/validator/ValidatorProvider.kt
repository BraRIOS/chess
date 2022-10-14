package model.validator

import model.enums.PieceType
import model.validator.movementValidator.MovementValidator

class ValidatorProvider {
    fun getValidator(pieceType: PieceType): MovementValidator {
        return when (pieceType) {
            PieceType.PAWN -> PawnValidator()
            PieceType.ROOK -> RookValidator()
            PieceType.KNIGHT -> KnightValidator()
            PieceType.BISHOP -> BishopValidator()
            PieceType.QUEEN -> QueenValidator()
            PieceType.KING -> KingValidator()
        }
    }
}
