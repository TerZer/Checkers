package lt.terzer.checkers.renderer;

import lt.terzer.checkers.drawables.BoardMap;
import lt.terzer.checkers.drawables.Checker;
import lt.terzer.checkers.drawables.Square;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.util.List;

public class BoardRenderer implements Renderer {

    protected AffineTransform translate;
    protected BoardMap boardMap;

    public BoardRenderer(BoardMap boardMap, Dimension dimension) {
        this.boardMap = boardMap;
        resize(dimension);
    }

    @Override
    public void paint(Graphics g, List<Checker> checkers) {
        Point2D size = boardMap.getSize(translate);
        for(Square square : boardMap.getSquares()){
            Color color = square.getColor();
            if(square.isHighlighted()){
                color = mixColors(color, square.getHighlightColor());
            }
            g.setColor(color);
            Point2D dst = new Point2D.Double();
            translate.transform(square.getPoint(), dst);
            g.fillRect((int) Math.round(dst.getX()), (int) Math.round(dst.getY()), (int) Math.round(size.getX()), (int) Math.round(size.getY()));
        }
    }

    public static Color mixColors(Color... colors) {
        float ratio = 1f / ((float) colors.length);
        int r = 0, g = 0, b = 0, a = 0;
        for (Color color : colors) {
            r += color.getRed() * ratio;
            g += color.getGreen() * ratio;
            b += color.getBlue() * ratio;
            a += color.getAlpha() * ratio;
        }
        return new Color(r, g, b, a);
    }

    @Override
    public void windowResize(Dimension size) {
        resize(size);
    }

    @Override
    public Point2D getPoint2D(Point point) {
        Point2D point2D = new Point2D.Double();
        try {
            translate.inverseTransform(new Point2D.Double(point.getX(), point.getY()), point2D);
        } catch (NoninvertibleTransformException e) {
            return null;
        }
        point2D.setLocation(Math.floor(point2D.getX()), Math.floor(point2D.getY()));
        return point2D;
    }

    private void resize(Dimension size){
        translate = AffineTransform.getScaleInstance(size.width/(double) (BoardMap.BORDER*2+BoardMap.MAP_SIZE),size.height/(double) (BoardMap.BORDER*2+BoardMap.MAP_SIZE));
    }
}
