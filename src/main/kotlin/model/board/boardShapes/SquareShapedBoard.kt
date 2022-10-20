package model.board.boardShapes

import model.Piece
import model.Position
import java.util.*

class SquareShapedBoard(private val limit: Int) : BoardShape, Squared {
    override fun isInside(position: Position): Boolean {
        return position.x in 0..limit && position.y in 0..limit
    }

    override fun getBoardShape(): MutableMap<Position, Optional<Piece>> {
        val boardShape: MutableMap<Position, Optional<Piece>> = mutableMapOf()
        for (x in 0..limit) {
            for (y in 0..limit) {
                boardShape[Position(x, y)] = Optional.empty()
            }
        }
        return boardShape
    }

    override fun getLimit(): Int {
        return limit
    }
}
