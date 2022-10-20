package model.board.boardShapes

import model.Piece
import model.Position
import java.util.*

class ClassicBoardShape : BoardShape, Squared {
    private val squareShapedBoard = SquareShapedBoard(7)

    override fun isInside(position: Position): Boolean =
        squareShapedBoard.isInside(position)

    override fun getBoardShape(): MutableMap<Position, Optional<Piece>> =
        squareShapedBoard.getBoardShape()

    override fun getLimit(): Int =
        squareShapedBoard.getLimit()
}
