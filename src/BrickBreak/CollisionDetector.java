package BrickBreak;

import org.w3c.dom.css.Rect;

import java.awt.*;
import java.util.ArrayList;

public class CollisionDetector {
    //Music collision = new Music ("./resources/dink.wav");
    GameObject obj1;
    GameObject obj2;
    ArrayList<Bricks> obj3;
    CollisionDetector(GameObject one, GameObject two, ArrayList<Bricks> three){
        this.obj1=one;
        this.obj2=two;
        this.obj3=three;
    }
    //NOTE: have a way to change angle of the bounce

    public void BallPlayer(Ball ball, Player player){
        Rectangle ballR = new Rectangle(ball.getBounds());
        Rectangle player1 = new Rectangle(player.getBounds1());
        Rectangle player2 = new Rectangle(player.getBounds2());
        if(ballR.intersects(player1)){
            //collision.play();
            ball.setAngle(45);
            ball.setLastHit("player");
        }
        else if(ballR.intersects(player2)){
            //collision.play();
            ball.setAngle(-45);
            ball.setLastHit("player");
        }
    }

    public void BallBrick(Player player,Ball ball, ArrayList<Bricks> brick){
        Rectangle one = new Rectangle(ball.getBounds());
        for(int i =0;i< brick.size();i++){
            if(one.intersects(brick.get(i).getBounds1())){
                //collision.play();
                ball.setAngle(45);
                brick.get(i).setState(brick.get(i).getState()-ball.getDmg());
                if(brick.get(i).getState()<=0){
                    brick.remove(brick.get(i));
                }
                player.setPoints(player.getPoints()+ball.getDmg());
                if(player.getPoints()==50){
                    ball.setR(5);
                }
                ball.setLastHit("brick");
            }
            else if(one.intersects(brick.get(i).getBounds2())){
                //collision.play();
                ball.setAngle(-45);
                brick.get(i).setState(brick.get(i).getState()-ball.getDmg());
                if(brick.get(i).getState()<=0){
                    brick.remove(brick.get(i));
                }
                player.setPoints(player.getPoints()+ball.getDmg());
                ball.setLastHit("brick");
            }
            if(player.getPoints()==25){
                ball.setR(5);
            }
        }
    }

    public void BallPower(Ball ball, ArrayList<PowerUp> power){
        Rectangle one = new Rectangle(ball.getBounds());
        for(int i=0;i<power.size();i++){
            if(one.intersects(power.get(i).getBounds1())){
                //collision.play();
                power.remove(power.get(i));
                ball.setDmg(ball.getDmg()*4);
            }
            else if(one.intersects(power.get(i).getBounds2())){
                //collision.play();
                power.remove(power.get(i));
                ball.setDmg(ball.getDmg()*4);
            }
        }

    }
}
