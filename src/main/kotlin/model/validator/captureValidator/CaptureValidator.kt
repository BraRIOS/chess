package model.validator.captureValidator

import model.Movement
import model.board.Board

interface CaptureValidator {
    fun validateCapture(movement: Movement, board: Board): Boolean
}
