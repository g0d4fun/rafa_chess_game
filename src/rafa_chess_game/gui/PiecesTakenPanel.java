/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rafa_chess_game.gui;

import com.google.common.primitives.Ints;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import rafa_chess_game.model.MoveLog;
import rafa_chess_game.model.board.Board;
import rafa_chess_game.model.board.Move;
import rafa_chess_game.model.pieces.Piece;

/**
 *
 * @author henri
 */
public class PiecesTakenPanel extends JPanel {

    private final JPanel northPanel;
    private final JPanel southPanel;

    public static final Color PANEL_COLOR = Color.decode("0xFDFE6");
    private static final Dimension TAKEN_PIECES_DIMENSION = new Dimension(40, 40);
    public static final EtchedBorder PANEL_BORDER = new EtchedBorder(EtchedBorder.RAISED);

    public PiecesTakenPanel() {
        super(new BorderLayout());
        setBackground(Color.decode("0XFDF5E6"));
        this.northPanel = new JPanel(new GridLayout(8, 2));
        this.southPanel = new JPanel(new GridLayout(8, 2));
        this.northPanel.setBackground(PANEL_COLOR);
        this.southPanel.setBackground(PANEL_COLOR);
        this.add(this.northPanel, BorderLayout.NORTH);
        this.add(this.southPanel, BorderLayout.SOUTH);
        setPreferredSize(TAKEN_PIECES_DIMENSION);
    }

    public void redo(final MoveLog moveLog) {
        this.southPanel.removeAll();
        this.northPanel.removeAll();

        List<Piece> whiteTakenPieces = new ArrayList<>();
        List<Piece> blackTakenPieces = new ArrayList<>();

        for (Move move : moveLog.getMoves()) {
            if (move.isAttack()) {
                Piece takenPiece = move.getAttackedPiece();

                if (takenPiece.getPieceAllegiance().isWhite()) {
                    whiteTakenPieces.add(takenPiece);
                } else if (takenPiece.getPieceAllegiance().isBlack()) {
                    blackTakenPieces.add(takenPiece);
                } else {
                    throw new RuntimeException("Should not reach here!");
                }
            }
        }

        Collections.sort(whiteTakenPieces, new Comparator<Piece>() {
            @Override
            public int compare(Piece o1, Piece o2) {
                return Ints.compare(o1.getPieceValue(), o2.getPieceValue());
            }
        });

        Collections.sort(blackTakenPieces, new Comparator<Piece>() {
            @Override
            public int compare(Piece o1, Piece o2) {
                return Ints.compare(o1.getPieceValue(), o2.getPieceValue());
            }
        });

        for (Piece takenPiece : whiteTakenPieces) {
            setUpTilePieceIcon(takenPiece, southPanel);
        }

        for (Piece takenPiece : blackTakenPieces) {
            setUpTilePieceIcon(takenPiece, northPanel);
        }
    }

    private void setUpTilePieceIcon(Piece piece, JPanel panel) {
        panel.removeAll();
        String pieceName = (piece.getPieceAllegiance().isBlack() ? "Dark " : "Light ") + piece.getPieceType().toString();
        //System.out.println("Piece Name:" + pieceName);
        try {
            panel.add(new JLabel(new ImageIcon(Pictures.getPicture(pieceName))));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
