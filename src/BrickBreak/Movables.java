package BrickBreak;

import java.awt.*;

public abstract class Movables extends GameObject {
    public Movables(int x, int y) {
        super(x, y);
    }
    public abstract void update();
    public abstract void checkBorder();
    public abstract Rectangle getBounds();
}
