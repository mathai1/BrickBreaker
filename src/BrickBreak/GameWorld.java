/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BrickBreak;


import sun.audio.AudioPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Random;


import static javax.imageio.ImageIO.getUseCache;
import static javax.imageio.ImageIO.read;

/**
 * Main driver class of Tank Example.
 * Class is responsible for loading resources and
 * initializing game objects. Once completed, control will
 * be given to infinite loop which will act as our game loop.
 * A very simple game loop.
 * @author anthony-pc
 */
public class GameWorld extends JPanel  {


    public static final int SCREEN_WIDTH = 600;
    public static final int SCREEN_HEIGHT = 900;
    private BufferedImage world;
    private Graphics2D buffer;
    private JFrame jFrame;
    private Player player;
    private Ball ball;
    private static CollisionDetector cd;
    public static ArrayList <Bricks> bricks;
    public static ArrayList <PowerUp> powers;
    //Music background;

    public static void main(String[] args) {
        GameWorld game = new GameWorld();
        game.init();
        try {

            while (true) {
                game.player.update();
                game.ball.update();
                game.repaint();
                cd.BallPlayer(game.ball,game.player);
                cd.BallBrick(game.player,game.ball,game.getBricks());
                cd.BallPower(game.ball,game.getPowers());
                Thread.sleep(1000 / 144);
            }
        } catch (InterruptedException ignored) {
            System.out.println(ignored);
        }

    }
    public static ArrayList<Bricks> getBricks(){ return bricks; }
    public static ArrayList<PowerUp> getPowers(){ return powers; }

