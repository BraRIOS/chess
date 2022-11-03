package model.board.boardShapes

import model.Piece
import model.Position
import model.enums.Color
import java.util.*

class CapablancaBoardShape : BoardShape {
    private val rectangularShapedBoard = RectangularShapedBoard(9, 7)

    override fun isInside(position: Position): Boolean =
        rectangularShapedBoard.isInside(position)

    override fun isInLastRows(position: Position, color: Color): Boolean =
        rectangularShapedBoard.isInLastRows(position, color)

    override fun getShape(): MutableMap<Position, Optional<Piece>> =
        rectangularShapedBoard.getShape()

    override fun getWidth(): Int =
        rectangularShapedBoard.getWidth()

    override fun getLength(): Int =
        rectangularShapedBoard.getLength()
}