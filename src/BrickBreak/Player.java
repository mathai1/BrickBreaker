package BrickBreak;

import java.util.ArrayList;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 *
 * @author anthony-pc
 */
public class Player extends Movables {
    private final int R = 4;
    int points;

    private BufferedImage playerImage;
    private boolean RightPressed;
    private boolean LeftPressed;

    Player(int x, int y, BufferedImage playerImage,int points) {
        super(x,y);
        this.playerImage = playerImage;
        this.points=points;
    }
    public int getPoints(){return this.points;}

    public void setPoints(int points){this.points=points;}

    void toggleRightPressed() {
        this.RightPressed = true;
    }

    void toggleLeftPressed() {
        this.LeftPressed = true;
    }


    void unToggleRightPressed() {
        this.RightPressed = false;
    }

    void unToggleLeftPressed() {
        this.LeftPressed = false;
    }


    public void update() {
        if (this.LeftPressed) {
            this.moveLeft();
            checkBorder();
        }
        if (this.RightPressed) {
            this.moveRight();
            checkBorder();
        }

    }

    private void moveLeft() {
        angle=0;
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        x -=vx;
    }

    private void moveRight() {
        angle=0;
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        x+=vx;
    }

    //checking border for player when moving left and right
    public void checkBorder() {
        if (x < 0) {
            x = 0;
        }
        if (x > GameWorld.SCREEN_WIDTH-90 ) {
            x = GameWorld.SCREEN_WIDTH-90;
        }
    }

    @Override
    public String toString() {
        return "x=" + x + ", y=" + y;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,playerImage.getWidth(),playerImage.getHeight());
    }
    public Rectangle getBounds1(){return new Rectangle(x,y,playerImage.getWidth()/2,playerImage.getHeight());}
    public Rectangle getBounds2(){return new Rectangle(x+(playerImage.getWidth()/2),y,playerImage.getWidth()/2,playerImage.getHeight());}

    public void drawImage(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.playerImage,x,y,null);

    }



}
