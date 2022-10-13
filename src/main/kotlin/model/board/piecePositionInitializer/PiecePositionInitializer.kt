package model.board.piecePositionInitializer

import model.Position
import model.piece.Piece
import java.util.*

interface PiecePositionInitializer {
    fun initializePieces(positions: MutableMap<Position, Optional<Piece>>)
}
