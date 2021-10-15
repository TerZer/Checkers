package lt.terzer.checkers.game;

import lt.terzer.checkers.drawables.BoardMap;
import lt.terzer.checkers.drawables.Checker;
import lt.terzer.checkers.players.BlackPlayer;
import lt.terzer.checkers.players.Player;
import lt.terzer.checkers.players.WhitePlayer;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Board {

    private final BoardMap boardMap = new BoardMap();
    private final GameRules gameRules = new GameRules(this);
    private final List<Player> players = new ArrayList<>();
    private Player currentPlayer;
    private Checker selectedChecker;

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

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void showPossibleMoves(Checker checker) {
        selectedChecker = checker;
        boardMap.clearHighlight();
        Player secondPlayer = getSecondPlayer();
        if(secondPlayer == null)
            return;

        List<Point2D> list = gameRules.getAvailableMoves(checker).stream().map(Move::getPoint).collect(Collectors.toList());
        boardMap.setHighlight(list);
    }

    public boolean isGameEnded() {
        return currentPlayer.getCheckers().isEmpty() || getSecondPlayer().getCheckers().isEmpty();
    }

    public void removeSelectedChecker() {
        selectedChecker = null;
        boardMap.clearHighlight();
    }

    public void changeCurrentPlayer(){
        currentPlayer = getSecondPlayer();
    }

    public void moveChecker(Point2D point) {
        if(selectedChecker != null){
            List<Move> list = gameRules.getAvailableMoves(selectedChecker);
            Move move = list.stream().filter(m -> m.getPoint().equals(point)).findFirst().orElse(null);
            if(move != null) {
                getSecondPlayer().removeCheckers(move.getCapturableCheckers());
                selectedChecker.move(move.getPoint());
                removeSelectedChecker();
                changeCurrentPlayer();
            }
        }
    }
}
