package my_project.model;

import my_project.Config;
//import KAGO_framework.Config;
import KAGO_framework.model.InteractiveGraphicalObject;
import KAGO_framework.view.DrawTool;


import javax.naming.ldap.Control;
import java.awt.event.KeyEvent;

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

    public Player(double x, double y, int leftKey, int rightKey){
        this.x = x;
        this.y = y;
        speedTimer = 0;
        speedBoolean = false;
        speed = 150;
        width = 80;
        height = 40;


        this.keyToGoLeft    = leftKey; //KeyEvent.VK_A;
        this.keyToGoRight   = rightKey;//KeyEvent.VK_D;
        this.direction      = -1; //-1 keine Bewegung, 0 nach rechts, 2 nach links
    }

    @Override
    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(157,152,3,255);
        drawTool.drawFilledRectangle(x,y,width,height);
        drawTool.setCurrentColor(0,0,0,255);
        drawTool.drawRectangle(x,y,width,height);
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

}
