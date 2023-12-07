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
    private ArrayList<BufferedImage> images = new ArrayList<>();
    public Fruit(double x, double y){
        this.x = x;
        this.y = y;
        speed = 100;
        timer = 0;
        setPictures();
    }

    public void update(double dt) {
        this.y = this.y + speed *dt;
        timer = timer + dt;
        if(this.y >= Config.WINDOW_HEIGHT-15-this.height*2){
            jumpBack();
        }
    }

    public void jumpBack(){}

    public int getPoints(){
        return points;
    }
    private void setPictures() {
        for (int i = 1; i <= 3; i++) {
            addPicturesToList("src/main/resources/graphic/spider/Spider" + i + ".png");
        }
    }

    private void addPicturesToList(String pathToImage) {
        try {
            images.add(ImageIO.read(new File(pathToImage)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void draw(DrawTool drawTool){
        Graphics2D g2d = drawTool.getGraphics2D();
        AffineTransform old = g2d.getTransform();
        drawTool.setCurrentColor(Color.CYAN);
        /*  g2d.rotate(degrees + Math.PI * 0.5, x, y);
            drawTool.drawImage(images.get(pictureIndex - 1), x - 32, y - 32);
            g2d.setTransform(old);
        */
    }
}
