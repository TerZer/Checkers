import lt.terzer.checkers.drawables.Checker;
import lt.terzer.checkers.game.Board;
import lt.terzer.checkers.game.GameRules;
import lt.terzer.checkers.game.Move;
import lt.terzer.checkers.renderer.CheckersRenderer;
import lt.terzer.checkers.renderer.Renderer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RenderMatrixTest {

    private Renderer renderer;

    @BeforeAll
    public void setUp(){
        renderer = new CheckersRenderer(new Board().getBoardMap(), new Dimension(800, 800));
    }

    @BeforeEach
    public void reset() {

    }

    @Test
    public void findResult(){
        Point2D point2D = renderer.getPoint2D(new Point(0, 0));
        assert point2D.equals(new Point2D.Double(0, 0));
        point2D = renderer.getPoint2D(new Point(800, 800));
        assert point2D.equals(new Point2D.Double(10, 10));
    }

}
