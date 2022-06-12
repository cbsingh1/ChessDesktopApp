package in.cbsingh.chess.core;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {
    private boolean hasMoved;

    public King() {
        this(Chess.PieceColor.WHITE);
    }

    public King(Chess.PieceColor color) {
        this(color, false);
    }

    public King(Chess.PieceColor color, boolean hasMoved) {
        super(color);
        this.hasMoved = hasMoved;
    }

    public boolean getHasMoved() {
        return this.hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public String toString() {
        if (this.getPieceColor() == Chess.PieceColor.WHITE)
            if (this.hasMoved)
                return "L";
            else
                return "K";
        else if (this.hasMoved)
            return "l";
        else
            return "k";
    }

    public Position[] allDestinations(Chess chess, Position p) {
        //preliminary, check mechanism not implemented.
        int[][] pattern = {
                {p.getRank() - 1, p.getFile() - 1},
                {p.getRank(), p.getFile() - 1},
                {p.getRank() + 1, p.getFile() - 1},

                {p.getRank() - 1, p.getFile()},
                {p.getRank() + 1, p.getFile()},

                {p.getRank() - 1, p.getFile() + 1},
                {p.getRank(), p.getFile() + 1},
                {p.getRank() + 1, p.getFile() + 1}
        };

        //Position[] result = new Position[0];  -> Commented this line to use arrayList
        List<Position> resultList = new ArrayList<>();

        for (int i = 0; i < pattern.length; i++) {
            Position potential = Position.generateFromRankAndFile(pattern[i][0],
                    pattern[i][1]);
            if (potential != null && (chess.isEmpty(potential)
                    || chess.getPieceAt(potential).getPieceColor()
                    != chess.getPieceAt(p).getPieceColor()))
                //result = Position.appendPositionsToArray(result, potential); -> Commented this line to use arrayList
                resultList.add(potential);
        }

        return resultList.toArray(new Position[resultList.size()]);
    }

    public King clone() {
        // This method is not really necessary
        return (King) super.clone();
    }
}
