/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rafa_chess_game.model;

import java.util.Collection;
import java.util.Collections;
import rafa_chess_game.model.board.Board;
import rafa_chess_game.model.board.Move;
import rafa_chess_game.model.board.Move.MoveStatus;
import rafa_chess_game.model.board.MoveTransition;
import rafa_chess_game.model.board.Tile;
import rafa_chess_game.model.pieces.Piece;

/**
 *
 * @author henri
 */
public class Model {

    protected Board chessBoard;
    protected MoveTransition transition;

    public Model() {

    }

    // Interation Methods
    public Board startNewGame() {
        chessBoard = Board.createStandardBoard();
        return chessBoard;
    }

    /**
     *
     * @param sourceTile
     * @param destinationTile
     * @return DONE, ILLEGAL_MOVE or LEAVES_PLAYER_IN_CHECK
     */
    public MoveStatus makeMove(Tile sourceTile, Tile destinationTile) {
        Move move = Move.MoveFactory.createMove(chessBoard,
                sourceTile.getTileCoordinate(), destinationTile.getTileCoordinate());

        MoveTransition transition = chessBoard.currentPlayer().makeMove(move);

        if (transition.getMoveStatus().isDone()) {
            chessBoard = transition.getToBoard();
        }

        return transition.getMoveStatus();
    }

    /**
     *
     * @param sourceTile
     * @param destinationTile
     * @return DONE, ILLEGAL_MOVE or LEAVES_PLAYER_IN_CHECK
     */
    public MoveStatus makeMove(int sourceTileId, int destinationTileId) {
        if (sourceTileId >= 64 || destinationTileId >= 64
                || sourceTileId < 0 || destinationTileId < 0) {
            return MoveStatus.ILLEGAL_MOVE;
        }

        Tile sourceTile = getTile(sourceTileId);
        Tile destinationTile = getTile(sourceTileId);

        Move move = Move.MoveFactory.createMove(chessBoard,
                sourceTile.getTileCoordinate(), destinationTile.getTileCoordinate());

        MoveTransition transition = chessBoard.currentPlayer().makeMove(move);

        if (transition.getMoveStatus().isDone()) {
            chessBoard = transition.getToBoard();
        }

        return transition.getMoveStatus();
    }

    public Collection<Move> pieceLegalMoves(Piece pieceToMove) {

        if (pieceToMove != null && pieceToMove.getPieceAllegiance()
                == chessBoard.currentPlayer().getAlliance()) {
            return pieceToMove.calculateLegalMoves(chessBoard);

        }
        return Collections.emptyList();
    }

    // Retrieve Data
    public Board getBoard() {
        return chessBoard;
    }

    public Tile getTile(int tileId) {
        return chessBoard.getTile(tileId);
    }

    public Alliance getCurrentPlayerAlliance() {
        return chessBoard.currentPlayer().getAlliance();
    }

    public Alliance getAlliancePieceByTileId(int tileId) {
        return chessBoard.getTile(tileId).getPiece().getPieceAllegiance();
    }
}
