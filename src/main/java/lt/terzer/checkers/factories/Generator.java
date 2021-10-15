package lt.terzer.checkers.factories;

import lt.terzer.checkers.drawables.Square;

public interface Generator {
    Square generateSquare(int i, int j);
}
