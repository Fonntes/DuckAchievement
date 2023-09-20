package main;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.Color;

public class UI{

    GamePanel gp;
    Graphics2D g2;
    Font arial_S30;
    int showFps;
    BufferedImage pausedBackground, startButton;

    public UI(GamePanel gp){
        this.gp = gp;

        arial_S30 = new Font("Arial", Font.PLAIN, 30);
    }

    public void setFps(int fps) {
        this.showFps = fps;
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(arial_S30);
        g2.setColor(Color.white);
        g2.drawString("FPS = " + showFps, 50, 50);

        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }

        if(gp.gameState == gp.gameState){
                //nothing
        }

        if(gp.gameState == gp.pauseState){
            drawPauseScreen();
        }
    }

    public void drawTitleScreen(){
        g2.setFont(arial_S30);
        g2.setColor(Color.white);
        String text = "a";
        g2.drawString(text, 200, 200);
    }

    private void loadPausedBackground(){
        try {
            pausedBackground = ImageIO.read(getClass().getResourceAsStream("/Assets/UI/PausedBackground/pausedBackground.png"));
            startButton = ImageIO.read(getClass().getResourceAsStream("/Assets/UI/PausedBackground/startButton.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawPauseScreen( ){
        System.out.println(gp.screenWidth);
        loadPausedBackground();
        int x = gp.screenWidth / 4;
        int y = gp.screenHeight / 100;

        g2.drawImage(pausedBackground,x,y,gp.screenHeight,gp.screenWidth/2,null);
        g2.drawImage(startButton,x+255,y+200,256,256,null);
        
    }
}