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
    private List<GraphicalObject> newList = new List<>();
    private List<GraphicalObject> newList2 = new List<>();
    private double appleTimer;
    private double pearTimer;
    private double bananaTimer;
    private boolean gameActive = true;

    private Pig pig1;



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


        p1 = new Player(50, Config.WINDOW_HEIGHT-200, KeyEvent.VK_A, KeyEvent.VK_D);



        viewController.draw(pig1);


        pig1 = new Pig(1000, 600, viewController);
        viewController.draw(pig1);
        viewController.draw(p1);
        viewController.register(p1);
    }

    /**
     * Aufruf mit jeder Frame
     * @param dt Zeit seit letzter Frame
     */
    public void updateProgram(double dt){
        if(gameActive){
            appleTimer += dt;
            pearTimer += dt;
            bananaTimer += dt;
            //System.out.println("funktioniert");
            if(appleTimer > 1.5){
                Apple newApple = new Apple(Math.random()*285,10,true,p1);
                newList.append(newApple);
                viewController.draw(newApple);
                appleTimer = 0;
            }

            newList2.toFirst();
            for (int i = 0; newList2.hasAccess(); i++) {
                GraphicalObject help = newList2.getContent();
                if (checkAndHandleCollisionWithPig(help)) {
                    newList2.remove();
                    // Das Schwein wurde getroffen, rufen Sie die entsprechende Methode auf
                    if(help.getFruitType().equalsIgnoreCase("banana")){
                        pig1.hitByBanana((Banana)help);
                    }
                    if(help.getFruitType().equalsIgnoreCase("apple")){
                        pig1.hitByApple((Apple)help);
                    }
                    if(help.getFruitType().equalsIgnoreCase("pear")){
                        pig1.hitByPear((Pear)help);
                    }
                    help = null;
                }
                newList2.next();
            }


            if(pearTimer > 1.5){
                Pear newPear = new Pear(Math.random()*285,10,true,p1);
                newList.append(newPear);
                viewController.draw(newPear);
                pearTimer = 0;
            }
            if(bananaTimer > 1.5){
                Pig pig1 = new Pig(1000, 600);
                Banana banana = new Banana(Math.random() * 285, 10, true, p1, pig1);
                newList.append(banana);
                viewController.draw(banana);
                bananaTimer = 0;
            }

            newList.toFirst();
            for(int i = 0; newList.hasAccess(); i++){
                GraphicalObject help =  newList.getContent();
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

        if(pig1.getScore() > 20){
            p1.setWon();
            gameActive = false;
            newList2.toFirst();
            while(newList2.hasAccess()){
                viewController.removeDrawable(newList2.getContent());
                newList2.next();
            }
            newList.toFirst();
            while(newList.hasAccess()){
                viewController.removeDrawable(newList.getContent());
                newList.next();
            }
        }
    }
    private boolean checkAndHandleCollision(GraphicalObject a){
        return collidesWith(a);
    }
    private boolean checkAndHandleCollisionWithPig(GraphicalObject b){
        return collidesWithPig(b);
    }

    private void wasPicked(GraphicalObject a){
        if(checkAndHandleCollision(a)){

            newQueue.enqueue(a);
        }
    }
    private boolean collidesWith(GraphicalObject gO) {
        if (gO != null && p1.getX() < gO.getX() + gO.getWidth() && p1.getX() + p1.getWidth() > gO.getX() &&
                p1.getY() < gO.getY() + gO.getHeight() && p1.getY() + p1.getHeight() > gO.getY()) {
            return true;
        }
        return false;
    }
    private boolean collidesWithPig(GraphicalObject gO){
        if (gO != null && pig1.getX() < gO.getX() + gO.getWidth() && pig1.getX() + pig1.getWidth() > gO.getX() &&
                pig1.getY() < gO.getY() + gO.getHeight() && pig1.getY() + pig1.getHeight() > gO.getY()) {
            return true;
        }
        return false;
    }
    private void shoot() {
        if (p1.getDidShoot() && !newQueue.isEmpty() && p1.canShoot()) {
            GraphicalObject go = newQueue.front();

            //double x = go.getX() + go.getWidth(); // Übernehme die X-Position des Originalobjekts
            //double y = go.getY(); // Übernehme die Y-Position des Originalobjekts

            if (go.getFruitType().equalsIgnoreCase("apple")) {
                Apple apple = new Apple(p1.getX()+67, p1.getY(), false, p1);
                viewController.draw(apple);
                newQueue.dequeue();
                apple.setSpeed();
                newList2.append(apple);
            } else if (go.getFruitType().equalsIgnoreCase("banana")) {
                Banana banana = new Banana(p1.getX()+67, p1.getY(), false, p1, pig1);
                viewController.draw(banana);
                newQueue.dequeue();
                banana.setSpeed();
                newList2.append(banana);
            } else if (go.getFruitType().equalsIgnoreCase("pear")) {
                Pear pear = new Pear(p1.getX()+67, p1.getY(), false, p1);
                viewController.draw(pear);
                newQueue.dequeue();
                pear.setSpeed();
                newList2.append(pear);
            }
            p1.shooted();
        }
    }


}
