package lt.terzer.checkers.factories;

import lt.terzer.checkers.drawables.Square;

import java.awt.*;
import java.awt.geom.Point2D;

public class WhiteSquareGenerator implements Generator {

    private static final Color WHITE_COLOR = new Color(180, 180, 180, 225);

    @Override
    public Square generateSquare(int i, int j) {
        if((i%2 == 1 && j%2 == 0) || (i%2 == 0 && j%2 == 1)){
            return new Square(new Point2D.Double(i+1, j+1), WHITE_COLOR);
        }
        return null;
    }
}
