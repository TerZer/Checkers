package lt.terzer.checkers.drawables;

import java.awt.geom.Point2D;

public class Checker {

    private final CheckerType checkerType;
    private Point2D point;

    public Checker(int x, int y, CheckerType checkerType){
        point = new Point2D.Double(x, y);
        this.checkerType = checkerType;
    }

    public Point2D getPoint() {
        return point;
    }

    public CheckerType getCheckerType() {
        return checkerType;
    }

    public void move(Point2D point) {
        this.point = point;
    }
}
