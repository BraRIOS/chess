package model.game

import model.Movement
import model.Player
import model.Position
import model.board.Board

abstract class Game(players: ArrayList<Player>, board: Board) {
    protected var turn: Int = 0
    protected var winner: Player? = null
    protected lateinit var currentPlayer: Player
    protected val moves: MutableList<Movement> = mutableListOf()
    protected var check: Boolean = false
    protected var checkMate: Boolean = false
    protected var staleMate: Boolean = false
    protected var draw: Boolean = false

    abstract fun start()
    abstract fun movePiece(movement: Movement)
    abstract fun nextTurn()
    abstract fun end()
}
