package in.cbsingh.chess.ui;

import javax.swing.*;
import java.awt.*;

public class BoardSquare extends JButton {

    static final String HIGLIGHT = "#FFFF99";
    private static final String LIGHT = "#EAF0CE";
    private static final String DARK = "#BBBE64";
    private int x;
    private int y;
    private String color;

    public BoardSquare(boolean isLightColor, int x, int y) {
        this.x = x;
        this.y = y;
        color = isLightColor ? LIGHT : DARK;
        setBackground(Color.decode(color));
        setBorder(BorderFactory.createEmptyBorder());
    }

    int[] getCoordinates() {
        return new int[]{x, y};
    }

    void setPiece(String s) {

        switch (s) {
            case "B":
                this.setIcon(new ImageIcon(this.getClass().getResource("/gfx/BishopW.png")));
                break;
            case "b":
                this.setIcon(new ImageIcon(this.getClass().getResource("/gfx/BishopB.png")));
                break;
            case "K":
                this.setIcon(new ImageIcon(this.getClass().getResource("/gfx/KingW.png")));
                break;
            case "L":
                this.setIcon(new ImageIcon(this.getClass().getResource("/gfx/KingW.png")));
                break;
            case "k":
                this.setIcon(new ImageIcon(this.getClass().getResource("/gfx/KingB.png")));
                break;
            case "l":
                this.setIcon(new ImageIcon(this.getClass().getResource("/gfx/KingB.png")));
                break;
            case "N":
                this.setIcon(new ImageIcon(this.getClass().getResource("/gfx/KnightW.png")));
                break;
            case "n":
                this.setIcon(new ImageIcon(this.getClass().getResource("/gfx/KnightB.png")));
                break;
            case "P":
                this.setIcon(new ImageIcon(this.getClass().getResource("/gfx/PawnW.png")));
                break;
            case "p":
                this.setIcon(new ImageIcon(this.getClass().getResource("/gfx/PawnB.png")));
                break;
            case "Q":
                this.setIcon(new ImageIcon(this.getClass().getResource("/gfx/QueenW.png")));
                break;
            case "q":
                this.setIcon(new ImageIcon(this.getClass().getResource("/gfx/QueenB.png")));
                break;
            case "R":
                this.setIcon(new ImageIcon(this.getClass().getResource("/gfx/RookW.png")));
                break;
            case "r":
                this.setIcon(new ImageIcon(this.getClass().getResource("/gfx/RookB.png")));
                break;
        }
    }

    void setPiece() {
        this.setIcon(null);
    }

    void setHighlight(boolean isHighlighted) {
        if (isHighlighted)
            this.setBackground(Color.decode(HIGLIGHT));
        else
            this.setBackground(Color.decode(color));
    }
}
