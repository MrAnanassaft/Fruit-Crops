package my_project.model;

import KAGO_framework.view.DrawTool;
import my_project.Config;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Banana extends Fruit{
    private ArrayList<BufferedImage> images = new ArrayList<>();

    public Banana(double x, double y){
        super(x,y);
        width = 15;
        height = 20;
        points = 2;
        setPictures();
    }

    public void draw(DrawTool drawTool){
        if(y > 750){
            pickedUp();
        }
        if(canDraw){
            drawTool.drawImage(images.get(0), x , y );
        }

        /*
            drawTool.setCurrentColor(new Color(255, 255, 0));
            drawTool.drawFilledRectangle(x,y,width,height);
         */

    }

    public void update(double dt){
        super.update(dt);
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
