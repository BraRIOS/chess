package model.game

import edu.austral.dissis.chess.gui.*
import edu.austral.dissis.chess.gui.PlayerColor.BLACK
import edu.austral.dissis.chess.gui.PlayerColor.WHITE
import model.Movement
import model.board.Board
import model.board.boardShapes.ClassicBoardShape
import model.board.piecePositionInitializer.ClassicPositionInitializer
import model.enums.Color
import model.validator.CheckValidator
import model.validator.ValidatorProvider
import model.validator.captureValidator.DefaultCaptureValidator
import java.util.*

class ClassicGameEngine : GameEngine {
    private var currentPlayerGUI = WHITE
    private var piecesGUI = listOf<ChessPiece>()
    private val board = Board()
    private val validatorProvider = ValidatorProvider()
    private val checkValidator = CheckValidator()

    override fun init(): InitialState {
        board.setBoard(ClassicBoardShape(), ClassicPositionInitializer(), DefaultCaptureValidator())
        val pieces = board.getAllPieces()

        pieces.forEach { piece ->
            val position = board.getPiecePosition(piece)
            val color = if (piece.color == Color.WHITE) WHITE else BLACK
            val positionGUI = Position(position.y+1, position.x+1)
            val chessPiece =  ChessPiece(piece.initialPosition.toString(), color, positionGUI,
                piece.type.name.lowercase(Locale.getDefault()))
            piecesGUI = piecesGUI.plus(chessPiece)
        }
        val size = (board.getBoardShape() as ClassicBoardShape).getLimit()
        val boardSize = BoardSize(size+1, size+1)

        return InitialState(boardSize, piecesGUI, WHITE)
    }

    override fun applyMove(move: Move): MoveResult {
        val fromPiece = piecesGUI.find { it.position == move.from }
        val toPiece = piecesGUI.find { it.position == move.to }
        val color = if (currentPlayerGUI == WHITE) Color.WHITE else Color.BLACK

        return if (checkValidator.isCheck(board, color) && !checkValidator.canUncheck(board, color)){
            val winner = if (currentPlayerGUI == WHITE) BLACK else WHITE
            GameOver(winner)
        } else if (fromPiece == null)
            InvalidMove("No piece in (${move.from.row}, ${move.from.column})")
        else if (fromPiece.color != currentPlayerGUI)
            InvalidMove("Piece does not belong to current player")
        else if (toPiece != null && toPiece.color == currentPlayerGUI)
            InvalidMove("You can't capture your own piece in (${move.to.row}, ${move.to.column})")
        else
            movePiece(move,fromPiece, toPiece, color)
    }
    private fun movePiece(
        move: Move,
        fromPiece: ChessPiece,
        toPiece: ChessPiece?,
        color: Color
    ): MoveResult {
        val piece = board.getPiece(model.Position(move.from.column - 1, move.from.row - 1))
        val position = board.getPiecePosition(piece)
        val newPosition = model.Position(move.to.column - 1, move.to.row - 1)
        val validator = validatorProvider.getPieceValidator(piece.type)
        val movement = Movement(position, newPosition, piece)
        if (validator.validateMovement(movement, board)) {
            board.movePiece(movement)

            if (checkValidator.isCheck(board, color)){
                board.undoLastMovement()
                return InvalidMove("You are in check or you tried to move into check")
            }

            piecesGUI = piecesGUI
                .filter { it != fromPiece && it != toPiece }
                .plus(fromPiece.copy(position = move.to))

            currentPlayerGUI = if (currentPlayerGUI == WHITE) BLACK else WHITE

            piecesGUI = piecesGUI.map {
                if ((it.color == WHITE && it.position.row == 8) || it.color == BLACK && it.position.row == 1)
                    it.copy(pieceId = "queen")
                else
                    it
            }
            return NewGameState(piecesGUI, currentPlayerGUI)
        }
        else
            return InvalidMove("Invalid move")
    }
}