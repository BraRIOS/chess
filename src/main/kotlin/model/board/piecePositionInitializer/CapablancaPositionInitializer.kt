package model.board.piecePositionInitializer

import model.Piece
import model.Position
import model.enums.Color
import model.enums.PieceType
import java.util.*

class CapablancaPositionInitializer : PiecePositionInitializer {
    override fun initializePieces(positions: MutableMap<Position, Optional<Piece>>) {
        for (x in 0..9) {
            val whitePawnPosition = Position(x, 1)
            val blackPawnPosition = Position(x, 6)
            positions[whitePawnPosition] = Optional.of(Piece(Color.WHITE, PieceType.PAWN, whitePawnPosition))
            positions[blackPawnPosition] = Optional.of(Piece(Color.BLACK, PieceType.PAWN, blackPawnPosition))
        }
        positions[Position(0, 0)] = Optional.of(Piece(Color.WHITE, PieceType.ROOK, Position(0, 0)))
        positions[Position(9, 0)] = Optional.of(Piece(Color.WHITE, PieceType.ROOK, Position(9, 0)))
        positions[Position(0, 7)] = Optional.of(Piece(Color.BLACK, PieceType.ROOK, Position(0, 7)))
        positions[Position(9, 7)] = Optional.of(Piece(Color.BLACK, PieceType.ROOK, Position(9, 7)))
        positions[Position(1, 0)] = Optional.of(Piece(Color.WHITE, PieceType.KNIGHT, Position(1, 0)))
        positions[Position(8, 0)] = Optional.of(Piece(Color.WHITE, PieceType.KNIGHT, Position(8, 0)))
        positions[Position(1, 7)] = Optional.of(Piece(Color.BLACK, PieceType.KNIGHT, Position(1, 7)))
        positions[Position(8, 7)] = Optional.of(Piece(Color.BLACK, PieceType.KNIGHT, Position(8, 7)))
        positions[Position(3, 0)] = Optional.of(Piece(Color.WHITE, PieceType.BISHOP, Position(3, 0)))
        positions[Position(6, 0)] = Optional.of(Piece(Color.WHITE, PieceType.BISHOP, Position(6, 0)))
        positions[Position(3, 7)] = Optional.of(Piece(Color.BLACK, PieceType.BISHOP, Position(3, 7)))
        positions[Position(6, 7)] = Optional.of(Piece(Color.BLACK, PieceType.BISHOP, Position(6, 7)))
        positions[Position(4, 0)] = Optional.of(Piece(Color.WHITE, PieceType.QUEEN, Position(4, 0)))
        positions[Position(4, 7)] = Optional.of(Piece(Color.BLACK, PieceType.QUEEN, Position(4, 7)))
        positions[Position(5, 0)] = Optional.of(Piece(Color.WHITE, PieceType.KING,  Position(5, 0)))
        positions[Position(5, 7)] = Optional.of(Piece(Color.BLACK, PieceType.KING, Position(5, 7)))
        positions[Position(2, 0)] = Optional.of(Piece(Color.WHITE, PieceType.ARCHBISHOP, Position(2, 0)))
        positions[Position(2, 7)] = Optional.of(Piece(Color.BLACK, PieceType.ARCHBISHOP, Position(2, 7)))
        positions[Position(7, 0)] = Optional.of(Piece(Color.WHITE, PieceType.CHANCELLOR, Position(7, 0)))
        positions[Position(7, 7)] = Optional.of(Piece(Color.BLACK, PieceType.CHANCELLOR, Position(7, 7)))
    }

}