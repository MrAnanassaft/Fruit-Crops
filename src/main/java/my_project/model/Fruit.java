package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
import my_project.Config;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Fruit extends GraphicalObject {

    protected double speed;
    protected double timer;

    protected  int points;
    protected boolean isStart;
    protected Player player;
    protected ArrayList<BufferedImage> images = new ArrayList<>();
    protected Graphics2D g2d;
    protected double degrees;

    public Fruit(double x, double y, boolean isStart, Player player){
        this.x = x;
        this.y = y;
        speed = 100;
        timer = 0;
        this.isStart = isStart;
        this.player = player;
        degrees = player.getDegrees();
    }

    public void update(double dt) {
        if(isStart()){
            this.y = this.y + speed *dt;
        }else{
            double dx = Math.cos(degrees)*speed*dt;
            double dy = Math.sin(degrees)*speed*dt;
            x += dx;
            y += dy;
        }
        timer = timer + dt;
        //if(this.y >= Config.WINDOW_HEIGHT-15-this.height*2){
        //    jumpBack();
        //}
    }




    public void jumpBack(){}
    public boolean isStart(){
        return isStart;
    }
    public int getPoints(){
        return points;
    }
    public void setSpeed(){
        speed = 300;
    }

    /*
        protected void moveTowardsPlayer(double dt){
            degrees = Math.atan2(player.getY()-y,player.getX()-x);
            double dx = Math.cos(degrees)*speed*dt;
            double dy = Math.sin(degrees)*speed*dt;
            x += dx;
            y += dy;
        }
     */

}
