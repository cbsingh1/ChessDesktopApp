package in.cbsingh.chess.core;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    public Pawn() {
        this(Chess.PieceColor.WHITE);
    }

    public Pawn(Chess.PieceColor color) {
        super(color);
    }

    public String toString() {
        if (this.getPieceColor() == Chess.PieceColor.WHITE)
            return "P";
        else
            return "p";
    }

    public Position[] allDestinations(Chess chess, Position p) {
        //Position[] result = new Position[0]; -> Commented this to use arrayList
        List<Position> resultList = new ArrayList<>();
        Chess.PieceColor myColor = chess.getPieceAt(p).getPieceColor();

        Position front = null, jump = null, left = null, right = null;

        if (myColor == Chess.PieceColor.WHITE) {
            //upward movements.
            front = Position.generateFromRankAndFile(p.getRank() - 1, p.getFile());
            if (p.getRank() == Chess.WHITE_PAWN_STARTING_RANK)
                jump = Position.generateFromRankAndFile(Chess.WHITE_PAWN_STARTING_RANK - 2, p.getFile());
            left = Position.generateFromRankAndFile(p.getRank() - 1, p.getFile() - 1);
            right = Position.generateFromRankAndFile(p.getRank() - 1, p.getFile() + 1);
        } else {
            //downward movements.
            front = Position.generateFromRankAndFile(p.getRank() + 1, p.getFile());
            if (p.getRank() == Chess.BLACK_PAWN_STARTING_RANK)
                jump = Position.generateFromRankAndFile(Chess.BLACK_PAWN_STARTING_RANK + 2, p.getFile());
            left = Position.generateFromRankAndFile(p.getRank() + 1, p.getFile() - 1);
            right = Position.generateFromRankAndFile(p.getRank() + 1, p.getFile() + 1);
        }

        if (front != null && chess.isEmpty(front))
            //result = Position.appendPositionsToArray(result, front); -> Commented this to use arrayList
            resultList.add(front);

        if (left != null && chess.getPieceAt(left) != null
                && chess.getPieceAt(left).getPieceColor() != this.getPieceColor())
            //result = Position.appendPositionsToArray(result, left); -> Commented this to use arrayList
            resultList.add(left);

        if (right != null && chess.getPieceAt(right) != null
                && chess.getPieceAt(right).getPieceColor() != this.getPieceColor())
            //result = Position.appendPositionsToArray(result, right); -> Commented this to use arrayList
            resultList.add(right);

        if (front != null && chess.isEmpty(front) && jump != null && chess.isEmpty(jump))
            //result = Position.appendPositionsToArray(result, jump); -> Commented this to use arrayList
            resultList.add(jump);

        return resultList.toArray(new Position[resultList.size()]);
    }
}
