package model.board

import model.Piece
import model.Position
import model.enums.Color
import model.enums.PieceType

class ClassicBoard: Board(HashMap()) {
    init {
        for (i in 0..7) {
            addPiece(Piece(Color.BLACK,PieceType.PAWN), Position(i, 1))
            addPiece(Piece(Color.WHITE, PieceType.PAWN), Position(i, 6))
        }
        addPiece(Piece.ROOK, Position(0, 0))
        addPiece(Piece.ROOK, Position(7, 0))
        addPiece(Piece.ROOK, Position(0, 7))
        addPiece(Piece.ROOK, Position(7, 7))
        addPiece(Piece.KNIGHT, Position(1, 0))
        addPiece(Piece.KNIGHT, Position(6, 0))
        addPiece(Piece.KNIGHT, Position(1, 7))
        addPiece(Piece.KNIGHT, Position(6, 7))
        addPiece(Piece.BISHOP, Position(2, 0))
        addPiece(Piece.BISHOP, Position(5, 0))
        addPiece(Piece.BISHOP, Position(2, 7))
        addPiece(Piece.BISHOP, Position(5, 7))
        addPiece(Piece.QUEEN, Position(3, 0))
        addPiece(Piece.QUEEN, Position(3, 7))
        addPiece(Piece.KING, Position(4, 0))
        addPiece(Piece.KING, Position(4, 7))
    }
}

}
