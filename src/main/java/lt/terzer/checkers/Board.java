package lt.terzer.checkers;

import lt.terzer.checkers.drawables.BoardMap;
import lt.terzer.checkers.drawables.Checker;
import lt.terzer.checkers.players.BlackPlayer;
import lt.terzer.checkers.players.Player;
import lt.terzer.checkers.players.WhitePlayer;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Board {

    private final BoardMap boardMap = new BoardMap();
    private final GameRules gameRules = new GameRules();
    private final List<Player> players = new ArrayList<>();
    private Player currentPlayer;

    public Board(){
        players.add(new BlackPlayer());
        currentPlayer = new WhitePlayer();
        players.add(currentPlayer);
    }

    public List<Checker> getCheckers() {
        List<Checker> checkers = new ArrayList<>();
        for(Player player : players){
            checkers.addAll(player.getCheckers());
        }
        return checkers;
    }

    public Checker getCheckerAt(Point2D point2D){
        for(Checker checker : currentPlayer.getCheckers()){
            if(checker.getPoint().equals(point2D)){
                return checker;
            }
        }
        return null;
    }

    public BoardMap getBoardMap() {
        return boardMap;
    }

    public Player getSecondPlayer(){
        for(Player player : players){
            if(!player.equals(currentPlayer)){
                return player;
            }
        }
        return null;
    }

    public void showPossibleMoves(Checker checker) {
        boardMap.clearHighlight();
        Player secondPlayer = getSecondPlayer();
        if(secondPlayer == null)
            return;

        List<Point2D> list = gameRules.getAvailableMoves(checker, secondPlayer.getCheckers(), currentPlayer.getCheckers());
        boardMap.setHighlight(list);
    }
}
