package lt.terzer.checkers.drawables;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoardMap {

    public static final int MAP_SIZE = 8;
    public static final int BORDER = 1;
    public static final int CHECKERS_SIZE = 3;

    private final List<Point2D> blackSquares = new ArrayList<>();
    private final List<Point2D> whiteSquares = new ArrayList<>();
    private final List<Point2D> highlightedSquares = new ArrayList<>();

    public BoardMap(){
        generateSquares();
    }

    //TODO replace ifs with something else
    private void generateSquares(){
        for(int i = 0;i < MAP_SIZE;i++){
            for(int j = 0;j < MAP_SIZE;j++){
                if(i%2 == 0 && j%2 == 0){
                    blackSquares.add(new Point2D.Double(i+1, j+1));
                }
                if(i%2 == 1 && j%2 == 1){
                    blackSquares.add(new Point2D.Double(i+1, j+1));
                }
                if(i%2 == 1 && j%2 == 0){
                    whiteSquares.add(new Point2D.Double(i+1, j+1));
                }
                if(i%2 == 0 && j%2 == 1){
                    whiteSquares.add(new Point2D.Double(i+1, j+1));
                }
            }
        }
    }

    public List<Point2D> getBlackSquares() {
        return blackSquares;
    }

    public List<Point2D> getWhiteSquares() {
        return whiteSquares;
    }

    public List<Point2D> getHighlightedSquares() {
        return highlightedSquares;
    }

    public Point2D getSize(AffineTransform translate) {
        Point2D dst = new Point2D.Double(0, 0);
        translate.transform(new Point2D.Double(1, 1), dst);
        return dst;
    }

    public void clearHighlight(){
        highlightedSquares.clear();
    }

    public void setHighlight(Point2D... point) {
        setHighlight(Arrays.asList(point));
    }

    public void setHighlight(List<Point2D> point) {
        highlightedSquares.addAll(point);
    }

    public List<Point2D> getAvailablePoints(Checker checker) {
        Point2D pt1 = new Point2D.Double(checker.getPoint().getX()-1, checker.getPoint().getY()-1);
        Point2D pt2 = new Point2D.Double(checker.getPoint().getX()+1, checker.getPoint().getY()-1);
        return checkBounds(pt1, pt2);
    }

    private List<Point2D> checkBounds(Point2D... points){
        List<Point2D> pts = new ArrayList<>();
        for(Point2D point : points){
            if(point.getX() < 1 || point.getX() > MAP_SIZE){
                continue;
            }
            if(point.getY() < 1 || point.getY() > MAP_SIZE){
                continue;
            }
            pts.add(point);
        }
        return pts;
    }
}
