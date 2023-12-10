package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Fly extends GraphicalObject {

    private ArrayList<BufferedImage> images = new ArrayList<>();

    public Fly(double x, double y){
        this.x = x;
        this.y = y;
        canDraw = true;
        setPictures();
    }

    public void draw(DrawTool drawTool){
        if(canDraw){
            drawTool.drawImage(images.get(0),x,y);
        }
    }

    public void setPictures(){
        addPicturesToList("src/main/resources/graphic/Fliege.png");
    }
    private void addPicturesToList(String pathToImage) {
        try {
            images.add(ImageIO.read(new File(pathToImage)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
