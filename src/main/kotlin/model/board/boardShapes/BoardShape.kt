package model.board.boardShapes

import model.Piece
import model.Position
import java.util.*

interface BoardShape {
    fun isInside(position: Position): Boolean

    fun getBoardShape(): MutableMap<Position, Optional<Piece>>
}
