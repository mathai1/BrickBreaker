package BrickBreak;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Ball extends Movables {
    private int R = 3;
    int dmg;
    String lastHit,prevHit;
    BufferedImage ball;
    public Ball(int x, int y, BufferedImage ball,int dmg) {
        super(x, y);
        this.ball=ball;
        this.dmg=dmg;
    }
    public void setR(int R){this.R=R;}
    public void setLastHit(String lastHit){this.lastHit=lastHit;}
    public void setDmg(int dmg){this.dmg=dmg;}
    public int getDmg(){return this.dmg;}



    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,ball.getWidth(),ball.getHeight());
    }

    @Override
    public void drawImage(Graphics buffer) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), this.ball.getWidth() / 2.0, this.ball.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) buffer;
        g2d.drawImage(this.ball, rotation, null);
    }

    @Override
    public void update() {
        vx=(int) Math.round(R * Math.sin(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        if(lastHit==null){
            x+=vx;
            y+=vy;
            checkBorder();
        }
        else if(lastHit=="player"){

            x-=vx;
            y-=vy;
            checkBorder();
        }
        else if(lastHit=="brick"){
            if(prevHit=="X0"){
                x+=vx;
                y+=vy;
                checkBorder();
            }
            else if(prevHit=="Xmax"){
                x+=vx;
                y+=vy;
                checkBorder();
            }
        }
        else if(prevHit=="top"){
            if(getAngle()<=180){
                x-=vx;
                y+=vy;
                checkBorder();
            }
            if(getAngle()>=180){
                x+=vx;
                y+=vy;
                checkBorder();
            }
        }

    }

    @Override
    public void checkBorder() {
        //check ball collision to border
        //if collision to border other than bottom then bounce
        if (x < 0) {

            prevHit="X0";
            if(lastHit=="player"){
                setAngle(getAngle()-90);
            }
            if(lastHit=="brick"){
                setAngle(getAngle()-90);
            }
        }
        else if (x > GameWorld.SCREEN_WIDTH-20) {
            prevHit="Xmax";
            if(lastHit=="brick"){
                setAngle(getAngle()-90);
            }
            if(lastHit=="player"){
                setAngle(getAngle()-90);
            }
        }
        else if (y<=0){
            if(prevHit=="X0"){
                setAngle(getAngle()+90);
            }
            else if(prevHit=="Xmax"){
                setAngle(getAngle()-90);
            }
            prevHit="top";

        }


    }
}
