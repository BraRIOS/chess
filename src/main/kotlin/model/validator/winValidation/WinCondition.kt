package model.validator.winValidation

import model.board.Board
import model.enums.Color

interface WinCondition {
    fun isWin(board: Board, opponentColor: Color): Boolean
}