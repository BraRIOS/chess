package model.board.boardShapes

import model.Piece
import model.Position
import model.enums.Color
import java.util.*

class RectangularShapedBoard(private val width:Int, private val length:Int): BoardShape{
    override fun isInside(position: Position): Boolean {
        return position.x in 0..width && position.y in 0..length
    }

    override fun isInLastRows(position: Position, color: Color): Boolean {
        return if (color == Color.WHITE) {
            position.y == length
        } else {
            position.y == 0
        }
    }

    override fun getShape(): MutableMap<Position, Optional<Piece>> {
        val boardShape: MutableMap<Position, Optional<Piece>> = mutableMapOf()
        for (x in 0..width) {
            for (y in 0..length) {
                boardShape[Position(x, y)] = Optional.empty()
            }
        }
        return boardShape
    }

    override fun getLength(): Int = length

    override fun getWidth(): Int = width
}