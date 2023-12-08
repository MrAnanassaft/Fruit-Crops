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

    public Fruit(double x, double y){
        this.x = x;
        this.y = y;
        speed = 100;
        timer = 0;
    }

    public void update(double dt) {
        this.y = this.y + speed *dt;
        timer = timer + dt;
        //if(this.y >= Config.WINDOW_HEIGHT-15-this.height*2){
        //    jumpBack();
        //}
    }

    public void jumpBack(){}

    public int getPoints(){
        return points;
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
