package my_project.control;

import KAGO_framework.control.Drawable;
import KAGO_framework.control.ViewController;
import KAGO_framework.model.GraphicalObject;
import my_project.Config;
import my_project.model.*;
import org.w3c.dom.ls.LSOutput;

import java.awt.event.KeyEvent;

/**
 * Ein Objekt der Klasse ProgramController dient dazu das Programm zu steuern. Die updateProgram - Methode wird
 * mit jeder Frame im laufenden Programm aufgerufen.
 */
public class ProgramController {

    //Attribute


    // Referenzen
    private ViewController viewController;  // diese Referenz soll auf ein Objekt der Klasse viewController zeigen. Über dieses Objekt wird das Fenster gesteuert.



    private Player p1;

    private Queue<GraphicalObject> newQueue;
    private Background background;
    private List newList = new List<GraphicalObject>();
    private double appleTimer;
    private double pearTimer;
    private double bananaTimer;



    /**
     * Konstruktor
     * Dieser legt das Objekt der Klasse ProgramController an, das den Programmfluss steuert.
     * Damit der ProgramController auf das Fenster zugreifen kann, benötigt er eine Referenz auf das Objekt
     * der Klasse viewController. Diese wird als Parameter übergeben.
     * @param viewController das viewController-Objekt des Programms
     */
    public ProgramController(ViewController viewController){
        this.viewController = viewController;
    }

    /**
     * Diese Methode wird genau ein mal nach Programmstart aufgerufen.
     * Sie erstellt die leeren Datenstrukturen, zu Beginn nur eine Queue
     */
    public void startProgram() {
        newQueue = new Queue();
        background = new Background();
        viewController.draw(background);
        appleTimer = 1;
        pearTimer = .5;
        bananaTimer = 0;


        p1 = new Player(50, Config.WINDOW_HEIGHT-200,KeyEvent.VK_A,KeyEvent.VK_D);


        viewController.draw(p1);
        viewController.register(p1);



    }

    /**
     * Aufruf mit jeder Frame
     * @param dt Zeit seit letzter Frame
     */
    public void updateProgram(double dt){
        appleTimer += dt;
        pearTimer += dt;
        bananaTimer += dt;
        //System.out.println("funktioniert");
        if(appleTimer > 1.5){
            System.out.println("funktioniert");
            Apple newApple = new Apple(Math.random()*285,10,true,p1);
            newList.append(newApple);
            viewController.draw(newApple);
            appleTimer = 0;
        }


        if(pearTimer > 1.5){
            Pear newPear = new Pear(Math.random()*285,10,true,p1);
            newList.append(newPear);
            viewController.draw(newPear);
            pearTimer = 0;
        }
        if(bananaTimer > 1.5){
            Banana newBanana = new Banana(Math.random()*285,10,true,p1);
            newList.append(newBanana);
            viewController.draw(newBanana);
            bananaTimer = 0;
        }

        newList.toFirst();
        for(int i = 0; newList.hasAccess(); i++){
            GraphicalObject help = (GraphicalObject) newList.getContent();
            if(checkAndHandleCollision(help)){
                wasPicked(help);
                newList.remove();
                help.pickedUp();
            }
            if(help.getY() > 750){
                help = null;
                newList.remove();
            }
            newList.next();
        }


        if(p1.getSpeedBoolean() == true){
            p1.setSpeedTimer(dt);
        }
        if(p1.getSpeedTimer() > 5){
            p1.speedUp(150);
            p1.setFalseSpeed();
            p1.resetSpeedTimer();
        }
        shoot();

    }
    private boolean checkAndHandleCollision(GraphicalObject a){
        return collidesWith(a);
    }


    private void wasPicked(GraphicalObject a){
        if(checkAndHandleCollision(a)){

            newQueue.enqueue(a);
        }
    }
    private boolean collidesWith(GraphicalObject gO) {
        if (p1.getX()  < gO.getX() + gO.getWidth() && p1.getX() + p1.getWidth() > gO.getX() && p1.getY() < gO.getY() + gO.getHeight() && p1.getY() + p1.getWidth() > gO.getY()) {
            return true;
        }
        return false;
    }
    private void shoot(){
        if(p1.getDidShoot() && !newQueue.isEmpty() && p1.canShoot()){
            GraphicalObject go = newQueue.front();
            if(go.getFruitType().equalsIgnoreCase("apple")){
                Apple apple = new Apple(p1.getX()+67,p1.getY(),false,p1);
                viewController.draw(apple);
                newQueue.dequeue();
                apple.setSpeed();
            } else if(go.getFruitType().equalsIgnoreCase("banana")) {
                Banana banana = new Banana(p1.getX()+67,p1.getY(),false,p1);
                viewController.draw(banana);
                newQueue.dequeue();
                banana.setSpeed();
            } else if(go.getFruitType().equalsIgnoreCase("pear")){
                Pear pear = new Pear(p1.getX()+67,p1.getY(),false,p1);
                viewController.draw(pear);
                newQueue.dequeue();
                pear.setSpeed();
            }
            p1.shooted();
        }
    }
}
