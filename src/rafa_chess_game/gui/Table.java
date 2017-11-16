/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rafa_chess_game.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import rafa_chess_game.model.Model;
import rafa_chess_game.model.board.Board;
import rafa_chess_game.model.board.BoardUtils;
import rafa_chess_game.model.board.Move;
import rafa_chess_game.model.board.MoveTransition;
import rafa_chess_game.model.board.Tile;
import rafa_chess_game.model.pieces.Piece;

/**
 *
 * @author rafa
 */
public class Table implements UIConstants {

    public Model model;
    private JFrame gameFrame;
    protected BoardPanel boardPanel;

    private Tile sourceTile;
    private Tile destinationTile;
    private Piece humanMovedPiece;
    private boolean highlighLegalMoves;
    private BoardDirection boardDirection; // Flip Board

    public Table() {
        setUpLookAndFeel();
        model = new Model();
        this.gameFrame = new JFrame("Chess Game");
        JMenuBar tableMenuBar = setUpMenuBar();

        this.gameFrame.setJMenuBar(tableMenuBar);
        this.gameFrame.setSize(FRAME_DIMENSION);

        model.startNewGame();

        this.boardPanel = new BoardPanel(BOARD_DIMENSION);
        this.gameFrame.add(this.boardPanel, BorderLayout.CENTER);

        this.gameFrame.setResizable(false);
        this.gameFrame.setVisible(true);

        this.boardDirection = BoardDirection.NORMAL;
        this.highlighLegalMoves = true;
    }

