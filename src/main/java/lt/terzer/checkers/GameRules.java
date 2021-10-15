package lt.terzer.checkers;

import lt.terzer.checkers.drawables.BoardMap;
import lt.terzer.checkers.drawables.Checker;
import lt.terzer.checkers.drawables.CheckerType;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameRules {

    public List<Point2D> getAvailableMoves(Checker checker, List<Checker> enemyCheckers, List<Checker> allyCheckers){
        if(canBeMoved(checker, enemyCheckers, allyCheckers)) {
            //TODO check all possible moves
            List<Point2D> points = getMovePoints(combine(enemyCheckers, allyCheckers), checker);
            points.removeIf(point2D -> getCheckerPoint(combine(enemyCheckers, allyCheckers), point2D) != null);
            return points;
        }
        return new ArrayList<>();
    }

    private boolean canBeMoved(Checker checker, List<Checker> enemyCheckers, List<Checker> allyCheckers){
        if(!canCapture(checker, enemyCheckers, combine(enemyCheckers, allyCheckers))) {
            for (Checker c : allyCheckers) {
                if (canCapture(c, enemyCheckers, combine(enemyCheckers, allyCheckers))) {
                    return false;
                }
            }
        }
        return true;
    }

    @SafeVarargs
    private List<Checker> combine(List<Checker>... lists){
        return Stream.of(lists)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<Point2D> getMovePoints(List<Checker> allCheckers, Checker checker) {
        Point2D pt1;
        Point2D pt2;
        if(checker.getCheckerType() == CheckerType.WHITE) {
            pt1 = new Point2D.Double(checker.getPoint().getX() - 1, checker.getPoint().getY() - 1);
            pt2 = new Point2D.Double(checker.getPoint().getX() + 1, checker.getPoint().getY() - 1);
        }
        else {
            pt1 = new Point2D.Double(checker.getPoint().getX() - 1, checker.getPoint().getY() + 1);
            pt2 = new Point2D.Double(checker.getPoint().getX() + 1, checker.getPoint().getY() + 1);
        }
        return checkBounds(pt1, pt2);
    }

    private List<Point2D> checkBounds(Point2D... points){
        List<Point2D> pts = new ArrayList<>();
        for(Point2D point : points){
            if(point.getX() < 1 || point.getX() > BoardMap.MAP_SIZE){
                continue;
            }
            if(point.getY() < 1 || point.getY() > BoardMap.MAP_SIZE){
                continue;
            }
            pts.add(point);
        }
        return pts;
    }

    private Point2D getVector(Point2D startPos, Point2D endPos){
        return new Point2D.Double(normalize(endPos.getX()-startPos.getX()), normalize(endPos.getY()-startPos.getY()));
    }

    private double normalize(double val){
        if(val > 0){
            return 1;
        }
        else if(val < 0){
            return -1;
        }
        else {
            return 0;
        }
    }


    public boolean canCapture(Checker checker, List<Checker> enemyCheckers, List<Checker> allCheckers) {
        List<Point2D> movePoints = getMovePoints(allCheckers, checker);
        for(Checker enemy : enemyCheckers){
            Point2D point = getEqualPoint(movePoints, enemy.getPoint());
            if(point == null)
                continue;

            Point2D vector = getVector(checker.getPoint(), point);
            Point2D movePoint = new Point2D.Double(point.getX()+vector.getX(), point.getY()+vector.getY());
            if(getCheckerPoint(allCheckers, movePoint) == null)
                return true;
        }
        return false;
    }

    private Point2D getCheckerPoint(List<Checker> checkers, Point2D point) {
        return getEqualPoint(checkers.stream().map(Checker::getPoint).collect(Collectors.toList()), point);
    }

    private Point2D getEqualPoint(List<Point2D> movePoints, Point2D point) {
        for(Point2D pt : movePoints){
            if(pt.equals(point)){
                return pt;
            }
        }
        return null;
    }
}
