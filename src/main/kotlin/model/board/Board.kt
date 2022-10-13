package model.board

import model.Movement
import model.Position
import model.board.boardShapes.BoardShape
import model.board.piecePositionInitializer.PiecePositionInitializer
import model.piece.Piece
import java.util.Optional

class Board {
    private var positions = mutableMapOf<Position, Optional<Piece>>()
    private lateinit var boardShape: BoardShape
    private lateinit var piecePositionInitializer: PiecePositionInitializer

    private fun setBoardShape(boardShape: BoardShape) {
        this.boardShape = boardShape
        positions = boardShape.getBoardShape()
    }

    private fun setPiecePositionInitializer(piecePositionInitializer: PiecePositionInitializer) {
        this.piecePositionInitializer = piecePositionInitializer
        piecePositionInitializer.initializePieces(positions)
    }

    fun initBoard(boardShape: BoardShape, piecePositionInitializer: PiecePositionInitializer) {
        setBoardShape(boardShape)
        setPiecePositionInitializer(piecePositionInitializer)
        if (positions.keys.any { !boardShape.isInside(it) }) throw Exception("La forma del tablero no es compatible con la posición de las piezas")
    }

    fun movePiece(movement: Movement) {
        val piece = positions[movement.start]!!.get()
        positions[movement.start] = Optional.empty()
        positions[movement.end] = Optional.of(piece)
    }

    fun getPositions(): MutableMap<Position, Optional<Piece>> {
        return positions
    }

    fun isInside(position: Position): Boolean {
        return boardShape.isInside(position)
    }

    fun isOccupiedBySameColor(position: Position): Boolean {
        return positions[position]!!.isPresent && positions[position]!!.get().color == positions[position]!!.get().color
    }
}
