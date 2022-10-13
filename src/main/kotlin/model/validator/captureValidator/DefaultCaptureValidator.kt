package model.validator.captureValidator

import model.Movement
import model.board.Board

class DefaultCaptureValidator : CaptureValidator {
    override fun validateCapture(movement: Movement, board: Board): Boolean {
        val pieceToCapture = board.getPositions()[movement.end]
        return pieceToCapture!!.isPresent && pieceToCapture.get().color != movement.piece.color
    }
}
