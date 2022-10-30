package model.board.boardShapes

import model.Piece
import model.Position
import model.enums.Color
import java.util.*

class SquareShapedBoard(private val limit: Int) : BoardShape, Squared {
    override fun isInside(position: Position): Boolean {
        return position.x in 0..limit && position.y in 0..limit
    }

    override fun isInLastRows(position: Position, color: Color): Boolean {
        return if (color == Color.WHITE) {
            position.y == limit
        } else {
            position.y == 0
        }
    }

    override fun getShape(): MutableMap<Position, Optional<Piece>> {
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
