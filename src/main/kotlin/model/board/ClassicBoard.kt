package model.board

import model.Position

import java.util.*

object ClassicBoard: Board() {
    init {
        for (i in 0..7)
            for (j in 0..7)
                positions[Position(i, j)] = Optional.empty()
    }
}
