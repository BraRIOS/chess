package model.board.boardShapes

import model.Position
import model.piece.Piece
import java.util.Optional

interface BoardShape {
    fun isInside(position: Position): Boolean

    fun getBoardShape(): MutableMap<Position, Optional<Piece>>
}
