package in.cbsingh.chess.ui;

import in.cbsingh.chess.core.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChessUI extends JFrame {
    Chess chess;
    BoardSquare[][] boardSquares = new BoardSquare[8][8];
    Position originPosition;
    BoardSquare clickedSquare;
    private JPanel panel;

    public ChessUI() throws HeadlessException {
        try {
            chess = new Chess("rnbqkbnr" +
                    "pppppppp" +
                    "--------" +
                    "--------" +
                    "--------" +
                    "--------" +
                    "PPPPPPPP" +
                    "RNBQKBNR",
                    Chess.PieceColor.WHITE);
            setSize(600, 600);
            setTitle("Chess Game");
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLayout(new BorderLayout());
            panel = new JPanel();
            this.panel.setLayout(new GridLayout(8, 8));
            int squareNo, row;
            boolean colorResult;

            for (int i = 0; i < Chess.BOARD_RANKS; i++) {
                for (int j = 0; j < Chess.BOARD_FILES; j++) {
                    squareNo = (i) * 8 + (j);
                    row = (squareNo / 8) % 2;

                    if (row == 0)
                        colorResult = squareNo % 2 == 0;
                    else
                        colorResult = !(squareNo % 2 == 0);

                    boardSquares[i][j] = new BoardSquare(colorResult, i, j);

                    boardSquares[i][j].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            clickedSquare = (BoardSquare) e.getSource();
                            boardClicked(clickedSquare.getCoordinates());
                        }
                    });
                    boardSquares[i][j].setPiece(String.valueOf(chess.getBoard()[i][j]));
                    panel.add(boardSquares[i][j]);
                }
            }
            panel.setSize(500, 500);
            updatePiece();
            add(panel, BorderLayout.CENTER);
            setVisible(true);
        } catch (IllegalArrangementException e) {
        }
    }

    private void boardClicked(int[] coordinates) {

        int x = coordinates[0], y = coordinates[1];

        if (isHighlightedSquare(x, y)) { //If 1st Click is already made

            Position destinationPosition = Position.generateFromRankAndFile(x, y);
            Move move = new Move(originPosition, destinationPosition);
            chess.performMove(move);
            updatePiece();
            originPosition = null;
            resetHighlightedSqaure();
        } else { //No click made yet for move
            originPosition = Position.generateFromRankAndFile(x, y);

            Piece piece = chess.getPieceAt(originPosition);
            if (piece.getPieceColor() == chess.getTurn()) {
                Position[] allMoves = piece.allDestinations(chess, originPosition);
                resetHighlightedSqaure();
                for (Position p : allMoves) {
                    boardSquares[p.getRank()][p.getFile()].setHighlight(true);
                    boardSquares[p.getRank()][p.getFile()].setBorder(BorderFactory.createLineBorder(Color.lightGray, 1));
                }
            }
        }
    }

    private void resetHighlightedSqaure() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (isHighlightedSquare(i, j)) {
                    boardSquares[i][j].setHighlight(false);
                    boardSquares[i][j].setBorder(BorderFactory.createEmptyBorder());
                }
            }
        }
    }

    private boolean isHighlightedSquare(int i, int j) {
        return boardSquares[i][j].getBackground().equals(Color.decode(BoardSquare.HIGLIGHT));
    }

    public void updatePiece() {
        for (int i = 0; i < Chess.BOARD_RANKS; i++) {
            for (int j = 0; j < Chess.BOARD_FILES; j++) {
                if (chess.getBoard()[i][j] != null) {
                    boardSquares[i][j].setPiece(String.valueOf(chess.getBoard()[i][j]));
                } else {
                    boardSquares[i][j].setPiece();
                }
            }
        }
    }
}