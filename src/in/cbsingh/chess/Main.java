package in.cbsingh.chess;

import in.cbsingh.chess.cli.ChessConsole;
import in.cbsingh.chess.ui.ChessUI;

public class Main {
    public static void main(String[] args) {
        // This program runs from the following location and the
        // accompanying database.txt file must be place there.
        //System.out.println(System.getProperty("user.dir"));

        if (args.length > 0) {
            ChessConsole chessConsole = new ChessConsole();
            chessConsole.run();
        } else {
            new ChessUI();
        }
    }
}