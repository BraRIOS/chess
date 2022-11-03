package model.validator.movementValidator

import model.Movement
import model.board.Board

interface MovementValidator {
    fun validateMovement(movement: Movement, board: Board): Boolean
}
