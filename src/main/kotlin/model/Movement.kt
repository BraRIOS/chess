package model

import model.piece.Piece

class Movement(val start: Position, val end: Position, val piece: Piece) {
    fun isFirstMove(): Boolean {
        return piece.isFirstMove(start)
    }
}
