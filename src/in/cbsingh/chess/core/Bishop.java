package in.cbsingh.chess.core;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
    public Bishop() {
        super();
    }

    public Bishop(Chess.PieceColor color) {
        super(color);
    }

    static Position[] reachablePositions(Chess chess, Position p) {
        //Accessed by Queens, does not have to be public
        //Position[] result = new Position[0]; -> Commeting this line to use arrayList
        List<Position> resultList = new ArrayList<>();

        int[] rankOffsets = {1, -1, 1, -1};
        int[] fileOffsets = {1, 1, -1, -1};

        for (int d = 0; d < 4; d++) {
            int i = p.getRank() + rankOffsets[d];
            int j = p.getFile() + fileOffsets[d];
            while (i >= 0 && i < Chess.BOARD_RANKS
                    && j >= 0 && j < Chess.BOARD_FILES) {
                Position current = Position.generateFromRankAndFile(i, j);

                if (chess.isEmpty(current))
                    //result = Position.appendPositionsToArray(result, current); -> Commeting this line to use arrayList
                    resultList.add(current);
                else {
                    if (chess.getPieceAt(current).getPieceColor()
                            != chess.getPieceAt(p).getPieceColor())
                        //result = Position.appendPositionsToArray(result, current); -> Commeting this line to use arrayList
                        resultList.add(current);
                    break;
                }
                i += rankOffsets[d];
                j += fileOffsets[d];
            }
        }

        return resultList.toArray(new Position[resultList.size()]);
    }

    public String toString() {
        if (this.getPieceColor() == Chess.PieceColor.WHITE)
            return "B";
        else
            return "b";
    }

    public Position[] allDestinations(Chess chess, Position p) {
        return Bishop.reachablePositions(chess, p);
    }
}
