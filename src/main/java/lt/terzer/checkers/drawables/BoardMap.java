package lt.terzer.checkers.drawables;

import lt.terzer.checkers.factories.SquareFactory;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoardMap {

    public static final int MAP_SIZE = 8;
    public static final int BORDER = 1;
    public static final int CHECKERS_SIZE = 3;

    private final List<Square> squares = new ArrayList<>();

    public BoardMap(){
        generateSquares();
    }

    private void generateSquares(){
        for(int i = 0;i < MAP_SIZE;i++){
            for(int j = 0;j < MAP_SIZE;j++){
                Square square = SquareFactory.createSquare(i, j);
                squares.add(square);
            }
        }
    }

    public List<Square> getSquares() {
        return squares;
    }

    public Point2D getSize(AffineTransform translate) {
        Point2D dst = new Point2D.Double(0, 0);
        translate.transform(new Point2D.Double(1, 1), dst);
        return dst;
    }

    public void clearHighlight(){
        for(Square square : squares){
            square.setHighlighted(false);
        }
    }

    public void setHighlight(List<Point2D> points) {
        for(Point2D point : points){
            setHighlight(point);
        }
    }

    public void setHighlight(Point2D point){
        for(Square square : squares){
            if(square.getPoint().equals(point)){
                square.setHighlighted(true);
                return;
            }
        }
    }
}
