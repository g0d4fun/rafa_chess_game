/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rafa_chess_game;

import rafa_chess_game.gui.Table;
import rafa_chess_game.model.board.Board;

/**
 *
 * @author rafa
 */
public class Rafa_chess_game {

    public static void main(String[] args) {
        Board board = Board.createStandardBoard();

        System.out.println(board);

        Table table = new Table();
    }

}
