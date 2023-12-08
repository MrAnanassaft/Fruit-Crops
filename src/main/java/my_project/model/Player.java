package my_project.model;

import KAGO_framework.control.ViewController;
//import KAGO_framework.Config;
import KAGO_framework.model.InteractiveGraphicalObject;
import KAGO_framework.view.DrawTool;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Player extends InteractiveGraphicalObject {

    private double timer;
    private double speedTimer;
    private boolean speedBoolean;
    private double shootTimer;
    private boolean didShoot;
    //Attribute
    private double speed;
    private int points;
    private ArrayList<BufferedImage> images = new ArrayList<>();

    //Tastennummern zur Steuerung
    private int keyToGoLeft;
    private int keyToGoRight;
    private int direction;
    private String s;
    private boolean winner1;
    private boolean winner2;

    public Player(double x, double y, int leftKey, int rightKey){
        this.x = x;
        this.y = y;
        speedTimer = 0;
        speedBoolean = false;
        speed = 150;
        width = 67;
        height = 30;
        points = 0;
        shootTimer = 0;
        didShoot = false;

        this.keyToGoLeft    = leftKey; //KeyEvent.VK_A;
        this.keyToGoRight   = rightKey;//KeyEvent.VK_D;
        this.direction      = -1; //-1 keine Bewegung, 0 nach rechts, 2 nach links
        setPictures();
    }

    @Override
    public void draw(DrawTool drawTool) {
        /*
        drawTool.setCurrentColor(new Color(255, 239, 0));
        drawTool.drawFilledRectangle(x,y,width,height);
        drawTool.setCurrentColor(0,0,0,255);
        drawTool.drawRectangle(x,y,width,height);
         */
        /*
        drawPoints(drawTool);
        if(winner1){
            drawTool.setCurrentColor(new Color(255, 255, 255));
            drawTool.drawFilledRectangle(0,0,1600,1024);
            drawTool.setCurrentColor(new Color(0,0,0,255));
            drawTool.formatText("Arial",1,50);
            drawTool.drawText(150,200,"PLAYER 1 WON CONGRATS");
        }else if(winner2){
            drawTool.setCurrentColor(new Color(255, 255, 255));
            drawTool.drawFilledRectangle(0,0,1600,1024);
            drawTool.setCurrentColor(new Color(0,0,0,255));
            drawTool.formatText("Arial",1,50);
            drawTool.drawText(150,200,"PLAYER 2 WON CONGRATS");
        }
         */
        if(!didShoot){
            drawTool.drawImage(images.get(0), x , y );
        }else{
            drawTool.drawImage(images.get(1), x , y );
        }
        if(shootTimer > 0.1){
            didShoot = false;
            shootTimer = 0;
        }
    }

    @Override
    public void update(double dt) {
        timer = timer + dt;
        //TODO 05 Überarbeiten Sie die Update-Methode derart, dass ein Player-Objekt nicht den Bildschirm verlassen kann und immer zu sehen ist.
        //if(direction == 0){
            //if(x < Config.WINDOW_WIDTH-19-this.width){
            //    x = x + speed*dt;
            //}
        //}
        if(ViewController.isKeyDown(65)){
            x -= speed*dt;
        }
        if(ViewController.isKeyDown(68)){
            x+= speed*dt;
        }

        //if(direction == 2){
          //  if(x > 0){
           //     x = x - speed*dt;
          //  }

        //}
        shoot(dt);
        if(x < 0){
            x = 0;
        }
        if(x + width > 300){
            x = 300 - width;
        }
    }

    @Override
    public void keyPressed(int key) {
        if(key == keyToGoLeft){
            direction = 2;
        }
        if(key == keyToGoRight){
            direction = 0;
        }
    }

    @Override
    public void keyReleased(int key) {
        if(key == keyToGoLeft){
            direction = -1;
        }
        if(key == keyToGoRight){
            direction = -1;
        }
    }

    public boolean getSpeedBoolean(){
        return speedBoolean;
    }
    public void setTrueSpeed(){
        speedBoolean = true;
    }
    public void setFalseSpeed(){
        speedBoolean = false;
    }
    public void speedUp(double speed){
        this.speed = speed;
    }
    public double getSpeed(){
        return speed;
    }
    public void setSpeedTimer(double dt){
        speedTimer = speedTimer + dt;
    }
    public void resetSpeedTimer(){
        speedTimer = 0;
    }
    public double getSpeedTimer(){
        return speedTimer;
    }
    public void shoot(double dt){
        if(didShoot == true){
            shootTimer += dt;
        }
    }
    public void setDidShoot(){
        didShoot = true;
    }
    /*
    public void setPoints(int points){
        this.points = this.points + points;
    }
    public int getPoints1(){
        return points;
    }
    public void drawPoints(DrawTool drawTool){
        this.s = String.valueOf(getPoints1());
        drawTool.setCurrentColor(new Color(0,0,0,255));
        drawTool.formatText("Arial",1,25);
        drawTool.drawText(x,y,s);
    }
    public void winner1(){
        winner1 = true;
    }
    public void winner2(){
        winner2 = true;
    }
     */
    public void setPictures(){
        addPicturesToList("src/main/resources/graphic/Kanone1.png");
        addPicturesToList("src/main/resources/graphic/Kanone2.png");
    }
    private void addPicturesToList(String pathToImage) {
        try {
            images.add(ImageIO.read(new File(pathToImage)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
