package lt.terzer.checkers.drawables;

import java.awt.geom.Point2D;

public class Checker {

    private CheckerType checkerType;
    private boolean hasCrown;
    private int x, y;

    public Checker(int x, int y, CheckerType checkerType){
        this.x = x;
        this.y = y;
        this.checkerType = checkerType;
    }

    public Point2D getPoint() {
        return new Point2D.Double(x, y);
    }

    public CheckerType getCheckerType() {
        return checkerType;
    }
}
