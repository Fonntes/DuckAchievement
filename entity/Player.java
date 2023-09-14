package entity;

import main.GamePanel;
import main.KeyHandler;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player extends Entity{
    
    GamePanel gp;
    KeyHandler keyH;
    String lastKeyPressed = "right";

    public final int screenX;
    public final int screenY;

    //int haskey = 0;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2) + 132;
    

        //localização da collision do player
        solidArea = new Rectangle(130, 0, gp.tileSize - 132, gp.tileSize);

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        worldX =  gp.tileSize * 2 - 64;
        worldY = gp.tileSize * 5 - 60;
        speed = 3;
        direction = "right";
    }

    public void getPlayerImage(){
        try{
            stop1 = ImageIO.read(getClass().getResourceAsStream("/Assets/Player/DuckLeftStop2.png"));
            stop2 = ImageIO.read(getClass().getResourceAsStream("/Assets/Player/DuckRightStop2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/Assets/Player/DuckRightStop.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/Assets/Player//DuckRightMove.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/Assets/Player/DuckLeftStop.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/Assets/Player/DuckLeftMove.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
        gp.cCheker.checkTile(this);

        if(keyH.rightPressed == true || keyH.leftPressed == true){
            if(keyH.rightPressed == true){
                direction = "right";
                lastKeyPressed = "right";

                if(colissionRight == false){
                    worldX += speed;
                    colissionLeft = false;
                }
            }

            if(keyH.leftPressed == true){
                direction = "left";
                lastKeyPressed = "left";
                
                 if(colissionLeft == false){
                    worldX -= speed;
                    colissionRight = false;
                }
            }

            spritesCounter();
         
        }else{
            //System.out.println(lastKeyPressed);
            if("right".equals(lastKeyPressed)){
                direction = "stopright";
            }

            if("left".equals(lastKeyPressed)){
                direction = "stopleft";
            }
            
            spritesCounter();
        }

    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;

        switch(direction){
            case "right":
                if(spriteNum == 1){
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }
            break;

            case "left":
                if(spriteNum == 1){
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
            break;

            case "stopleft":
                if(spriteNum == 1){
                    image = left1;
                }
                if(spriteNum == 2){
                    image = stop1;
                }
            break;

            case "stopright":
                if(spriteNum == 1){
                    image = right1;
                }
                if(spriteNum == 2){
                    image = stop2;
                }
            break;
        }

        g2.drawImage(image, screenX, screenY, gp.tileSize * 2, gp.tileSize * 2, null);
    }

    public void spritesCounter(){
        spriteCounter++;

        if(spriteCounter > 13){
            if(spriteNum == 1){
                spriteNum = 2;
            }
            else if(spriteNum ==2){
                spriteNum = 1;
            }
            spriteCounter = 0;
            }
    }
}
