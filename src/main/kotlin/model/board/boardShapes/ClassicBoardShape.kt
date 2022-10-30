package model.board.boardShapes

import model.Piece
import model.Position
import model.enums.Color
import java.util.*

class ClassicBoardShape : BoardShape, Squared {
    private val squareShapedBoard = SquareShapedBoard(7)

    override fun isInside(position: Position): Boolean =
        squareShapedBoard.isInside(position)

    override fun isInLastRows(position: Position, color: Color): Boolean =
        squareShapedBoard.isInLastRows(position, color)

    override fun getShape(): MutableMap<Position, Optional<Piece>> =
        squareShapedBoard.getShape()

    override fun getLimit(): Int =
        squareShapedBoard.getLimit()
}