    private JMenuBar setUpMenuBar() {

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createFileMenu());
        menuBar.add(createPreferencesMenu());
        return menuBar;
    }

    private JMenu createPreferencesMenu() {
        JMenu preferencesMenu = new JMenu("Preferences");

        JMenuItem flipBoardMenuItem = new JMenuItem("Flip Board");
        flipBoardMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boardDirection = boardDirection.opposite();
                boardPanel.drawBoard(model.getBoard());
            }
        });
        preferencesMenu.add(flipBoardMenuItem); 
        preferencesMenu.addSeparator();
        
        JCheckBoxMenuItem legalMovesHighlightCheckBox = new JCheckBoxMenuItem("Highligh Legal Moves", false);
        legalMovesHighlightCheckBox.setSelected(true);
        legalMovesHighlightCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                highlighLegalMoves = legalMovesHighlightCheckBox.isSelected();
            }
        });
        preferencesMenu.add(legalMovesHighlightCheckBox);
        return preferencesMenu;
    }

    private JMenu createFileMenu() {
        JMenu fileMenu = new JMenu("File");

        JMenuItem openPGN = new JMenuItem("Load PGN File...");
        openPGN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Open up a PGN file!");
            }
        });
        fileMenu.add(openPGN);

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(exitMenuItem);

        return fileMenu;
    }

    private void setUpLookAndFeel() {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }
    }

    public class BoardPanel extends JPanel {

        List<TilePanel> boardTiles;

        public BoardPanel(Dimension boardDimension) {
            super(new GridLayout(8, 8));
            setUpTiles();

            //setLocation(0, 200);
            setSize(boardDimension);
            //setPreferredSize(boardDimension);
            validate();
        }

        public void setUpTiles() {
            this.boardTiles = new ArrayList<>();

            for (int i = 0; i < BoardUtils.NUM_TILES; i++) {
                int column = i / 8;
                int row = i - (column * 8);
                TilePanel tilePanel;
                if ((row % 2) == (column % 2)) { // Light Color
                    tilePanel = new TilePanel(this, i, false, model.getBoard());
                } else { // Dark Color
                    tilePanel = new TilePanel(this, i, true, model.getBoard());
                }

                this.boardTiles.add(tilePanel);
                add(tilePanel);
            }
        }

        public void drawBoard(Board board) {
            removeAll();
            for (TilePanel tilePanel : boardDirection.traverse(boardTiles)) {
                tilePanel.drawTile(board);
                add(tilePanel);
            }
            validate();
            repaint();
        }
    }

    public class TilePanel extends JPanel {

        private final int tileId;
        private final boolean isDarkTile;

        public TilePanel(BoardPanel boardPanel, int tileId, boolean isDarkTile, Board chessBoard) {
            super(new GridBagLayout());

            this.tileId = tileId;
            this.isDarkTile = isDarkTile;
            //setPreferredSize(TILE_DIMENSION);
            setUpTileColor(this.isDarkTile);
            setUpTilePieceIcon(chessBoard);
            setUpListeners();
            validate();
        }

        // Getting from login on VIEW
        private void setUpTilePieceIcon(final Board board) {
            this.removeAll();
            if (board.getTile(this.tileId).isTileOccupied()) {
                Piece piece = board.getTile(tileId).getPiece();
                String pieceName = (piece.getPieceAllegiance().isBlack()? "Dark " : "Light ") + piece.getPieceType().toString();
                //System.out.println("Piece Name:" + pieceName);
                try {
                    add(new JLabel(new ImageIcon(Pictures.getPicture(pieceName))));
                } catch (Exception e) {
                    //System.out.println("aaa");
                }
            }
        }

        void drawTile(final Board board) {
            setUpTileColor(isDarkTile);
            setUpTilePieceIcon(board);
//            highlightTileBorder(board);
            highlightLegals();
//            highlightAIMove();
            validate();
            repaint();
        }

        private void setUpTileColor(boolean isDarkTile) {
            if (sourceTile != null) {
                if (isDarkTile && tileId == sourceTile.getTileCoordinate() &&
                        model.getCurrentPlayerAlliance() == model.getAlliancePieceByTileId(tileId)) {
                    setBackground(DARK_TILE_COLOR_HEX_ON_CLICK);
                } else if (!isDarkTile && tileId == sourceTile.getTileCoordinate() &&
                        model.getCurrentPlayerAlliance() == model.getAlliancePieceByTileId(tileId)) {
                    setBackground(LIGHT_TILE_COLOR_HEX_ON_CLICK);
                }
                return;
            }
            if (isDarkTile) {
                setBackground(DARK_TILE_COLOR_HEX);
            } else {
                setBackground(LIGHT_TILE_COLOR_HEX);
            }
        }

        private void setUpListeners() {
            this.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("HERE");
                    if (SwingUtilities.isRightMouseButton(e)) {
                        System.out.println("HERE RIGHT");
                        sourceTile = null;
                        destinationTile = null;
                        humanMovedPiece = null;
                    } else if (SwingUtilities.isLeftMouseButton(e)) {
                        System.out.println("HERE LEFT");
                        if (sourceTile == null) {
                            // firstClick
                            sourceTile = model.getTile(tileId);
                            humanMovedPiece = sourceTile.getPiece();
                            if (humanMovedPiece == null) {
                                sourceTile = null;
                            }
                        } else {
                            // secondClick 
                            destinationTile = model.getTile(tileId);
                            System.out.println("Move Status: " + model.makeMove(sourceTile, destinationTile));
                            sourceTile = null;
                            destinationTile = null;
                            humanMovedPiece = null;
                        }
                    }

                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            boardPanel.drawBoard(model.getBoard());
                        }
                    });

                    validate();
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
        }

        /*
        private void highlightAIMove() {

            if(computerMove != null) {
                if(this.tileId == computerMove.getCurrentCoordinate()) {
                    setBackground(Color.pink);
                } else if(this.tileId == computerMove.getDestinationCoordinate()) {
                    setBackground(Color.red);
                }
            }
        }
        */
        private void highlightTileBorder(final Board board) {

            if(humanMovedPiece != null &&
               humanMovedPiece.getPieceAllegiance() == board.currentPlayer().getAlliance() &&
               humanMovedPiece.getPiecePosition() == this.tileId) {
                setBorder(BorderFactory.createLineBorder(Color.cyan));
            } else {
                setBorder(BorderFactory.createLineBorder(Color.GRAY));
            }

        } 
        
        private void highlightLegals() {
            if(highlighLegalMoves){
                for(Move move : model.pieceLegalMoves(humanMovedPiece)){
                    if(move.getDestinationCoordinate() == this.tileId){
                        add(new JLabel(new ImageIcon(Pictures.getPicture(AIM_MOVE))));
                    }
                }
            }
        }
        
    }
}
