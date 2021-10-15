package lt.terzer.checkers.game;

import lt.terzer.checkers.drawables.BoardMap;
import lt.terzer.checkers.drawables.Checker;
import lt.terzer.checkers.drawables.CheckerType;

import java.awt.geom.Point2D;
import java.util.*;
import java.util.stream.Collectors;

public record GameRules(Board board) {

    public List<Move> getAvailableMoves(Checker checker) {
        if (canBeMoved(checker)) {
            if (!canCapture(checker)) {
                List<Point2D> points = getMovePoints(checker);
                points.removeIf(point2D -> getCheckerPoint(board.getCheckers(), point2D) != null);
                return points.stream().map(Move::new).collect(Collectors.toList());
            } else {
                List<Point2D> movePoints = getCaptureMovePoints(checker.getPoint());
                return calculateMoves(checker.getPoint(), movePoints, new ArrayList<>(), null);
            }
        }
        return new ArrayList<>();
    }

    private boolean canBeMoved(Checker checker) {
        List<Checker> allyCheckers = board.getCurrentPlayer().getCheckers();
        if (!canCapture(checker)) {
            for (Checker c : allyCheckers) {
                if (canCapture(c)) {
                    return false;
                }
            }
        }
        return true;
    }

    private List<Move> calculateMoves(Point2D startPosition, List<Point2D> movePoints,
                                      List<Point2D> visited, List<Checker> capturedCheckers) {
        List<Checker> enemyCheckers = board.getSecondPlayer().getCheckers();
        List<Move> moves = new ArrayList<>();
        for (Checker enemy : enemyCheckers) {
            Point2D point = getEqualPoint(movePoints, enemy.getPoint());
            if (point == null)
                continue;

            Point2D vector = getVector(startPosition, point);
            Point2D movePoint = new Point2D.Double(point.getX() + vector.getX(), point.getY() + vector.getY());
            if (board.getCheckers().stream().anyMatch(checker -> checker.getPoint().equals(movePoint))) {
                continue;
            }
            if (checkBounds(movePoint).isEmpty()) {
                continue;
            }
            if (visited.contains(movePoint)) {
                continue;
            }
            List<Checker> captured;
            if (capturedCheckers == null) {
                captured = new ArrayList<>();
            } else {
                captured = new ArrayList<>(capturedCheckers);
            }
            captured.add(enemy);
            visited.add(startPosition);
            moves.addAll(calculateMoves(movePoint, getCaptureMovePoints(movePoint), visited, captured));
        }
        if (!visited.contains(startPosition) && capturedCheckers != null)
            moves.add(new Move(startPosition, capturedCheckers));
        return moves;
    }

    public boolean canCapture(Checker checker) {
        List<Point2D> movePoints = getCaptureMovePoints(checker.getPoint());
        return !calculateMoves(checker.getPoint(), movePoints, new ArrayList<>(), null).isEmpty();
    }

    private List<Point2D> getCaptureMovePoints(Point2D point) {
        Point2D pt1 = new Point2D.Double(point.getX() - 1, point.getY() - 1);
        Point2D pt2 = new Point2D.Double(point.getX() + 1, point.getY() - 1);
        Point2D pt3 = new Point2D.Double(point.getX() - 1, point.getY() + 1);
        Point2D pt4 = new Point2D.Double(point.getX() + 1, point.getY() + 1);
        return checkBounds(pt1, pt2, pt3, pt4);
    }

    private List<Point2D> getMovePoints(Checker checker) {
        Point2D pt1;
        Point2D pt2;
        if (checker.getCheckerType() == CheckerType.WHITE) {
            pt1 = new Point2D.Double(checker.getPoint().getX() - 1, checker.getPoint().getY() - 1);
            pt2 = new Point2D.Double(checker.getPoint().getX() + 1, checker.getPoint().getY() - 1);
        } else {
            pt1 = new Point2D.Double(checker.getPoint().getX() - 1, checker.getPoint().getY() + 1);
            pt2 = new Point2D.Double(checker.getPoint().getX() + 1, checker.getPoint().getY() + 1);
        }
        return checkBounds(pt1, pt2);
    }

    private List<Point2D> checkBounds(Point2D... points) {
        return checkBounds(Arrays.asList(points));
    }

    private List<Point2D> checkBounds(List<Point2D> points) {
        List<Point2D> pts = new ArrayList<>();
        for (Point2D point : points) {
            if (point.getX() < 1 || point.getX() > BoardMap.MAP_SIZE) {
                continue;
            }
            if (point.getY() < 1 || point.getY() > BoardMap.MAP_SIZE) {
                continue;
            }
            pts.add(point);
        }
        return pts;
    }

    private Point2D getVector(Point2D startPos, Point2D endPos) {
        return new Point2D.Double(normalize(endPos.getX() - startPos.getX()), normalize(endPos.getY() - startPos.getY()));
    }

    private double normalize(double val) {
        if (val > 0) {
            return 1;
        } else if (val < 0) {
            return -1;
        } else {
            return 0;
        }
    }

    private Point2D getCheckerPoint(List<Checker> checkers, Point2D point) {
        return getEqualPoint(checkers.stream().map(Checker::getPoint).collect(Collectors.toList()), point);
    }

    private Point2D getEqualPoint(List<Point2D> movePoints, Point2D point) {
        for (Point2D pt : movePoints) {
            if (pt.equals(point)) {
                return pt;
            }
        }
        return null;
    }
}
