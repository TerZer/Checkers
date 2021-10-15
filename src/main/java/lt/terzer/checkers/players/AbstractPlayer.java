package lt.terzer.checkers.players;

import lt.terzer.checkers.drawables.Checker;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPlayer implements Player {

    protected final List<Checker> checkers = new ArrayList<>();

    @Override
    public void removeCheckers(List<Checker> capturableCheckers) {
        checkers.removeIf(checker -> capturableCheckers.stream().anyMatch(c -> c.getPoint().equals(checker.getPoint())));
    }

    @Override
    public List<Checker> getCheckers() {
        return checkers;
    }
}
