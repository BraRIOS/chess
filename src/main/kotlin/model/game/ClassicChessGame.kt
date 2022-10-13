package model.game

import model.Movement
import model.Player
import model.Position
import model.board.ClassicBoard

class ClassicChessGame(player1: Player, player2: Player, board: ClassicBoard) : Game(player1, player2, board) {
    override fun start() {
        TODO("Not yet implemented")
    }

    override fun selectPieceAndShowOptions(position: Position) {
        TODO("Not yet implemented")
    }

    override fun movePiece(movement: Movement) {
        TODO("Not yet implemented")
    }

    override fun nextTurn() {
        TODO("Not yet implemented")
    }

    override fun end() {
        TODO("Not yet implemented")
    }
}
