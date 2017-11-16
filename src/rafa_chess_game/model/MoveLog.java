/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rafa_chess_game.model;

import java.util.ArrayList;
import java.util.List;
import rafa_chess_game.model.board.Move;

/**
 *
 * @author henri
 */
public class MoveLog {
    
    private List<Move> moves;

    public MoveLog() {
        moves = new ArrayList<>();
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void addMoves(Move move) {
        moves.add(move);
    }
    
    public int size(){
        return moves.size();
    }
    
    public void clear(){
        moves.clear();
    }
    
    public void removeMove(int index){
        moves.remove(index);
    }
    
    public void removeMove(Move index){
        moves.remove(index);
    }
}
