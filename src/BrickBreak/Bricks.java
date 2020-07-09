package BrickBreak;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static javax.imageio.ImageIO.read;

public class Bricks extends Stationary{
    int state;
    BufferedImage brickImage;
    BufferedImage RBrick=read(GameWorld.class.getClassLoader().getResource("RBrick.png"));
    BufferedImage GBrick=read(GameWorld.class.getClassLoader().getResource("GBrick.png"));
    BufferedImage YBrick=read(GameWorld.class.getClassLoader().getResource("YBrick.png"));
    BufferedImage BBrick=read(GameWorld.class.getClassLoader().getResource("BBrick.png"));

    public Bricks (int x,int y,int state,BufferedImage brickImage) throws IOException {
        super(x,y);
        this.state=state;
        this.brickImage=brickImage;
    }
    public int getState(){return this.state;}

    @Override
    public Rectangle getBounds1() {
        return new Rectangle(x,y,brickImage.getWidth()/2,brickImage.getHeight());
    }
    public Rectangle getBounds2() { return new Rectangle(x+(brickImage.getWidth()/2),y,brickImage.getWidth()/2,brickImage.getHeight()); }

    public void setState(int state){this.state=state;}

    //depending on the state of the brick draw a different color brick
    public void drawImage(Graphics g){
        if(state==1){
            Graphics2D g2 =(Graphics2D)g;
            g2.drawImage(RBrick,x,y,null);
        }
        if(state==2){
            Graphics2D g2 =(Graphics2D)g;
            g2.drawImage(YBrick,x,y,null);
        }
        if(state==3){
            Graphics2D g2 =(Graphics2D)g;
            g2.drawImage(BBrick,x,y,null);
        }
        if(state==4){
            Graphics2D g2 =(Graphics2D)g;
            g2.drawImage(GBrick,x,y,null);
        }



    }
}
