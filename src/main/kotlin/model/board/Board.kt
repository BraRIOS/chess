package model.board

import model.Movement
import model.Piece
import model.Position
import model.board.boardShapes.BoardShape
import model.board.piecePositionInitializer.PiecePositionInitializer
import model.enums.Color
import model.enums.PieceType
import java.util.*

class Board {
    private var positions = mutableMapOf<Position, Optional<Piece>>()
    private lateinit var boardShape: BoardShape
    private lateinit var piecePositionInitializer: PiecePositionInitializer
    private val movements = mutableListOf<Movement>()

    fun getBoardShape(): BoardShape {
        return boardShape
    }

    fun getPiecePositionInitializer(): PiecePositionInitializer {
        return piecePositionInitializer
    }

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
        movements.add(movement)
    }

    fun getMovements() = movements

    fun getPositions(): MutableMap<Position, Optional<Piece>> {
        return Collections.unmodifiableMap(positions)
    }

    fun isInside(position: Position): Boolean {
        return boardShape.isInside(position)
    }

    fun isOccupiedBySameColor(movement: Movement): Boolean {
        return positions[movement.end]!!.isPresent && positions[movement.end]!!.get().color == movement.piece.color
    }

    fun isOccupied(position: Position): Boolean {
        return positions[position]!!.isPresent
    }

    fun getPiecePosition(piece: Piece): Position {
        return positions.filter { it.value.isPresent && it.value.get() == piece }.keys.first()
    }

    fun getPiecePosition(pieceType: PieceType, color: Color): Position {
        return positions.filter { it.value.isPresent && it.value.get().type == pieceType && it.value.get().color == color }.keys.first()
    }

    fun getPiece(position: Position): Piece {
        return positions[position]?.get() ?: throw Exception("No hay pieza en la posición $position")
    }

    fun getPiece(pieceType: PieceType, color: Color): Piece {
        return positions.filter { it.value.isPresent && it.value.get().type == pieceType && it.value.get().color == color }.values.first().get()
    }

    fun getOpponentPieces(color: Color): List<Piece> {
        return positions.filter { it.value.isPresent && it.value.get().color != color }.values.map { it.get() }
    }

    fun clone(): Board {
        val board = Board()
        board.setBoardShape(boardShape)
        board.setPiecePositionInitializer(piecePositionInitializer)
        board.positions = positions.mapValues { Optional.of(it.value.get().clone()) }.toMutableMap()
        board.movements.addAll(movements)
        return board
    }

    fun getAllPieces(): List<Piece> {
        return positions.values.filter { it.isPresent }.map { it.get() }
    }
}
