package in.cbsingh.chess.core;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {
    public Queen() {
        super();
    }

    public Queen(Chess.PieceColor color) {
        super(color);
    }

    public String toString() {
        if (this.getPieceColor() == Chess.PieceColor.WHITE)
            return "Q";
        else
            return "q";
    }

    //Changed this method to use ArrayList
    public Position[] allDestinations(Chess chess, Position p) {
        List<Position> resultList = new ArrayList<>();
        Position[] rookReachablePositions = Rook.reachablePositions(chess, p);
        Position[] bishopReachablePositions = Bishop.reachablePositions(chess, p);
        resultList.addAll(List.of(rookReachablePositions));
        resultList.addAll(List.of(bishopReachablePositions));
        return resultList.toArray(new Position[resultList.size()]);
    }
}
