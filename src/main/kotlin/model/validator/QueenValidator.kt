package model.validator

import model.Movement
import model.Position
import model.board.Board
import model.validator.movementValidator.*

class QueenValidator : MovementValidator {
    private val distanceMovementValidator = DistanceMovementValidator(777)
    private val diagonalMovementValidator = DiagonalMovementValidator()
    private val horizontalMovementValidator = HorizontalMovementValidator()
    private val verticalMovementValidator = VerticalMovementValidator()

    override fun validateMovement(movement: Movement, board: Board): Boolean {
        return distanceMovementValidator.validateMovement(movement, board)&& !isPieceBetween(movement, board) &&
            (
                    diagonalMovementValidator.validateMovement(movement, board) ||
                    horizontalMovementValidator.validateMovement(movement, board) ||
                    verticalMovementValidator.validateMovement(movement, board)
                )
    }

    private fun isPieceBetween(movement: Movement, board: Board): Boolean{
        val distanceX = movement.start.distanceTo(movement.end)
        val distanceY = movement.start.distanceTo(movement.end)
        return when {
            distanceX == 0 -> {
                val y = if (movement.start.y < movement.end.y) movement.start.y + 1 else movement.start.y - 1
                (y until movement.end.y).any { board.isOccupied(Position(movement.end.x, it)) }
            }
            distanceY == 0 -> {
                val x = if (movement.start.x < movement.end.x) movement.start.x + 1 else movement.start.x - 1
                (x until movement.end.x).any { board.isOccupied(Position(it, movement.start.y)) }
            }
            else -> {
                var bool = false
                for (i in movement.start.x + 1 until movement.end.x) {
                    for (j in movement.start.y + 1 until movement.end.y) {
                        if (board.isOccupied(Position(i, j))) bool = true
                    }
                }
                bool
            }
        }
    }
}
