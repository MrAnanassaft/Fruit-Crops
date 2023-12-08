package my_project.model;

import my_project.Config;
//import KAGO_framework.Config;
import KAGO_framework.model.InteractiveGraphicalObject;
import KAGO_framework.view.DrawTool;


import java.awt.*;

public class Player extends InteractiveGraphicalObject {

    private double timer;
    private double speedTimer;
    private boolean speedBoolean;
    //Attribute
    private double speed;
    private int points;

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
        width = 80;
        height = 40;
        points = 0;

        this.keyToGoLeft    = leftKey; //KeyEvent.VK_A;
        this.keyToGoRight   = rightKey;//KeyEvent.VK_D;
        this.direction      = -1; //-1 keine Bewegung, 0 nach rechts, 2 nach links
    }

    @Override
    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(new Color(255, 239, 0));
        drawTool.drawFilledRectangle(x,y,width,height);
        drawTool.setCurrentColor(0,0,0,255);
        drawTool.drawRectangle(x,y,width,height);
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

    }

    @Override
    public void update(double dt) {
        timer = timer + dt;
        //TODO 05 Überarbeiten Sie die Update-Methode derart, dass ein Player-Objekt nicht den Bildschirm verlassen kann und immer zu sehen ist.
        if(direction == 0){
            if(x < Config.WINDOW_WIDTH-19-this.width){
                x = x + speed*dt;
            }
        }

        if(direction == 2){
            if(x > 0){
                x = x - speed*dt;
            }

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




}
