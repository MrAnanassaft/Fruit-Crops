package my_project.model;

import KAGO_framework.view.DrawTool;
import my_project.Config;
import KAGO_framework.model.GraphicalObject;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Banana extends Fruit{
    private ArrayList<BufferedImage> images = new ArrayList<>();

    private Pig pig;

    public Banana(double x, double y, boolean isStart, Player player, Pig pig){
        super(x,y,isStart,player);
        width = 15;
        height = 20;
        points = 2;
        setPictures();
        fruit = "banana";
        this.pig = pig;
    }

    public void draw(DrawTool drawTool){
        if(y > 750){
            pickedUp();
        }
        if(canDraw && isStart()){
            drawTool.drawImage(images.get(0), x , y );
        }
        if(!isStart()){
            g2d = drawTool.getGraphics2D();
            AffineTransform old = g2d.getTransform();
            g2d.rotate(degrees+Math.PI*0.5,x,y);
            drawTool.drawImage(images.get(0), x , y );
            g2d.setTransform(old);
        }
    }

    public void update(double dt) {
        super.update(dt);
        checkCollisionWithPig(); // Überprüfe Kollision mit dem Schwein
    }
    private void checkCollisionWithPig() {
        // Überprüfe Kollision mit dem Schwein
        if (collidesWith(pig)) {
            pig.hitByBanana();
            pickedUp();
        }
    }

    public boolean collidesWith(GraphicalObject gO) {
        if (gO != null && gO.getRadius() > 0 &&
                x < gO.getX() + width && x + width > gO.getX() &&
                y < gO.getY() + height && y + height > gO.getY()) {
            return true;
        }
        return false;
    }

    public void jumpBack() {
        this.y = 0;
        this.x = Math.random()*(300-width);
    }
    public void setPictures(){
        addPicturesToList("src/main/resources/graphic/Banane.png");
    }
    private void addPicturesToList(String pathToImage) {
        try {
            images.add(ImageIO.read(new File(pathToImage)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
