package model.validator.movementValidator

import model.Movement
import model.Position
import model.board.Board

class HorizontalMovementValidator : MovementValidator {
    override fun validateMovement(movement: Movement, board: Board): Boolean =
        movement.start.y == movement.end.y && movement.start.x != movement.end.x && board.isInside(movement.end) && !board.isOccupiedBySameColor(movement)

    override fun getValidMovements(position: Position, board: Board): List<Movement> {
        TODO("Not yet implemented")
    }

    override fun isPieceBetween(movement: Movement, board: Board): Boolean{
        val directionX = if (movement.end.x < movement.start.x)
            -1
        else 1
        if (directionX<0) {
            for (x in movement.start.x - 1 downTo movement.end.x + 1) {
                if (board.isOccupied(Position(x, movement.start.y))) {
                    return true
                }
            }
        } else {
            for (x in movement.start.x + 1 until movement.end.x) {
                if (board.isOccupied(Position(x, movement.start.y))) {
                    return true
                }
            }
        }
        return false
    }
}