    private void init() {
        this.jFrame = new JFrame("Brick Breaker");
        this.world = new BufferedImage(GameWorld.SCREEN_WIDTH, GameWorld.SCREEN_HEIGHT, BufferedImage.TYPE_INT_RGB);
        BufferedImage playerImage = null;
        BufferedImage ballImage=null;
        BufferedImage RBrick=null;
        BufferedImage YBrick=null;
        BufferedImage GBrick=null;
        BufferedImage BBrick=null;
        BufferedImage PowBrick=null;
        bricks=new ArrayList<>();
        powers=new ArrayList<>();
        //background =new Music ("resources/Music.wav");
        //background.play();
        //background.loop();
        try {

            /*
             * There is a subtle difference between using class
             * loaders and File objects to read in resources for your
             * tank games. First, File objects when given to input readers
             * will use your project's working directory as the base path for
             * finding the file. Class loaders will use the class path of the project
             * as the base path for finding files. For Intellij, this will be in the out
             * folder. if you expand the out folder, the expand the production folder, you
             * will find a folder that has the same name as your project. This is the folder
             * where your class path points to by default. Resources, will need to be stored
             * in here for class loaders to load resources correctly.
             *
             * Also one important thing to keep in mind, Java input readers given File objects
             * cannot be used to access file in jar files. So when building your jar, if you want
             * all files to be stored inside the jar, you'll need to class loaders and not File
             * objects.
             *
             */
            //Using class loaders to read in resources
            playerImage = read(GameWorld.class.getClassLoader().getResource("Player.png"));
            ballImage=read(GameWorld.class.getClassLoader().getResource("Ball.png"));

            RBrick=read(GameWorld.class.getClassLoader().getResource("RBrick.png"));
            GBrick=read(GameWorld.class.getClassLoader().getResource("GBrick.png"));
            YBrick=read(GameWorld.class.getClassLoader().getResource("YBrick.png"));
            BBrick=read(GameWorld.class.getClassLoader().getResource("BBrick.png"));

            PowBrick=read(GameWorld.class.getClassLoader().getResource("PowBrick.png"));
            //Using file objects to read in resources.
            //tankImage = read(new File("tank1.png"));

            //randomizing the maps between map1,map2,map3
            InputStreamReader isr = null;
            Random random =new Random();
            int randnum= random.nextInt(2+1)+1;
            if(randnum==1){isr = new InputStreamReader(GameWorld.class.getClassLoader().getResourceAsStream("maps/map1"));}
            else if(randnum==2){isr = new InputStreamReader(GameWorld.class.getClassLoader().getResourceAsStream("maps/map2"));}
            else if(randnum==3){isr = new InputStreamReader(GameWorld.class.getClassLoader().getResourceAsStream("maps/map3"));}
            //InputStreamReader isr = new InputStreamReader(GameWorld.class.getClassLoader().getResourceAsStream("maps/map1"));
            BufferedReader mapReader =new BufferedReader(isr);

            String row =mapReader.readLine();
            String[] mapInfo=row.split("\t");
            int numCols = Integer.parseInt(mapInfo[0]);
            int numRows = Integer.parseInt(mapInfo[1]);

            for(int curRow=0;curRow<numRows;curRow++){
                row=mapReader.readLine();
                mapInfo=row.split("\t");
                for(int curCol=0;curCol<numCols;curCol++){
                    switch(mapInfo[curCol]){
                        case "1":
                            this.bricks.add(new Bricks(curCol*60,curRow*30,1,RBrick));
                            break;
                        case "2":
                            this.bricks.add(new Bricks(curCol*60,curRow*30,2,YBrick));
                            break;
                        case "3":
                            this.bricks.add(new Bricks(curCol*60,curRow*30,3,BBrick));
                            break;
                        case "4":
                            this.bricks.add(new Bricks(curCol*60,curRow*30,4,GBrick));
                            break;
                        case "5":
                            this.powers.add(new PowerUp(curCol*60,curRow*30,PowBrick));

                    }
                }
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        player = new Player(200, 800, playerImage,0);
        ball = new Ball(200, 250,ballImage,1);
        cd = new CollisionDetector(player,ball,bricks);


        PlayerControl playerControl = new PlayerControl(player, KeyEvent.VK_A,KeyEvent.VK_D);

        this.jFrame.setLayout(new BorderLayout());
        this.jFrame.add(this);
        this.jFrame.addKeyListener(playerControl);
        this.jFrame.setSize(GameWorld.SCREEN_WIDTH, GameWorld.SCREEN_HEIGHT+80 );
        this.jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        this.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jFrame.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);
        buffer = world.createGraphics();
        if(ball.getY()<GameWorld.SCREEN_HEIGHT){
            buffer.setColor(Color.white);
            buffer.fillRect(0,0,GameWorld.SCREEN_WIDTH,GameWorld.SCREEN_HEIGHT);
            this.bricks.forEach(bricks -> bricks.drawImage(buffer));
            this.powers.forEach(powers -> powers.drawImage(buffer));
            this.player.drawImage(buffer);
            this.ball.drawImage(buffer);

            //showing points of player
            g2.setColor(Color.black);
            g2.setFont(new Font("TimesNewRoman", Font.BOLD, 25));
            g2.drawString("POINTS: "+ player.getPoints(),0,945);


            g2.drawImage(world,0,0,null);
        }
        if(bricks.size()==0){
            g2.setColor(Color.green);
            g2.fillRect(0,0,GameWorld.SCREEN_WIDTH,GameWorld.SCREEN_HEIGHT+80);
            g2.setColor(Color.red);
            g2.setFont(new Font("TimesNewRoman", Font.BOLD, 50));
            g2.drawString("GAME OVER", 130, 400);
            g2.drawString("YOU WIN", 160, 451);
            g2.setColor(Color.BLACK);
            g2.drawString("POINTS: "+player.getPoints(),160,500);
        }
        if(ball.getY()>=GameWorld.SCREEN_HEIGHT){
            g2.setColor(Color.CYAN);
            g2.fillRect(0,0,GameWorld.SCREEN_WIDTH,GameWorld.SCREEN_HEIGHT+80);
            g2.setColor(Color.red);
            g2.setFont(new Font("TimesNewRoman", Font.BOLD, 50));
            g2.drawString("GAME OVER", 130, 400);
            g2.drawString("YOU LOSE", 160, 451);
            g2.setColor(Color.BLACK);
            g2.drawString("POINTS: "+player.getPoints(),160,500);
        }

    }
}
