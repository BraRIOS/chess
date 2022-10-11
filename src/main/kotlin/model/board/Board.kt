package model.board

import model.piece.Piece
import model.Position
import java.util.Optional

abstract class Board {
    protected val positions= mutableMapOf<Position, Optional<Piece>>()

    fun addPiece(piece: Piece, position: Position) {
        positions[position] = Optional.of(piece)
    }
}
