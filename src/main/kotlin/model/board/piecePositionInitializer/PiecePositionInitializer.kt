package model.board.piecePositionInitializer

import model.Piece
import model.Position
import java.util.*

interface PiecePositionInitializer {
    fun initializePieces(positions: MutableMap<Position, Optional<Piece>>)
}
