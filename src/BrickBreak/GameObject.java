package BrickBreak;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class GameObject {
    protected int x, y,angle,vx,vy;
    BufferedImage image;

    public GameObject(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setX(int x){this.x=x;}
    public void setY(int y){this.y=y;}
    public void setAngle(int angle){this.angle=angle;}

    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public int getAngle(){return this.angle;}


    public abstract void drawImage(Graphics g);
}
