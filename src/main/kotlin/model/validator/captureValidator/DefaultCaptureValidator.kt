package model.validator.captureValidator

import model.Movement
import model.board.Board
import model.validator.ValidatorProvider

class DefaultCaptureValidator : CaptureValidator {
    private val validatorProvider = ValidatorProvider()
    override fun validateCapture(movement: Movement, board: Board): Boolean {
        val pieceToCapture = board.getPositions()[movement.end]
        return pieceToCapture!!.isPresent && pieceToCapture.get().color != movement.piece.color &&
                validatorProvider.getPieceValidator(movement.piece.type).validateMovement(movement, board)
    }
}
