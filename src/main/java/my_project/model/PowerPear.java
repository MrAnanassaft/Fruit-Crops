package my_project.model;

import KAGO_framework.view.DrawTool;

import java.awt.*;

public class PowerPear extends Pear{
    private double w;
    private double h;
    public PowerPear(double x, double y){
        super(x,y);
        w = 50;
        h = 50;
        points = -5;
    }

    public void draw(DrawTool drawTool){
        super.draw(drawTool);
        drawTool.setCurrentColor(new Color(93, 56, 21));
        drawTool.drawFilledRectangle(x+width/2-w/6,y-h/4-radius,w/3,h/2);
    }
    public void update(double dt){
        super.update(dt);
    }
}
