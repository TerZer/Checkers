package lt.terzer.checkers.drawables;

import java.awt.*;

public enum CheckerType {
    WHITE(Color.WHITE),
    BLACK(Color.BLACK);

    private final Color color;

    CheckerType(Color color){
        this.color = color;
    }

    public Color getColor(){
        return color;
    }

}
