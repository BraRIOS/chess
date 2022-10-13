package model.validator.movementValidator

import model.Movement
import model.Position
import model.board.Board

class HorizontalMovementValidator : MovementValidator, PieceBetweenValidator {
    override fun validateMovement(movement: Movement, board: Board): Boolean =
        movement.start.y == movement.end.y && movement.start.x != movement.end.x && board.isInside(movement.end) && !board.isOccupiedBySameColor(movement.end)

    override fun isPieceBetween(movement: Movement, board: Board): Boolean{
        val x = if (movement.start.x < movement.end.x) movement.start.x + 1 else movement.start.x - 1
        return (x until movement.end.x).any { board.isOccupiedBySameColor(Position(it, movement.start.y)) }
    }
}
