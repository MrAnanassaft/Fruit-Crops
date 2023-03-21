package my_project.model;

import KAGO_framework.view.DrawTool;

import java.awt.*;

public class PowerApple extends Apple{
        private double w;
        private double h;
    public PowerApple(double x,double y){
        super(x,y);
        w = 50;
        h = 50;
    }

    public void draw(DrawTool drawTool) {
        super.draw(drawTool);
        drawTool.setCurrentColor(new Color(167, 30, 30));
        drawTool.drawFilledRectangle(x-w/2,y-h/2,w,h);
    }

    public void update(){

    }
}
