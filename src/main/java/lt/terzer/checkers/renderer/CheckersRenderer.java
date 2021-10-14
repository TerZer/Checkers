package lt.terzer.checkers.renderer;

import lt.terzer.checkers.drawables.BoardMap;
import lt.terzer.checkers.drawables.Checker;
import lt.terzer.checkers.drawables.CheckerType;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheckersRenderer extends BoardRenderer {

    public CheckersRenderer(BoardMap boardMap, Dimension dimension) {
        super(boardMap, dimension);
    }

    @Override
    public void paint(Graphics g, List<Checker> checkers) {
        super.paint(g, checkers);
        Point2D size = boardMap.getSize(translate);
        for(Checker checker : checkers){
            g.setColor(checker.getCheckerType().getColor());
            Point2D dst = new Point2D.Double();
            translate.transform(checker.getPoint(), dst);
            g.fillOval((int) Math.round(dst.getX()), (int) Math.round(dst.getY()), (int) Math.round(size.getX()), (int) Math.round(size.getY()));
        }
    }
}
