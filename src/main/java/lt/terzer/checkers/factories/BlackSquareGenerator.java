package lt.terzer.checkers.factories;

import lt.terzer.checkers.drawables.Square;

import java.awt.*;
import java.awt.geom.Point2D;

public class BlackSquareGenerator implements Generator {

    private static final Color BLACK_COLOR = new Color(40, 40, 40, 225);

    @Override
    public Square generateSquare(int i, int j) {
        if((i%2 == 0 && j%2 == 0) || (i%2 == 1 && j%2 == 1)){
            return new Square(new Point2D.Double(i+1, j+1), BLACK_COLOR);
        }
        return null;
    }
}
