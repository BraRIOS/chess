package model.validator

import model.Movement
import model.Position
import model.board.Board
import model.validator.movementValidator.*

class QueenValidator : MovementValidator, PieceBetweenValidator {
    private val diagonalMovementValidator = DiagonalMovementValidator()
    private val horizontalMovementValidator = HorizontalMovementValidator()
    private val verticalMovementValidator = VerticalMovementValidator()

    override fun validateMovement(movement: Movement, board: Board): Boolean {
        return !isPieceBetween(movement, board) &&
                (diagonalMovementValidator.validateMovement(movement, board) ||
                horizontalMovementValidator.validateMovement(movement, board) ||
                verticalMovementValidator.validateMovement(movement, board))
    }

    override fun isPieceBetween(movement: Movement, board: Board): Boolean{
        val distanceX = movement.start.distanceTo(movement.end)
        val distanceY = movement.start.distanceTo(movement.end)
        return when {
            distanceX == 0 -> {
                verticalMovementValidator.isPieceBetween(movement, board)
            }
            distanceY == 0 -> {
                horizontalMovementValidator.isPieceBetween(movement, board)
            }
            else -> {
                diagonalMovementValidator.isPieceBetween(movement, board)
            }
        }
    }
}
