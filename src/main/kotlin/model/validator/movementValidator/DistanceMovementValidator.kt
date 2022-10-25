package model.validator.movementValidator

import model.Movement
import model.board.Board

class DistanceMovementValidator(private val limit:Int) : MovementValidator {
    override fun validateMovement(movement: Movement, board: Board): Boolean =
        movement.start.distanceTo(movement.end) <= limit

    override fun isPieceBetween(movement: Movement, board: Board): Boolean {
        return false
    }

    fun validateDistanceX(movement: Movement): Boolean =
        movement.start.distanceToX(movement.end) <= limit

    fun validateExactDistanceXWithLimit(movement: Movement, limit: Int): Boolean =
        movement.start.distanceToX(movement.end) == limit

    fun validateDistanceY(movement: Movement): Boolean =
        movement.start.distanceToY(movement.end) <= limit

    fun validateExactDistanceY(movement: Movement): Boolean =
        movement.start.distanceToY(movement.end) == limit

    fun validateDistanceYWithLimit(movement: Movement, limit: Int): Boolean =
        movement.start.distanceToY(movement.end) <= limit
    fun validateExactDistanceYWithLimit(movement: Movement, limit: Int): Boolean =
        movement.start.distanceToY(movement.end) == limit
}
