package model.piece

import model.Position
import model.enums.Color
import model.enums.PieceType

class Piece(val color: Color, val type: PieceType, val initialPosition: Position) {
    var hasMoved: Boolean = false
    fun isFirstMove(start: Position): Boolean {
        return hasMoved
    }
}
