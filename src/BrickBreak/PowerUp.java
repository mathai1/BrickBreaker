package BrickBreak;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PowerUp extends Stationary {
    BufferedImage power;
    public PowerUp(int x, int y, BufferedImage power) {
        super(x, y);
        this.power=power;
    }

    @Override
    public Rectangle getBounds1() {
        return new Rectangle(x,y,power.getWidth()-30,power.getHeight());
    }
    public Rectangle getBounds2() {
        return new Rectangle(x+30,y,power.getWidth(),power.getHeight());
    }

    @Override
    public void drawImage(Graphics g) {
        Graphics2D g2 =(Graphics2D)g;
        g2.drawImage(power,x,y,null);
    }
}
