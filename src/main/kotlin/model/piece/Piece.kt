package model.piece

import model.enums.Color
import model.enums.PieceType

class Piece(val color: Color, val type: PieceType) {
    var alreadyMoved = false

    fun hasAlredyMoved(): Boolean {
        return alreadyMoved
    }
}
