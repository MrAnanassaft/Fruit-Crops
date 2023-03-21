package my_project.model;


import KAGO_framework.view.DrawTool;

public class Pear extends Fruit {

    //Attribute
    private boolean nachLinks;



    public Pear(double x, double y){
        super(x,y);
        width = 25;
        height = 35;
        nachLinks = false;

    }

    @Override
    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(0,255,0,255);
        drawTool.drawFilledRectangle(x,y,width,height);
        drawTool.setCurrentColor(0,0,0,255);
        drawTool.drawRectangle(x,y,width,height);
    }

    @Override
    public void update(double dt) {
        super.update(dt);
        if(timer > 2 && timer < 4){
            nachLinks = true;
        }
        if(timer > 4){
            nachLinks = false;
            timer = 0;
        }
        if(nachLinks){
            this.x = this.x + 100*dt;
        }else{
            this.x = this.x - 100*dt;
        }
    }
        //TODO 03 Eine Birne soll von oben herab fallen. Sobald sie unten den Bildschirmrand berührt wird die Methode jumpBack() aufgerufen (siehe TODO 04).

    public void jumpBack(){
        this.y = 0;
        this.x = Math.random()*(1000-width-200);
    }

    //TODO 04 Lege eine Methode jumpBack() an, die bei Aufruf das Pear-Objekt oben am oberen Bildschirmrand an einer zufälligen x-Position positioniert.
}

