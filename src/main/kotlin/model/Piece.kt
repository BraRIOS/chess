package model

import model.enums.Color
import model.enums.PieceType

data class Piece(val color: Color, val type: PieceType, val initialPosition: Position) {
    var hasMoved: Boolean = false
    fun isFirstMove(): Boolean {
        return !hasMoved
    }

    fun clone(): Piece {
        val piece = Piece(color, type, initialPosition)
        piece.hasMoved = hasMoved
        return piece
    }

    fun promote(): Piece {
        return Piece(color, PieceType.QUEEN, initialPosition)
    }
}
