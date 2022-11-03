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
import model.validator.specialMovementValidator.CastlingValidator
import model.validator.specialMovementValidator.PromotionValidator
import model.validator.winValidation.CheckMateCondition
import model.validator.winValidation.WinValidator

class ClassicGameEngine : GameEngine {
    private var currentPlayerGUI = WHITE
    private var piecesGUI = listOf<ChessPiece>()
    private val board = Board()
    private val validatorProvider = ValidatorProvider()
    private val checkValidator = CheckValidator()
    private lateinit var winValidator:WinValidator
    private val promotionValidator = PromotionValidator()
    private val castlingValidator = CastlingValidator()
    private val modelGUITranslator = ModelGUITranslator()

    override fun init(): InitialState {
        winValidator = WinValidator(listOf(CheckMateCondition()))
        board.setBoard(ClassicBoardShape(), ClassicPositionInitializer(), DefaultCaptureValidator())
        val pieces = board.getAllPieces()
        pieces.forEach { piece ->
            val position = board.getPiecePosition(piece)
            val chessPiece =  modelGUITranslator.fromPieceToChessPiece(piece, position)
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
        else if (toPiece != null && toPiece.color == currentPlayerGUI && fromPiece.pieceId != "king" && toPiece.pieceId != "rook")
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
        val piece = board.getPiece(modelGUITranslator.fromPositionGUItoPosition(move.from))!!
        val pieceEnd = board.getPiece(modelGUITranslator.fromPositionGUItoPosition(move.to))
        val position = board.getPiecePosition(piece)
        var newPosition = modelGUITranslator.fromPositionGUItoPosition(move.to)
        val validator = validatorProvider.getPieceValidator(piece.type)
        val movement = Movement(position, newPosition, piece)

        if(pieceEnd != null && castlingValidator.canCastlingWithRook(board, piece, pieceEnd)) {
            board.castlingWithRook(piece, board, pieceEnd)
            newPosition = board.getPiecePosition(piece)
            val rookPosition = board.getPiecePosition(pieceEnd)
            piecesGUI = piecesGUI
                .filter { it != fromPiece && it != toPiece }
                .plus(fromPiece.copy(position = modelGUITranslator.fromPositionToPositionGUI(newPosition)))
                .plus(toPiece!!.copy(position = modelGUITranslator.fromPositionToPositionGUI(rookPosition)))
            currentPlayerGUI = if (currentPlayerGUI == WHITE) BLACK else WHITE
            return NewGameState(piecesGUI, currentPlayerGUI)
        }
        else if (validator.validateMovement(movement, board)) {

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