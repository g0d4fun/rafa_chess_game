/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rafa_chess_game.model;

import java.util.Collection;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import rafa_chess_game.model.board.Board;
import rafa_chess_game.model.board.Move;
import rafa_chess_game.model.board.Move.MoveStatus;
import rafa_chess_game.model.board.Tile;
import rafa_chess_game.model.pieces.Piece;

/**
 *
 * @author henri
 */
public class Model implements Observable {

    protected ModelChess modelChess;

    public Model() {
        modelChess = new ModelChess();
    }

    // Interation Methods
    public Board startNewGame() {
        return modelChess.startNewGame();
    }

    /**
     *
     * @param sourceTile
     * @param destinationTile
     * @return DONE, ILLEGAL_MOVE or LEAVES_PLAYER_IN_CHECK
     */
    public MoveStatus makeMove(Tile sourceTile, Tile destinationTile) {
        return modelChess.makeMove(sourceTile, destinationTile);
    }

    /**
     *
     * @param sourceTile
     * @param destinationTile
     * @return DONE, ILLEGAL_MOVE or LEAVES_PLAYER_IN_CHECK
     */
    public MoveStatus makeMove(int sourceTileId, int destinationTileId) {
        return modelChess.makeMove(sourceTileId, destinationTileId);
    }

    public Collection<Move> pieceLegalMoves(Piece pieceToMove) {
        return modelChess.pieceLegalMoves(pieceToMove);
    }

    // Retrieve Data
    public Board getBoard() {
        return modelChess.getBoard();
    }

    public Tile getTile(int tileId) {
        return modelChess.getTile(tileId);
    }

    public Alliance getCurrentPlayerAlliance() {
        return modelChess.getCurrentPlayerAlliance();
    }

    public Alliance getAlliancePieceByTileId(int tileId) {
        return modelChess.getAlliancePieceByTileId(tileId);
    }

    public MoveLog getMoveLog() {
        return modelChess.getMoveLog();
    }

    public Alliance getCurrentPlayer(){
        return modelChess.getCurrentPlayer();
    }
    
    @Override
    public void addListener(InvalidationListener listener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
