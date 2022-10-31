package model.validator.winValidation

import model.board.Board
import model.enums.Color
import model.validator.CheckValidator

class CheckMateCondition : WinCondition {
    private val checkValidator = CheckValidator()
    override fun isWin(board: Board, opponentColor: Color) =
        checkValidator.isCheck(board, opponentColor) && !checkValidator.canUncheck(board, opponentColor)
}