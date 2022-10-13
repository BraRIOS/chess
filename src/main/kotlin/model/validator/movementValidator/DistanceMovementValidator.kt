package model.validator.movementValidator

import model.Movement
import model.board.Board

class DistanceMovementValidator(private val limit:Int) : MovementValidator {
    override fun validateMovement(movement: Movement, board: Board): Boolean =
        movement.start.distanceTo(movement.end) <= limit

    fun validateDistanceX(movement: Movement, board: Board): Boolean =
        movement.start.distanceToX(movement.end) <= limit

    fun validateDistanceXWithLimit(movement: Movement, board: Board, limit:Int): Boolean =
        movement.start.distanceToX(movement.end) <= limit

    fun validateDistanceY(movement: Movement, board: Board): Boolean =
        movement.start.distanceToY(movement.end) <= limit

    fun validateDistanceYWithLimit(movement: Movement, board: Board, limit:Int): Boolean =
        movement.start.distanceToY(movement.end) <= limit
}
