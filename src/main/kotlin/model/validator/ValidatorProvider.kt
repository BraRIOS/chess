package model.validator

import model.enums.PieceType
import model.validator.movementValidator.MovementValidator
import model.validator.pieceValidator.*

class ValidatorProvider {
    val checkValidator = CheckValidator()
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
}
