package model.game

import edu.austral.dissis.chess.gui.*
import edu.austral.dissis.chess.gui.PlayerColor.BLACK
import edu.austral.dissis.chess.gui.PlayerColor.WHITE
import model.Movement
import model.board.Board
import model.board.boardShapes.ClassicBoardShape
import model.board.piecePositionInitializer.ClassicPositionInitializer
import model.enums.Color
import model.enums.PieceType
import model.validator.CheckValidator
import model.validator.ValidatorProvider
import model.validator.captureValidator.DefaultCaptureValidator
import model.validator.specialMovementValidator.CastlingValidator
import model.validator.specialMovementValidator.PromotionValidator
import model.validator.winValidation.CheckMateCondition
import model.validator.winValidation.WinValidator
import java.util.*

class ClassicGameEngine : GameEngine {
    private var currentPlayerGUI = WHITE
    private var piecesGUI = listOf<ChessPiece>()
    private val board = Board()
    private val validatorProvider = ValidatorProvider()
    private val checkValidator = CheckValidator()
    private lateinit var winValidator:WinValidator
    private val promotionValidator = PromotionValidator()
    private val castlingValidator = CastlingValidator()

    override fun init(): InitialState {
        winValidator = WinValidator(listOf(CheckMateCondition()))
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

        return if (fromPiece == null)
            InvalidMove("No piece in (${move.from.row}, ${move.from.column})")
        else if (fromPiece.color != currentPlayerGUI)
            InvalidMove("Piece does not belong to current player")
        else if (toPiece != null && toPiece.color == currentPlayerGUI)
            InvalidMove("You can't capture your own piece in (${move.to.row}, ${move.to.column})")
        else
            movePieceAndReturnResult(move,fromPiece, toPiece, color)
    }
    private fun movePieceAndReturnResult(
        move: Move,
        fromPiece: ChessPiece,
        toPiece: ChessPiece?,
        color: Color
    ): MoveResult {
        val piece = board.getPiece(model.Position(move.from.column - 1, move.from.row - 1))!!
        val pieceEnd = board.getPiece(model.Position(move.to.column - 1, move.to.row - 1))
        val position = board.getPiecePosition(piece)
        val newPosition = model.Position(move.to.column - 1, move.to.row - 1)
        val validator = validatorProvider.getPieceValidator(piece.type)
        val movement = Movement(position, newPosition, piece)
        if (validator.validateMovement(movement, board)) {
            if(pieceEnd != null && pieceEnd.type == PieceType.ROOK && piece.type == PieceType.KING &&
                castlingValidator.canCastlingWithRook(movement, board, pieceEnd)){

            }
            board.movePiece(movement)

            if (checkValidator.isCheck(board, color)){
                board.undoLastMovement()
                return InvalidMove("You are in check or you tried to move into check")
            }

            piecesGUI = piecesGUI
                .filter { it != fromPiece && it != toPiece }
                .plus(fromPiece.copy(position = move.to))

            if (promotionValidator.validateMovement(movement, board)) {
                board.promotePiece(piece)
                piecesGUI = piecesGUI.map {
                    if (it.id == piece.initialPosition.toString())
                        it.copy(pieceId = "queen")
                    else
                        it
                }
            }

            val opponentColor = if (color == Color.WHITE) Color.BLACK else Color.WHITE
            //reemplazar por win validator con win conditions
            if (winValidator.isWin(board, opponentColor)) {
                return GameOver(currentPlayerGUI)
            }
            currentPlayerGUI = if (currentPlayerGUI == WHITE) BLACK else WHITE
            return NewGameState(piecesGUI, currentPlayerGUI)
        }
        else
            return InvalidMove("Invalid move")
    }
}