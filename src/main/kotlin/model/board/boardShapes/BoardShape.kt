package model.board.boardShapes

import model.Piece
import model.Position
import model.enums.Color
import java.util.*

interface BoardShape {
    fun isInside(position: Position): Boolean

    fun isInLastRows(position: Position, color: Color): Boolean
    fun getShape(): MutableMap<Position, Optional<Piece>>
}
