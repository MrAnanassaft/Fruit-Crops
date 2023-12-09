package my_project.model;

import KAGO_framework.view.DrawTool;

import java.awt.*;

public class PowerApple extends Apple{
        private double w;
        private double h;
        private double speedBuff;
    public PowerApple(double x,double y, boolean isStart, Player player){
        super(x,y,isStart,player);
        w = 50;
        h = 50;
        points = 5;
    }

    public void draw(DrawTool drawTool) {
        super.draw(drawTool);
        drawTool.setCurrentColor(new Color(93, 56, 21));
        drawTool.drawFilledRectangle(x-w/6,y-h/4-radius,w/3,h/2);
    }

    public void update(){

    }

    public double getSpeedBuff(){
        this.speedBuff = Math.random()*100+100;
        return speedBuff;
    }
}
