package model.game

import edu.austral.dissis.chess.gui.ChessPiece
import edu.austral.dissis.chess.gui.PlayerColor
import edu.austral.dissis.chess.gui.Position
import model.enums.Color

class ModelToGUI {
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
}