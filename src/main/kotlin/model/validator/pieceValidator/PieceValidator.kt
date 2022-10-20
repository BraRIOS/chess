package model.validator.pieceValidator

import model.validator.movementValidator.MovementValidator
import model.validator.movementValidator.PieceBetweenValidator

interface PieceValidator: MovementValidator, PieceBetweenValidator {
}