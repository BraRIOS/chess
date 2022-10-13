package model.board.piecePositionInitializer

import model.Position
import model.enums.Color
import model.enums.PieceType
import model.piece.Piece
import java.util.*

class ClassicPositionInitializer : PiecePositionInitializer {
    override fun initializePieces(positions: MutableMap<Position, Optional<Piece>>) {
        for (x in 0..7) {
            positions[Position(x, 1)] = Optional.of(Piece(Color.WHITE, PieceType.PAWN))
            positions[Position(x, 6)] = Optional.of(Piece(Color.BLACK, PieceType.PAWN))
        }
        positions[Position(0, 0)] = Optional.of(Piece(Color.WHITE, PieceType.ROOK))
        positions[Position(7, 0)] = Optional.of(Piece(Color.WHITE, PieceType.ROOK))
        positions[Position(0, 7)] = Optional.of(Piece(Color.BLACK, PieceType.ROOK))
        positions[Position(7, 7)] = Optional.of(Piece(Color.BLACK, PieceType.ROOK))
        positions[Position(1, 0)] = Optional.of(Piece(Color.WHITE, PieceType.KNIGHT))
        positions[Position(6, 0)] = Optional.of(Piece(Color.WHITE, PieceType.KNIGHT))
        positions[Position(1, 7)] = Optional.of(Piece(Color.BLACK, PieceType.KNIGHT))
        positions[Position(6, 7)] = Optional.of(Piece(Color.BLACK, PieceType.KNIGHT))
        positions[Position(2, 0)] = Optional.of(Piece(Color.WHITE, PieceType.BISHOP))
        positions[Position(5, 0)] = Optional.of(Piece(Color.WHITE, PieceType.BISHOP))
        positions[Position(2, 7)] = Optional.of(Piece(Color.BLACK, PieceType.BISHOP))
        positions[Position(5, 7)] = Optional.of(Piece(Color.BLACK, PieceType.BISHOP))
        positions[Position(3, 0)] = Optional.of(Piece(Color.WHITE, PieceType.QUEEN))
        positions[Position(3, 7)] = Optional.of(Piece(Color.BLACK, PieceType.QUEEN))
        positions[Position(4, 0)] = Optional.of(Piece(Color.WHITE, PieceType.KING))
        positions[Position(4, 7)] = Optional.of(Piece(Color.BLACK, PieceType.KING))
    }
}
