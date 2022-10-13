package model.board.boardShapes

import model.Position
import model.piece.Piece
import java.util.*

class ClassicBoardShape : BoardShape {
    private val squareShapedBoard = SquareShapedBoard(7)

    override fun isInside(position: Position): Boolean =
        squareShapedBoard.isInside(position)

    override fun getBoardShape(): MutableMap<Position, Optional<Piece>> =
        squareShapedBoard.getBoardShape()
}
