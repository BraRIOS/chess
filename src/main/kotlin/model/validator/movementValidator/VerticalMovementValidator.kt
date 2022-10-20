package model.validator.movementValidator

import model.Movement
import model.Position
import model.board.Board

class VerticalMovementValidator : MovementValidator {
    override fun validateMovement(movement: Movement, board: Board): Boolean =
        movement.start.x == movement.end.x && movement.start.y != movement.end.y &&
                board.isInside(movement.end) && !board.isOccupiedBySameColor(movement)

    override fun isPieceBetween(movement: Movement, board: Board): Boolean {
        val directionY = if (movement.end.y < movement.start.y)
            -1
        else 1

        if (directionY<0)
            for (y in movement.start.y - 1 downTo movement.end.y + 1) {
                if (board.isOccupied(Position(movement.start.x, y))) {
                    return true
                }
            }
        else
            for (y in movement.start.y + 1 until movement.end.y) {
                if (board.isOccupied(Position(movement.start.x, y))) {
                    return true
                }
            }
        return false
    }
}
