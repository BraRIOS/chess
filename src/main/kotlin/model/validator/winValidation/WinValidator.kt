package model.validator.winValidation

import model.board.Board
import model.enums.Color

class WinValidator(private val winConditions: List<WinCondition>) : WinCondition {
    override fun isWin(board: Board, opponentColor: Color): Boolean {
        for (winCondition in winConditions) {
            if (winCondition.isWin(board, opponentColor)) {
                return true
            }
        }
        return false
    }
}