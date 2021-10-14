package lt.terzer.checkers.renderer;

import lt.terzer.checkers.drawables.Checker;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;

public interface Renderer {
    void paint(Graphics g, List<Checker> checkers);
    void windowResize(Dimension size);
    Point2D getPoint2D(Point point);
}
