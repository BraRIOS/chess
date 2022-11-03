package model.validator.pieceValidator

import model.Movement
import model.board.Board
import model.validator.movementValidator.HorizontalMovementValidator
import model.validator.movementValidator.MovementValidator
import model.validator.movementValidator.PieceBetweenValidator
import model.validator.movementValidator.VerticalMovementValidator

class RookValidator: MovementValidator, PieceBetweenValidator {
    private val verticalMovementValidator = VerticalMovementValidator()
    private val horizontalMovementValidator = HorizontalMovementValidator()

    override fun validateMovement(movement: Movement, board: Board): Boolean {
        return !isPieceBetween(movement, board) &&
                (verticalMovementValidator.validateMovement(movement, board) ||
                horizontalMovementValidator.validateMovement(movement, board))
    }

    override fun isPieceBetween(movement: Movement, board: Board): Boolean{
        val distanceX = movement.start.distanceToX(movement.end)
        val distanceY = movement.start.distanceToY(movement.end)
        return when {
            distanceX == 0 -> {
                verticalMovementValidator.isPieceBetween(movement, board)
            }
            distanceY == 0 -> {
                horizontalMovementValidator.isPieceBetween(movement, board)
            }
            else -> {
                false
            }
        }
    }
}
