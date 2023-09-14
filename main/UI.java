package main;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Color;

public class UI{

    GamePanel gp;
    Graphics2D g2;
    Font arial_S30;
    int showFps;

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

        if(gp.gameState == gp.gameState){

        }

        if(gp.gameState == gp.pauseState){
            drawPauseScreen();
        }
    }

    public void drawPauseScreen( ){
        String text = "Paused";
        int x = getXforCenteredText(text);

        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);
    }

    public int getXforCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth() - 800;
        int x = gp.screenHeight/2 - length/2;
        return x;
    }
}