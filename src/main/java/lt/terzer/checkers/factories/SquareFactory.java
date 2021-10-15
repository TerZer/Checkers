package lt.terzer.checkers.factories;

import lt.terzer.checkers.drawables.Square;

import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.List;

public class SquareFactory {

    private static final List<Generator> generators = Arrays.asList(new BlackSquareGenerator(), new WhiteSquareGenerator());

    public static Square createSquare(int i, int j) {
        for(Generator generator : generators){
            Square square = generator.generateSquare(i, j);
            if(square != null){
                return square;
            }
        }
        return null;
    }

}
