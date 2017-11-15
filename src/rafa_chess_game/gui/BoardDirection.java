/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rafa_chess_game.gui;

import java.util.Collections;
import java.util.List;
import rafa_chess_game.gui.Table.TilePanel;

/**
 *
 * @author rafa
 */
public enum BoardDirection {
    
    NORMAL{
        @Override
        public List<TilePanel> traverse(List<TilePanel> boardTiles) {
            return boardTiles;
        }

        @Override
        public BoardDirection opposite() {
            return FLIPPED;
        }
        
    },
    FLIPPED{
        @Override
        public List<TilePanel> traverse(List<TilePanel> boardTiles) {
            List<TilePanel> reversedList = boardTiles;
            Collections.reverse(reversedList);
            return reversedList;
        }

        @Override
        public BoardDirection opposite() {
            return NORMAL;
        }
        
    };
    
    public abstract List<TilePanel> traverse(List<TilePanel> boardTiles); 
    public abstract BoardDirection opposite();
}
