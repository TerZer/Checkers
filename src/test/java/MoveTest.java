import lt.terzer.checkers.drawables.Checker;
import lt.terzer.checkers.game.Board;
import lt.terzer.checkers.game.GameRules;
import lt.terzer.checkers.game.Move;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MoveTest {

    private Board board;
    private GameRules gameRules;
    private List<Point2D> correctAnswer = Arrays.asList(new Point2D.Double(3, 5), new Point2D.Double(5, 5));

    @BeforeAll
    public void setUp(){
        board = new Board();
        gameRules = new GameRules(board);
    }

    @BeforeEach
    public void reset() {

    }

    @Test
    public void findResult(){
        Checker checker = board.getCheckerAt(new Point2D.Double(4, 6));
        List<Point2D> list = gameRules.getAvailableMoves(checker).stream().map(Move::getPoint).collect(Collectors.toList());
        assert list.equals(correctAnswer);
    }

}
