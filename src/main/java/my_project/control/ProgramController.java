package my_project.control;

import KAGO_framework.control.ViewController;
import my_project.Config;
import my_project.model.*;
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

    private PowerApple PowerA2;
    private PowerApple PowerA1;

    private Pear pear01;
    private Pear pear02;
    private Pear pear03;
    private Pear pear04;
    private PowerPear PowerP1;
    private Banana banana1;

    private Player player01;
    private Player player02;

    private Background background;

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

        background = new Background();
        viewController.draw(background);

        PowerA1 = new PowerApple(100,100);
        viewController.draw(PowerA1);
        PowerA2 = new PowerApple(Math.random()*(Config.WINDOW_WIDTH-50) + 50, Math.random()*(Config.WINDOW_HEIGHT-50) + 50);
        viewController.draw(PowerA2);

        apple01 = new Apple(Math.random()*(Config.WINDOW_WIDTH-50) + 50, Math.random()*(Config.WINDOW_HEIGHT-50) + 50);
        viewController.draw(apple01);
        apple02 = new Apple(Math.random()*(Config.WINDOW_WIDTH-50) + 50, Math.random()*(Config.WINDOW_HEIGHT-50) + 50);
        viewController.draw(apple02);
        apple03 = new Apple(Math.random()*(Config.WINDOW_WIDTH-50) + 50, Math.random()*(Config.WINDOW_HEIGHT-50) + 50);
        viewController.draw(apple03);
        apple04 = new Apple(Math.random()*(Config.WINDOW_WIDTH-50) + 50, Math.random()*(Config.WINDOW_HEIGHT-50) + 50);
        viewController.draw(apple04);



        pear01 = new Pear(Math.random()*(Config.WINDOW_WIDTH-50) + 50, Math.random()*(Config.WINDOW_HEIGHT-50) + 50);
        viewController.draw(pear01);
        pear02 = new Pear(Math.random()*(Config.WINDOW_WIDTH-50) + 50, Math.random()*(Config.WINDOW_HEIGHT-50) + 50);
        viewController.draw(pear02);
        pear03 = new Pear(Math.random()*(Config.WINDOW_WIDTH-50) + 50, Math.random()*(Config.WINDOW_HEIGHT-50) + 50);
        viewController.draw(pear03);
        pear04 = new Pear(Math.random()*(Config.WINDOW_WIDTH-50) + 50, Math.random()*(Config.WINDOW_HEIGHT-50) + 50);
        viewController.draw(pear04);

        PowerP1 = new PowerPear(Math.random()*(Config.WINDOW_WIDTH-50) + 50, Math.random()*(Config.WINDOW_HEIGHT-50) + 50);
        viewController.draw(PowerP1);

        banana1 = new Banana(Math.random()*(Config.WINDOW_WIDTH-50) + 50, Math.random()*(Config.WINDOW_HEIGHT-50) + 50);
        viewController.draw(banana1);

        player01 = new Player(50, Config.WINDOW_HEIGHT-100,KeyEvent.VK_A,KeyEvent.VK_D);
        player02 = new Player(870, Config.WINDOW_HEIGHT-100,KeyEvent.VK_LEFT,KeyEvent.VK_RIGHT);

        viewController.draw(player01);
        viewController.register(player01);
        viewController.draw(player02);
        viewController.register(player02);


    }

    /**
     * Aufruf mit jeder Frame
     * @param dt Zeit seit letzter Frame
     */
    public void updateProgram(double dt){
        //TODO 08 Nachdem Sie die TODOs 01-07 erledigt haben: Setzen Sie um, dass im Falle einer Kollision (siehe TODO 06 bzw. 07) zwischen dem Spieler und dem Apfel bzw. dem Spieler und der Birne, die jumpBack()-Methode von dem Apfel bzw. der Birne aufgerufen wird.
        //Weitere TODOs folgen und werden im Unterricht formuliert. Spätestens nach TODO 08 sollte der Aufbau des Projekts durchdacht werden.
        if(player01.getPoints1() > 50){
            player02.winner1();
        }
        if(player02.getPoints1() > 50){
            player02.winner2();
        }

        if(checkAndHandleCollision(apple01)){
            apple01.jumpBack();
            player01.setPoints(apple01.getPoints());
        }
        if(checkAndHandleCollision(apple02)){
            apple02.jumpBack();
            player01.setPoints(apple02.getPoints());
        }
        if(checkAndHandleCollision(apple03)){
            apple03.jumpBack();
            player01.setPoints(apple03.getPoints());
        }
        if(checkAndHandleCollision(apple04)){
            apple04.jumpBack();
            player01.setPoints(apple04.getPoints());
        }


        if(checkAndHandleCollision(pear01)){
            pear01.jumpBack();
            pear01.setPrintBool();
            player01.setPoints(pear01.getPoints());
        }
        if(checkAndHandleCollision(pear02)){
            pear02.jumpBack();
            pear02.setPrintBool();
            player01.setPoints(pear02.getPoints());
        }
        if(checkAndHandleCollision(pear03)){
            pear03.jumpBack();
            pear03.setPrintBool();
            player01.setPoints(pear03.getPoints());
        }
        if(checkAndHandleCollision(pear04)){
            pear04.jumpBack();
            pear04.setPrintBool();
            player01.setPoints(pear04.getPoints());
        }
        if(checkAndHandleCollision(banana1)){
            banana1.jumpBack();
            player01.setPoints(banana1.getPoints());
            apple01.changePointsBack();
            apple02.changePointsBack();
            apple03.changePointsBack();
            apple04.changePointsBack();
            PowerA1.changePointsBack();
            PowerA2.changePointsBack();
        }
        if(checkAndHandleCollision(PowerP1)){
            PowerP1.jumpBack();
            player01.setPoints(PowerP1.getPoints());
            apple01.changePoints();
            apple02.changePoints();
            apple03.changePoints();
            apple04.changePoints();
            PowerA1.changePoints();
            PowerA2.changePoints();
        }

        if(checkAndHandleCollision(PowerA2)){
            PowerA2.jumpBack();
            player01.speedUp(PowerA2.getSpeedBuff()+ player01.getSpeed());
            player01.setTrueSpeed();
            player01.resetSpeedTimer();
            player01.setPoints(PowerA2.getPoints());
        }

        if(checkAndHandleCollision(PowerA1)){
            PowerA1.jumpBack();
            player01.speedUp(PowerA1.getSpeedBuff()+ player01.getSpeed());
            player01.setTrueSpeed();
            player01.resetSpeedTimer();
            player01.setPoints(PowerA1.getPoints());
        }
        if(player01.getSpeedBoolean() == true){
            player01.setSpeedTimer(dt);
        }
        if(player01.getSpeedTimer() > 5){
            player01.speedUp(150);
            player01.setFalseSpeed();
            player01.resetSpeedTimer();
        }



        if(checkAndHandleCollision2(apple01)){
            apple01.jumpBack();
            player02.setPoints(apple01.getPoints());
        }
        if(checkAndHandleCollision2(apple02)){
            apple02.jumpBack();
            player02.setPoints(apple02.getPoints());
        }
        if(checkAndHandleCollision2(apple03)){
            apple03.jumpBack();
            player02.setPoints(apple03.getPoints());
        }
        if(checkAndHandleCollision2(apple04)){
            apple04.jumpBack();
            player02.setPoints(apple04.getPoints());
        }


        if(checkAndHandleCollision2(pear01)){
            pear01.jumpBack();
            pear01.setPrintBool();
            player02.setPoints(pear01.getPoints());
        }
        if(checkAndHandleCollision2(pear02)){
            pear02.jumpBack();
            pear02.setPrintBool();
            player02.setPoints(pear02.getPoints());
        }
        if(checkAndHandleCollision2(pear03)){
            pear03.jumpBack();
            pear03.setPrintBool();
            player02.setPoints(pear03.getPoints());
        }
        if(checkAndHandleCollision2(pear04)){
            pear04.jumpBack();
            pear04.setPrintBool();
            player02.setPoints(pear04.getPoints());
        }

        if(checkAndHandleCollision2(banana1)){
            banana1.jumpBack();
            player02.setPoints(banana1.getPoints());
            apple01.changePointsBack();
            apple02.changePointsBack();
            apple03.changePointsBack();
            apple04.changePointsBack();
            PowerA1.changePointsBack();
            PowerA2.changePointsBack();
        }
        if(checkAndHandleCollision2(PowerP1)){
            PowerP1.jumpBack();
            player02.setPoints(PowerP1.getPoints());
            apple01.changePoints();
            apple02.changePoints();
            apple03.changePoints();
            apple04.changePoints();
            PowerA1.changePoints();
            PowerA2.changePoints();
        }


        if(checkAndHandleCollision2(PowerA2)){
            PowerA2.jumpBack();
            player02.speedUp(PowerA2.getSpeedBuff()+ player02.getSpeed());
            player02.setTrueSpeed();
            player02.resetSpeedTimer();
            player02.setPoints(PowerA2.getPoints());
        }

        if(checkAndHandleCollision2(PowerA1)){
            PowerA1.jumpBack();
            player02.speedUp(PowerA1.getSpeedBuff()+ player02.getSpeed());
            player02.setTrueSpeed();
            player02.resetSpeedTimer();
            player02.setPoints(PowerA1.getPoints());
        }
        if(player02.getSpeedBoolean() == true){
            player02.setSpeedTimer(dt);
        }
        if(player02.getSpeedTimer() > 5){
            player02.speedUp(150);
            player02.setFalseSpeed();
            player02.resetSpeedTimer();
        }

    }
    private boolean checkAndHandleCollision(Apple a){
        return a.collidesWith(player01);
    }
    private boolean checkAndHandleCollision(Pear p){
        return p.collidesWith(player01);
    }
    private boolean checkAndHandleCollision(Banana b){
        return b.collidesWith(player01);
    }

    private boolean checkAndHandleCollision2(Apple a2){
        return a2.collidesWith(player02);
    }
    private boolean checkAndHandleCollision2(Pear p2){
        return p2.collidesWith(player02);
    }
    private boolean checkAndHandleCollision2(Banana b2){
        return b2.collidesWith(player02);
    }
    //TODO 06 Fügen Sie eine Methode checkAndHandleCollision(Apple a) hinzu. Diese gibt true zurück, falls das Apple-Objekt mit dem Player-Objekt kollidiert. Nutzen Sie hierzu die collidesWith-Methode der Klasse GraphicalObject.

    //TODO 07 Fügen Sie eine Methode checkAndHandleCollision(Pear p) hinzu. Diese gibt true zurück, falls das Pear-Objekt mit dem Player-Objekt kollidiert. Nutzen Sie hierzu die collidesWith-Methode der Klasse GraphicalObject.

}
