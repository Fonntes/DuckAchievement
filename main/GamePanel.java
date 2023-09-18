package main;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Color; 
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import entity.Player;
import tiles.TileManager;


public class GamePanel extends JPanel implements Runnable{

    Graphics2D g2;

    //Screen settings
    final int originalTileSize = 64;
    final int scale = 2;


    public final int tileSize = originalTileSize * scale;
    public final int maxScreenColumn = 12;
    public final int maxScreenRow = 6;
    public final int screenWidth = tileSize * maxScreenColumn;
    public final int screenHeight = tileSize * maxScreenRow;

    int screenHeight2 = screenHeight;
    int screenWidth2 = screenWidth;
    BufferedImage tempScreen;

    
    //World settings
    public final int maxWorldCol = 60;
    public final int maxWorldRow = 7;
    public final int worlWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    //FPS
    int FPS = 60;
    public int showFps = 0;

    //state
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    
    

    Thread gameThread;
    KeyHandler KeyH = new KeyHandler(this);
    public Player player = new Player(this,KeyH);
    TileManager tileM = new TileManager(this);

    public UI ui = new UI(this);
    public CollisionChecker cCheker = new CollisionChecker(this);


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.blue);
        this.setDoubleBuffered(true);
        this.addKeyListener(KeyH);
        this.setFocusable(true);
    }

    
    public void setupGame(){
        //gameState = titleState;
        tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D)tempScreen.getGraphics();
    }

    public void startGameThread(){
        gameState = playState;
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run(){
        
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null){

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }
            
            if(timer >= 1000000000){
                showFps = drawCount;
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update(){
        if(gameState == playState){
            player.update();
        }

        if(gameState == pauseState){
            //nothing
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        if(gameState == titleState){
          
        }else{
            tileM.draw(g2);   
            player.draw(g2);
            ui.setFps(showFps);
            ui.draw(g2);
            g2.dispose();
        }
    }
}