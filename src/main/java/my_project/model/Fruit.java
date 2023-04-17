package my_project.model;

import KAGO_framework.model.GraphicalObject;
import my_project.Config;

public class Fruit extends GraphicalObject {

    protected double speed;
    protected double timer;

    protected  int points;
    public Fruit(double x, double y){
        this.x = x;
        this.y = y;
        speed = 100;
        timer = 0;
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
}
