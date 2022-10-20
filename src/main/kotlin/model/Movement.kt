package model

class Movement(val start: Position, var end: Position, val piece: Piece) {
    fun isFirstMove(): Boolean {
        return piece.isFirstMove()
    }
}
