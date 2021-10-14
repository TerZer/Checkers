package lt.terzer.checkers.players;

import lt.terzer.checkers.drawables.BoardMap;
import lt.terzer.checkers.drawables.Checker;
import lt.terzer.checkers.drawables.CheckerType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WhitePlayer implements Player {

    private final List<Checker> checkers = new ArrayList<>();

    public WhitePlayer(){
        for(int i = 0; i < BoardMap.MAP_SIZE; i++){
            for(int j = BoardMap.MAP_SIZE-BoardMap.CHECKERS_SIZE;j < BoardMap.MAP_SIZE;j++){
                if(i%2 == 0 && j%2 == 0){
                    checkers.add(new Checker(i+1, j+1, CheckerType.WHITE));
                }
                if(i%2 == 1 && j%2 == 1){
                    checkers.add(new Checker(i+1, j+1, CheckerType.WHITE));
                }
            }
        }
    }

    @Override
    public List<Checker> getCheckers() {
        return checkers;
    }
}
