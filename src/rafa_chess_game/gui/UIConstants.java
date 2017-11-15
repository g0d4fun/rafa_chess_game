/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rafa_chess_game.gui;

import java.awt.Color;
import java.awt.Dimension;

/**
 *
 * @author rafa
 */
public interface UIConstants {
    
    Dimension FRAME_DIMENSION = new Dimension(600, 600);
    int BOARD_DIM_X = 600;
    int BOARD_DIM_Y = 600;
    Dimension BOARD_DIMENSION = new Dimension(BOARD_DIM_X,BOARD_DIM_Y);
    Dimension TILE_DIMENSION = new Dimension(BOARD_DIM_X / 8,BOARD_DIM_Y / 8);
    
    int PIECE_WIDTH = 60;
    int PIECE_HEIGHT = 60;
    
    Color LIGHT_TILE_COLOR_HEX = Color.decode("#FECD9D");
    Color DARK_TILE_COLOR_HEX = Color.decode("#D18B47");
    Color LIGHT_TILE_COLOR_HEX_ON_CLICK = Color.decode("#CBA47D");
    Color DARK_TILE_COLOR_HEX_ON_CLICK = Color.decode("#A76F38");
    Color LIGHT_TILE_COLOR_HEX_ON_CLICK_CANDIDATE = Color.decode("#E4B88D");
    Color DARK_TILE_COLOR_HEX_ON_CLICK_CANDIDATE = Color.decode("#BC7D3F"); 
    
    String AIM_MOVE = "Aim Move";
    String PATH_IMG_AIM_MOVE = "pictures/chess_aim.png";
    
    // Pieces Pictures - Dark Ones
    String DARK_PAWN = "Dark Pawn";
    String PATH_IMG_DARK_PAWN = "pictures/chess_pdt60.png"; 
    
    String DARK_ROOK = "Dark Rook";
    String PATH_IMG_DARK_ROOK = "pictures/chess_rdt60.png";
    
    String DARK_KNIGHT = "Dark Knight";
    String PATH_IMG_DARK_KNIGHT = "pictures/chess_ndt60.png";
    
    String DARK_BISHOP = "Dark Bishop";
    String PATH_IMG_DARK_BISHOP = "pictures/chess_bdt60.png";
    
    String DARK_KING = "Dark King";
    String PATH_IMG_DARK_KING = "pictures/chess_kdt60.png";
    
    String DARK_QUEEN = "Dark Queen";
    String PATH_IMG_DARK_QUEEN = "pictures/chess_qdt60.png";
    
    // Pieces Pictures - Light ones
    String LIGHT_PAWN = "Light Pawn";
    String PATH_IMG_LIGHT_PAWN = "pictures/chess_plt60.png"; 
    
    String LIGHT_ROOK = "Light Rook";
    String PATH_IMG_LIGHT_ROOK = "pictures/chess_rlt60.png";
    
    String LIGHT_KNIGHT = "Light Knight";
    String PATH_IMG_LIGHT_KNIGHT = "pictures/chess_nlt60.png";
    
    String LIGHT_BISHOP = "Light Bishop";
    String PATH_IMG_LIGHT_BISHOP = "pictures/chess_blt60.png";
    
    String LIGHT_KING = "Light King";
    String PATH_IMG_LIGHT_KING = "pictures/chess_klt60.png";
    
    String LIGHT_QUEEN = "Light Queen";
    String PATH_IMG_LIGHT_QUEEN = "pictures/chess_qlt60.png";
}
