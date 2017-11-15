package rafa_chess_game.model.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import rafa_chess_game.model.Alliance;
import rafa_chess_game.model.board.Board;
import rafa_chess_game.model.board.BoardUtils;
import rafa_chess_game.model.board.Move;
import rafa_chess_game.model.board.Move.MajorAttackMove;
import rafa_chess_game.model.board.Move.MajorMove;
import rafa_chess_game.model.board.Tile;

public final class Queen extends Piece {

    private final static int[] CANDIDATE_MOVE_COORDINATES = { -9, -8, -7, -1, 1,
        7, 8, 9 };

    public Queen(final Alliance alliance, final int piecePosition) {
        super(PieceType.QUEEN, alliance, piecePosition, true);
    }

    public Queen(final Alliance alliance,
                 final int piecePosition,
                 final boolean isFirstMove) {
        super(PieceType.QUEEN, alliance, piecePosition, isFirstMove);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        int candidateDestinationCoordinate;
        for (final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATES) {
            candidateDestinationCoordinate = this.piecePosition;
            while (true) {
                if (isFirstColumnExclusion(currentCandidateOffset, candidateDestinationCoordinate) ||
                    isEightColumnExclusion(currentCandidateOffset, candidateDestinationCoordinate)) {
                    break;
                }
                candidateDestinationCoordinate += currentCandidateOffset;
                if (!BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                    break;
                } else {
                    final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                    if (!candidateDestinationTile.isTileOccupied()) {
                        legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
                    } else {
                        final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                        final Alliance pieceAtDestinationAllegiance = pieceAtDestination.getPieceAllegiance();
                        if (this.pieceAlliance != pieceAtDestinationAllegiance) {
                            legalMoves.add(new MajorAttackMove(board, this, candidateDestinationCoordinate,
                                    pieceAtDestination));
                        }
                        break;
                    }
                }
            }
        }
        return legalMoves;
    }

    @Override
    public int locationBonus() {
        return this.pieceAlliance.queenBonus(this.piecePosition);
    }

    @Override
    public Queen movePiece(final Move move) {
        return PieceUtils.INSTANCE.getMovedQueen(move.getMovedPiece().getPieceAllegiance(), move.getDestinationCoordinate());
    }

    @Override
    public String toString() {
        return this.pieceType.toString();
    }

    private static boolean isFirstColumnExclusion(final int currentPosition,
                                                  final int candidatePosition) {
        return BoardUtils.INSTANCE.FIRST_COLUMN.get(candidatePosition) && ((currentPosition == -9)
                || (currentPosition == -1) || (currentPosition == 7));
    }

    private static boolean isEightColumnExclusion(final int currentPosition,
                                                  final int candidatePosition) {
        return BoardUtils.INSTANCE.EIGHTH_COLUMN.get(candidatePosition) && ((currentPosition == -7)
                || (currentPosition == 1) || (currentPosition == 9));
    }

}