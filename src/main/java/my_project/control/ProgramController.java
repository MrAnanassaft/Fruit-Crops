package my_project.control;

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

    private Apple apple01;
    private Apple apple02;
    private Apple apple03;
    private Apple apple04;

    //private PowerApple PowerA2;
    //private PowerApple PowerA1;

    private Pear pear01;
    private Pear pear02;
    private Pear pear03;
    private Pear pear04;
    //private PowerPear PowerP1;
    private Banana banana1;

    private Player p1;
    //private Player p2;
    private Queue newQueue;
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
        newQueue = new Queue<Class<?>>();
        background = new Background();
        viewController.draw(background);
        appleTimer = 1;
        pearTimer = .5;
        bananaTimer = 0;

        /*
        PowerA1 = new PowerApple(100,100);
        viewController.draw(PowerA1);
        PowerA2 = new PowerApple(Math.random()*(300-50) + 50, Math.random()*(Config.WINDOW_HEIGHT-50) + 50);
        viewController.draw(PowerA2);
         */

        /*
        apple01 = new Apple(Math.random()*(300-50) + 50, Math.random()*(Config.WINDOW_HEIGHT-50) + 50);
        viewController.draw(apple01);
        apple02 = new Apple(Math.random()*(300-50) + 50, Math.random()*(Config.WINDOW_HEIGHT-50) + 50);
        viewController.draw(apple02);
        apple03 = new Apple(Math.random()*(300-50) + 50, Math.random()*(Config.WINDOW_HEIGHT-50) + 50);
        viewController.draw(apple03);
        apple04 = new Apple(Math.random()*(300-50) + 50, Math.random()*(Config.WINDOW_HEIGHT-50) + 50);
        viewController.draw(apple04);



        pear01 = new Pear(Math.random()*(300-50) + 50, Math.random()*(Config.WINDOW_HEIGHT-50) + 50);
        viewController.draw(pear01);
        pear02 = new Pear(Math.random()*(300-50) + 50, Math.random()*(Config.WINDOW_HEIGHT-50) + 50);
        viewController.draw(pear02);
        pear03 = new Pear(Math.random()*(300-50) + 50, Math.random()*(Config.WINDOW_HEIGHT-50) + 50);
        viewController.draw(pear03);
        pear04 = new Pear(Math.random()*(300-50) + 50, Math.random()*(Config.WINDOW_HEIGHT-50) + 50);
        viewController.draw(pear04);
        banana1 = new Banana(Math.random()*(300-50) + 50, Math.random()*(Config.WINDOW_HEIGHT-50) + 50);
        viewController.draw(banana1);
         */
        /*
        PowerP1 = new PowerPear(Math.random()*(300-50) + 50, Math.random()*(Config.WINDOW_HEIGHT-50) + 50);
        viewController.draw(PowerP1);
        */


        p1 = new Player(50, Config.WINDOW_HEIGHT-200,KeyEvent.VK_A,KeyEvent.VK_D);
        //p2 = new Player(870, Config.WINDOW_HEIGHT-100,KeyEvent.VK_LEFT,KeyEvent.VK_RIGHT);

        viewController.draw(p1);
        viewController.register(p1);
        //viewController.draw(p2);
        //viewController.register(p2);


    }

    /**
     * Aufruf mit jeder Frame
     * @param dt Zeit seit letzter Frame
     */
    public void updateProgram(double dt){
        //TODO 08 Nachdem Sie die TODOs 01-07 erledigt haben: Setzen Sie um, dass im Falle einer Kollision (siehe TODO 06 bzw. 07) zwischen dem Spieler und dem Apfel bzw. dem Spieler und der Birne, die jumpBack()-Methode von dem Apfel bzw. der Birne aufgerufen wird.
        //Weitere TODOs folgen und werden im Unterricht formuliert. Spätestens nach TODO 08 sollte der Aufbau des Projekts durchdacht werden.
        appleTimer += dt;
        pearTimer += dt;
        bananaTimer += dt;
        if(appleTimer > 1.5){
            Apple newApple = new Apple(Math.random()*285,10);
            newList.append(newApple);
            viewController.draw(newApple);
            appleTimer = 0;
        }
        if(pearTimer > 1.5){
            Pear newPear = new Pear(Math.random()*285,10);
            newList.append(newPear);
            viewController.draw(newPear);
            pearTimer = 0;
        }
        if(bananaTimer > 1.5){
            Banana newBanana = new Banana(Math.random()*285,10);
            newList.append(newBanana);
            viewController.draw(newBanana);
            bananaTimer = 0;
        }
        newList.toFirst();
        for(int i = 0; newList.hasAccess(); i++){
            GraphicalObject help = (GraphicalObject) newList.getContent();
            if(checkAndHandleCollision(help)){
                System.out.println("picked");
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

        /*
        if(p1.getPoints1() > 50){
            p2.winner1();
        }
        if(p2.getPoints1() > 50){
            p2.winner2();
        }
         */

        /*
        if(checkAndHandleCollisionApple(apple01)){
            apple01.jumpBack();
            //p1.setPoints(apple01.getPoints());
            newQueue.enqueue(apple01);
        }
        if(checkAndHandleCollisionApple(apple02)){
            apple02.jumpBack();
            //p1.setPoints(apple02.getPoints());
        }
        if(checkAndHandleCollisionApple(apple03)){
            apple03.jumpBack();
            //p1.setPoints(apple03.getPoints());
        }
        if(checkAndHandleCollisionApple(apple04)){
            apple04.jumpBack();
            //p1.setPoints(apple04.getPoints());
        }


        if(checkAndHandleCollisionPear(pear01)){
            pear01.jumpBack();
            pear01.setPrintBool();
            //p1.setPoints(pear01.getPoints());
        }
        if(checkAndHandleCollisionPear(pear02)){
            pear02.jumpBack();
            pear02.setPrintBool();
            //p1.setPoints(pear02.getPoints());
        }
        if(checkAndHandleCollisionPear(pear03)){
            pear03.jumpBack();
            pear03.setPrintBool();
            //p1.setPoints(pear03.getPoints());
        }
        if(checkAndHandleCollisionPear(pear04)){
            pear04.jumpBack();
            pear04.setPrintBool();
            //p1.setPoints(pear04.getPoints());
        }
        if(checkAndHandleCollisionBanana(banana1)){
            banana1.jumpBack();
            //p1.setPoints(banana1.getPoints());
            apple01.changePointsBack();
            apple02.changePointsBack();
            apple03.changePointsBack();
            apple04.changePointsBack();
            //PowerA1.changePointsBack();
            //PowerA2.changePointsBack();
        }
         */
        /*
            if(checkAndHandleCollisionPear(PowerP1)){
            PowerP1.jumpBack();
            p1.setPoints(PowerP1.getPoints());
            apple01.changePoints();
            apple02.changePoints();
            apple03.changePoints();
            apple04.changePoints();
            PowerA1.changePoints();
            PowerA2.changePoints();
        }

        if(checkAndHandleCollisionApple(PowerA2)){
            PowerA2.jumpBack();
            p1.speedUp(PowerA2.getSpeedBuff()+ p1.getSpeed());
            p1.setTrueSpeed();
            p1.resetSpeedTimer();
            p1.setPoints(PowerA2.getPoints());
        }

        if(checkAndHandleCollisionApple(PowerA1)){
            PowerA1.jumpBack();
            p1.speedUp(PowerA1.getSpeedBuff()+ p1.getSpeed());
            p1.setTrueSpeed();
            p1.resetSpeedTimer();
            p1.setPoints(PowerA1.getPoints());
        }
         */
        if(p1.getSpeedBoolean() == true){
            p1.setSpeedTimer(dt);
        }
        if(p1.getSpeedTimer() > 5){
            p1.speedUp(150);
            p1.setFalseSpeed();
            p1.resetSpeedTimer();
        }
        System.out.println(p1.getX() + "    " + p1.getY());
        /*
        if(checkAndHandleCollision2Apple(apple01)){
            apple01.jumpBack();
            //p2.setPoints(apple01.getPoints());
        }
        if(checkAndHandleCollision2Apple(apple02)){
            apple02.jumpBack();
            //p2.setPoints(apple02.getPoints());
        }
        if(checkAndHandleCollision2Apple(apple03)){
            apple03.jumpBack();
            //p2.setPoints(apple03.getPoints());
        }
        if(checkAndHandleCollision2Apple(apple04)){
            apple04.jumpBack();
            //p2.setPoints(apple04.getPoints());
        }


        if(checkAndHandleCollision2Pear(pear01)){
            pear01.jumpBack();
            pear01.setPrintBool();
            //p2.setPoints(pear01.getPoints());
        }
        if(checkAndHandleCollision2Pear(pear02)){
            pear02.jumpBack();
            pear02.setPrintBool();
            //p2.setPoints(pear02.getPoints());
        }
        if(checkAndHandleCollision2Pear(pear03)){
            pear03.jumpBack();
            pear03.setPrintBool();
            //p2.setPoints(pear03.getPoints());
        }
        if(checkAndHandleCollision2Pear(pear04)){
            pear04.jumpBack();
            pear04.setPrintBool();
            //p2.setPoints(pear04.getPoints());
        }

        if(checkAndHandleCollision2Banana(banana1)){
            banana1.jumpBack();
            //p2.setPoints(banana1.getPoints());
            apple01.changePointsBack();
            apple02.changePointsBack();
            apple03.changePointsBack();
            apple04.changePointsBack();
            //PowerA1.changePointsBack();
            //PowerA2.changePointsBack();
        }
         */




        /*
        if(checkAndHandleCollision2Pear(PowerP1)){
            PowerP1.jumpBack();
            p2.setPoints(PowerP1.getPoints());
            apple01.changePoints();
            apple02.changePoints();
            apple03.changePoints();
            apple04.changePoints();
            PowerA1.changePoints();
            PowerA2.changePoints();
        }


        if(checkAndHandleCollision2Apple(PowerA2)){
            PowerA2.jumpBack();
            p2.speedUp(PowerA2.getSpeedBuff()+ p2.getSpeed());
            p2.setTrueSpeed();
            p2.resetSpeedTimer();
            p2.setPoints(PowerA2.getPoints());
        }

        if(checkAndHandleCollision2Apple(PowerA1)){
            PowerA1.jumpBack();
            p2.speedUp(PowerA1.getSpeedBuff()+ p2.getSpeed());
            p2.setTrueSpeed();
            p2.resetSpeedTimer();
            p2.setPoints(PowerA1.getPoints());
        }
         */
        /*
        if(p2.getSpeedBoolean() == true){
            p2.setSpeedTimer(dt);
        }
        if(p2.getSpeedTimer() > 5){
            p2.speedUp(150);
            p2.setFalseSpeed();
            p2.resetSpeedTimer();
        }
         */


    }
    private boolean checkAndHandleCollision(GraphicalObject a){
        return collidesWith(a);
    }

    //private boolean checkAndHandleCollisionPear(Pear p){
        //return p.collidesWith(p1);
    //}
    //private boolean checkAndHandleCollisionBanana(Banana b){
       // return b.collidesWith(p1);
    //}
    /*
    private boolean checkAndHandleCollision2Apple(Apple a2){
        return a2.collidesWith(p2);
    }
    private boolean checkAndHandleCollision2Pear(Pear p2){
        return p2.collidesWith(this.p2);
    }
    private boolean checkAndHandleCollision2Banana(Banana b2){
        return b2.collidesWith(p2);
    }

     */
    //TODO 06 Fügen Sie eine Methode checkAndHandleCollision(Apple a) hinzu. Diese gibt true zurück, falls das Apple-Objekt mit dem Player-Objekt kollidiert. Nutzen Sie hierzu die collidesWith-Methode der Klasse GraphicalObject.

    //TODO 07 Fügen Sie eine Methode checkAndHandleCollision(Pear p) hinzu. Diese gibt true zurück, falls das Pear-Objekt mit dem Player-Objekt kollidiert. Nutzen Sie hierzu die collidesWith-Methode der Klasse GraphicalObject.
    private void wasPicked(GraphicalObject a){
        if(checkAndHandleCollision(a)){
            newQueue.enqueue(a);
        }
    }
    private boolean collidesWith(GraphicalObject gO) {
        if (p1.getX()  < gO.getX() - gO.getWidth() && p1.getX() + p1.getWidth()> gO.getX() && p1.getY() < gO.getY() + gO.getHeight() && p1.getY() + p1.getHeight() > gO.getY()) {
            return true;
        }
        //System.out.println("tried");
        return false;
    }
}
