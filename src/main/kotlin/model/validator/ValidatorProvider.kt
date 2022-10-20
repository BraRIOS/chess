package model.validator

import model.enums.PieceType
import model.validator.pieceValidator.*

class ValidatorProvider {
    fun getPieceValidator(pieceType: PieceType): PieceValidator {
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
