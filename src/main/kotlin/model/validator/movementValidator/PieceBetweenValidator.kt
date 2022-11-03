package model.validator.movementValidator

import model.Movement
import model.board.Board

interface PieceBetweenValidator {
    fun isPieceBetween(movement: Movement, board: Board): Boolean
}
