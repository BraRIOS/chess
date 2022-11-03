package model.game

import edu.austral.dissis.chess.gui.ChessPiece
import edu.austral.dissis.chess.gui.PlayerColor
import edu.austral.dissis.chess.gui.Position
import model.board.Board
import model.enums.Color

class ModelGUITranslator {
    fun fromPieceToChessPiece(piece: model.Piece, position: model.Position): ChessPiece {
        val color = fromColorToPlayerColor(piece.color)
        val positionGUI = fromPositionToPositionGUI(position)
        return ChessPiece(piece.initialPosition.toString(), color, positionGUI,
            piece.type.name.lowercase())
    }

    fun fromColorToPlayerColor(color: Color): PlayerColor {
        return when (color) {
            Color.WHITE -> PlayerColor.WHITE
            Color.BLACK -> PlayerColor.BLACK
        }
    }

    fun fromPositionToPositionGUI(position: model.Position): Position {
        return Position(position.y + 1, position.x + 1)
    }

    fun fromPositionGUItoPosition(positionGUI: Position): model.Position {
        return model.Position(positionGUI.column - 1, positionGUI.row - 1)
    }

    fun fromBoardPiecesToChessPieces(board: Board): List<ChessPiece> {
        val piecesGUI = mutableListOf<ChessPiece>()
        val pieces = board.getAllPieces()
        pieces.forEach { piece ->
            val position = board.getPiecePosition(piece)
            val chessPiece = fromPieceToChessPiece(piece, position)
            piecesGUI.add(chessPiece)
        }
        return piecesGUI
    }
}