package BrickBreak;

import java.awt.*;

public abstract class Stationary extends GameObject {
    public Stationary(int x, int y) {
        super(x, y);
    }
    public abstract Rectangle getBounds1();
    public abstract Rectangle getBounds2();
}
