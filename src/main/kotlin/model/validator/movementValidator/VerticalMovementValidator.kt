package model.validator.movementValidator

import model.Movement
import model.Position
import model.board.Board

class VerticalMovementValidator : MovementValidator, PieceBetweenValidator {
    override fun validateMovement(movement: Movement, board: Board): Boolean =
        movement.start.x == movement.end.x && movement.start.y != movement.end.y &&
                board.isInside(movement.end) && !board.isOccupiedBySameColor(movement)

    override fun isPieceBetween(movement: Movement, board: Board): Boolean {
        val y = if (movement.start.y < movement.end.y) movement.start.y + 1 else movement.start.y - 1
        return (y until movement.end.y).any { board.isOccupied(Position(movement.start.x, it)) }
    }
}
