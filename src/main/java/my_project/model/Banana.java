package my_project.model;

import KAGO_framework.view.DrawTool;
import my_project.Config;

import java.awt.*;

public class Banana extends Fruit{

    public Banana(double x, double y){
        super(x,y);
        width = 20;
        height = 50;
    }

    public void draw(DrawTool drawTool){
        drawTool.setCurrentColor(new Color(255, 255, 0));
        drawTool.drawFilledRectangle(x,y,width,height);

    }

    public void update(double dt){
        super.update(dt);
    }


    public void jumpBack() {
        this.y = 0;
        this.x = Math.random()*(Config.WINDOW_WIDTH-width);
    }
}
