package model.board

import model.Movement
import model.Piece
import model.Position
import model.board.boardShapes.BoardShape
import model.board.piecePositionInitializer.PiecePositionInitializer
import model.enums.Color
import model.enums.PieceType
import model.validator.captureValidator.CaptureValidator
import java.util.*

class Board {
    private var positions = mutableMapOf<Position, Optional<Piece>>()
    private lateinit var boardShape: BoardShape
    private var piecePositionInitializer: PiecePositionInitializer? = null
    private lateinit var captureValidator: CaptureValidator
    private val movementsLog = mutableListOf<(Pair<Movement, Optional<Piece>>)>()

    fun getBoardShape(): BoardShape {
        return boardShape
    }

    fun getPiecePositionInitializer(): PiecePositionInitializer? {
        return piecePositionInitializer
    }

    private fun setBoardShape(boardShape: BoardShape) {
        this.boardShape = boardShape
        positions = boardShape.getShape()
    }

    private fun setPiecePositionInitializer(piecePositionInitializer: PiecePositionInitializer) {
        this.piecePositionInitializer = piecePositionInitializer
        piecePositionInitializer.initializePieces(positions)
    }

    private fun setCaptureValidator(captureValidator: CaptureValidator) {
        this.captureValidator = captureValidator
    }

    fun setBoard(boardShape: BoardShape, piecePositionInitializer: PiecePositionInitializer?, captureValidator: CaptureValidator) {
        setBoardShape(boardShape)
        if (piecePositionInitializer != null ) setPiecePositionInitializer(piecePositionInitializer)
        setCaptureValidator(captureValidator)
        if (positions.keys.any { !boardShape.isInside(it) }) throw Exception("La forma del tablero no es compatible con la posición de las piezas")
    }

    fun movePiece(movement: Movement) {
        val piece = movement.piece
        piece.hasMoved = true
        positions[movement.start] = Optional.empty()
        if (captureValidator.validateCapture(movement, this)) {
            val capturedPiece = positions[movement.end]!!.get()
            positions[movement.end] = Optional.of(piece)
            movementsLog.add(Pair(movement, Optional.of(capturedPiece)))
        } else {
            positions[movement.end] = Optional.of(piece)
            movementsLog.add(Pair(movement, Optional.empty()))
        }
    }

    fun undoLastMovement() {
        val lastMovement = movementsLog.last()
        val piece = lastMovement.first.piece
        positions[lastMovement.first.start] = Optional.of(piece)
        positions[lastMovement.first.end] = lastMovement.second
        movementsLog.remove(lastMovement)
        piece.hasMoved = movementsLog.any { it.first.piece == piece }
    }

    fun getMovementsLog() = movementsLog

    fun getPositions(): MutableMap<Position, Optional<Piece>> {
        return Collections.unmodifiableMap(positions)
    }

    fun isInside(position: Position): Boolean {
        return boardShape.isInside(position)
    }

    fun isOccupiedBySameColor(movement: Movement): Boolean {
        return positions[movement.end]!!.isPresent && positions[movement.end]!!.get().color == movement.piece.color
    }

    fun isOccupiedByDifferentColor(movement: Movement): Boolean {
        return positions[movement.end]!!.isPresent && positions[movement.end]!!.get().color != movement.piece.color
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

    fun getPiece(position: Position): Piece? {
        return positions[position]?.orElse(null)
    }

    fun getPiece(pieceType: PieceType, color: Color): Piece {
        return positions.filter { it.value.isPresent && it.value.get().type == pieceType && it.value.get().color == color }.values.first().get()
    }

    fun getOpponentPieces(color: Color): List<Piece> {
        return positions.filter { it.value.isPresent && it.value.get().color != color }.values.map { it.get() }
    }

    fun getAllyPieces(color: Color): List<Piece> {
        return positions.values.filter { it.isPresent && it.get().color == color }.map { it.get() }
    }

    fun getAllPieces(): List<Piece> {
        return positions.values.filter { it.isPresent }.map { it.get() }
    }

    fun clone(): Board {
        val board = Board()
        board.setBoard(boardShape, piecePositionInitializer, captureValidator)
        board.positions = positions.mapValues {
            if (it.value.isPresent) Optional.of( it.value.get().clone()) else Optional.empty() }.toMutableMap()
        board.movementsLog.addAll(movementsLog)
        return board
    }

    fun promotePiece(piece: Piece) {
        val position = getPiecePosition(piece)
        positions[position] = Optional.of(piece.promote())
    }

    fun castlingWithRook(king: Piece, board: Board, rook: Piece) {
        val rookPosition = board.getPiecePosition(rook)
        val kingPosition = board.getPiecePosition(king)
        val rookMovement = Movement(rookPosition, rookPosition, rook)
        val kingMovement = Movement(kingPosition, kingPosition, king)
        when {
            kingPosition.x < rookPosition.x -> {
                kingMovement.end = Position(kingPosition.x + 2, kingPosition.y)
                rookMovement.end = Position(kingMovement.end.x - 1, rookPosition.y)
            }

            else -> {
                kingMovement.end = Position(kingPosition.x - 2, kingPosition.y)
                rookMovement.end = Position(kingMovement.end.x + 1, rookPosition.y)
            }
        }
        board.movePiece(kingMovement)
        board.movePiece(rookMovement)
    }
}
