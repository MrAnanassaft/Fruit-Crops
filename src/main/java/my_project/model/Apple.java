package my_project.model;


import KAGO_framework.view.DrawTool;
import my_project.Config;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Apple extends Fruit {

    //Attribute
    private ArrayList<BufferedImage> images = new ArrayList<>();

    public Apple(double x, double y){
        super(x,y);
        width = 15;
        height = 20;
        points = 1;
        setPictures();
    }

    @Override
    public void draw(DrawTool drawTool) {
        //drawTool.setCurrentColor(255,0,0,255);
        //drawTool.drawFilledCircle(x,y,radius);
        //drawTool.setCurrentColor(0,0,0,255);
        //drawTool.drawCircle(x,y,radius);
        if(y > 750){
            pickedUp();
        }
        if(canDraw){
            drawTool.drawImage(images.get(0), x , y );
        }

    }

    @Override
    public void update(double dt) {
        super.update(dt);
        //TODO 01 Ein Apfel soll von oben herab fallen. Sobald er unten den Bildschirmrand berührt wird die Methode jumpBack() aufgerufen (siehe TODO 02).
    }

    public void jumpBack(){
        this.y = 10;
        this.x = Math.random()*(300-radius*2)+radius;
    }


        public void changePoints(){
            if(points > 0){
                this.points = this.points * -1;
            }
        }
        public void changePointsBack(){
            if(points < 0){
                this.points = this.points * -1;
            }
        }

    public void setPictures(){
        addPicturesToList("src/main/resources/graphic/Apfel.png");
    }
    private void addPicturesToList(String pathToImage) {
        try {
            images.add(ImageIO.read(new File(pathToImage)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
