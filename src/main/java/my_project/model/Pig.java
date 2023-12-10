package my_project.model;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Pig extends GraphicalObject {
    private ArrayList<BufferedImage> images = new ArrayList<>();
    private int score = 0;
    private ViewController viewController;

    public Pig(double x, double y, ViewController viewController) {
        this.x = x;
        this.y = y;
        this.viewController = viewController;
        canDraw = true;
        setPictures();
    }

    public Pig(int x, int y) {

    }


    public void draw(DrawTool drawTool) {
        if (canDraw) {
            drawTool.drawImage(images.get(0), x, y);
        }
    }

    public void setPictures() {
        addPicturesToList("src/main/resources/graphic/Schwein.png");
    }

    private void addPicturesToList(String pathToImage) {
        try {
            images.add(ImageIO.read(new File(pathToImage)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void hitByBanana(Banana banana) {
        increaseScore();
        System.out.println("Oink! Das Schwein wurde von einer Banane getroffen!");

        viewController.removeDrawable(banana);
    }


    public void increaseScore() {
        score++;
        System.out.println("Oink! Das Schwein wurde getroffen! Punktestand: " + score);
    }
}
