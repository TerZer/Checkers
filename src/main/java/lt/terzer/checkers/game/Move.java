package lt.terzer.checkers.game;

import lt.terzer.checkers.drawables.Checker;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Move {
    
    private final Point2D point;
    private List<Checker> capturableCheckers = new ArrayList<>();

    public Move(Point2D point, List<Checker> capturableCheckers) {
        this.point = point;
        this.capturableCheckers = capturableCheckers;
    }

    public Move(Point2D point) {
        this.point = point;
    }

    public Point2D getPoint() {
        return point;
    }

    public List<Checker> getCapturableCheckers() {
        return capturableCheckers;
    }
}
