package lt.terzer.checkers.drawables;

import java.awt.*;
import java.awt.geom.Point2D;

public class Square {

    private static final Color HIGHLIGHT_COLOR = new Color(7, 250, 24, 70);

    private Point2D point;
    private Color color;
    private boolean highlighted;

    public Square(Point2D point, Color color) {
        this.point = point;
        this.color = color;
    }

    public Point2D getPoint() {
        return point;
    }

    public Color getColor() {
        return color;
    }

    public void setHighlighted(boolean bool){
        this.highlighted = bool;
    }

    public boolean isHighlighted(){
        return highlighted;
    }

    public Color getHighlightColor(){
        return HIGHLIGHT_COLOR;
    }
}
