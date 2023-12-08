package my_project.model;


import KAGO_framework.view.DrawTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Pear extends Fruit {

    //Attribute
    private boolean toLeft;
    private boolean print;
    private double printTimer;
    private ArrayList<BufferedImage> images = new ArrayList<>();


    public Pear(double x, double y){
        super(x,y);
        width = 15;
        height = 20;
        toLeft = false;
        print = false;
        printTimer = 0;
        points = -1;
        setPictures();
    }

    @Override
    public void draw(DrawTool drawTool) {
        if(y < 750){
            drawTool.drawImage(images.get(0), x , y );
        }

        /*
        drawTool.setCurrentColor(0,255,0,255);
        drawTool.drawFilledRectangle(x,y,width,height);
        drawTool.setCurrentColor(0,0,0,255);
        drawTool.drawRectangle(x,y,width,height);
        if(print == true){
            printPear(drawTool);
        }
         */
    }

    @Override
    public void update(double dt) {
        super.update(dt);
        if(print == true){
            printTimer = printTimer + dt;
        }
        if(printTimer > 5){
            print = false;
            printTimer = 0;
        }
    }
        //TODO 03 Eine Birne soll von oben herab fallen. Sobald sie unten den Bildschirmrand berührt wird die Methode jumpBack() aufgerufen (siehe TODO 04).

    public void jumpBack(){
        this.y = 0;
        this.x = Math.random()*(300-width);
    }

    public void printPear(DrawTool drawTool){
        drawTool.setCurrentColor(new Color(0,0,0,255));
        drawTool.formatText("Arial",1,25);
        drawTool.drawText(250,200,"si no hay comida el diablo quiere pera");
    }
    public void goLeft(double dt){
        if(timer > 2 && timer < 4){
            toLeft = true;
        }
        if(timer > 4){
            toLeft = false;
            timer = 0;
        }
        if(toLeft){
            this.x = this.x + 100*dt;
        }else{
            this.x = this.x - 100*dt;
        }
    }
    public void setPrintBool(){
        if(print == false){
            print = true;
        }
        printTimer = 0;
    }
    public void setPictures(){
        addPicturesToList("src/main/resources/graphic/Birne.png");
    }
    private void addPicturesToList(String pathToImage) {
        try {
            images.add(ImageIO.read(new File(pathToImage)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

