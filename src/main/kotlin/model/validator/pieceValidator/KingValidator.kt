package model.validator.pieceValidator

import model.Movement
import model.board.Board
import model.validator.movementValidator.*

class KingValidator : MovementValidator {
    private val diagonalMovementValidator = DiagonalMovementValidator()
    private val horizontalMovementValidator = HorizontalMovementValidator()
    private val verticalMovementValidator = VerticalMovementValidator()
    private val distanceMovementValidator = DistanceMovementValidator(1)

    override fun validateMovement(movement: Movement, board: Board): Boolean {
        return distanceMovementValidator.validateMovement(movement, board) &&
                (diagonalMovementValidator.validateMovement(movement, board) ||
                horizontalMovementValidator.validateMovement(movement, board) ||
                verticalMovementValidator.validateMovement(movement, board)) &&
                !isPieceBetween(movement, board)
    }

    override fun isPieceBetween(movement: Movement, board: Board): Boolean{
        val distanceX = movement.start.distanceTo(movement.end)
        val distanceY = movement.start.distanceTo(movement.end)
        return when {
            distanceX == 0 -> {
                if (distanceMovementValidator.validateDistanceY(movement)) {
                    verticalMovementValidator.isPieceBetween(movement, board)
                } else {
                    false
                }
            }
            distanceY == 0 -> {
                if (distanceMovementValidator.validateDistanceX(movement)) {
                    horizontalMovementValidator.isPieceBetween(movement, board)
                } else {
                    false
                }
            }
            else -> {
                if (distanceMovementValidator.validateMovement(movement, board)) {
                    diagonalMovementValidator.isPieceBetween(movement, board)
                } else {
                    false
                }
            }
        }
    }
}