package model.validator.winValidation

import model.board.Board
import model.enums.Color
import model.validator.CheckValidator

class TreeCheckCondition : WinCondition{
    private var checkCount=0
    private val CheckValidator=CheckValidator()

    override fun isWin(board: Board, opponentColor: Color): Boolean {
        if(CheckValidator.isCheck(board, opponentColor)){
            checkCount++
            if(checkCount==3){
                return true
            }
        }
        return false
    }
}