package my_project.control;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.GraphicalObject;
import my_project.Config;
import my_project.model.Apple;
import my_project.model.Pear;
import my_project.model.Player;
import my_project.model.PowerApple;

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
    private Apple apple05;

    private Pear pear01;
    private Pear pear02;
    private Pear pear03;
    private Pear pear04;
    private Pear pear05;

    private PowerApple PowerA1;

    private Player player01;

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

        PowerA1 = new PowerApple(100,100);
        viewController.draw(PowerA1);

        apple01 = new Apple(Math.random()*(Config.WINDOW_WIDTH-50) + 50, Math.random()*(Config.WINDOW_HEIGHT-50) + 50);
        viewController.draw(apple01);
        apple02 = new Apple(Math.random()*(Config.WINDOW_WIDTH-50) + 50, Math.random()*(Config.WINDOW_HEIGHT-50) + 50);
        viewController.draw(apple02);
        apple03 = new Apple(Math.random()*(Config.WINDOW_WIDTH-50) + 50, Math.random()*(Config.WINDOW_HEIGHT-50) + 50);
        viewController.draw(apple03);
        apple04 = new Apple(Math.random()*(Config.WINDOW_WIDTH-50) + 50, Math.random()*(Config.WINDOW_HEIGHT-50) + 50);
        viewController.draw(apple04);
        apple05 = new Apple(Math.random()*(Config.WINDOW_WIDTH-50) + 50, Math.random()*(Config.WINDOW_HEIGHT-50) + 50);
        viewController.draw(apple05);


        pear01 = new Pear(Math.random()*(Config.WINDOW_WIDTH-50) + 50, Math.random()*(Config.WINDOW_HEIGHT-50) + 50);
        viewController.draw(pear01);
        pear02 = new Pear(Math.random()*(Config.WINDOW_WIDTH-50) + 50, Math.random()*(Config.WINDOW_HEIGHT-50) + 50);
        viewController.draw(pear02);
        pear03 = new Pear(Math.random()*(Config.WINDOW_WIDTH-50) + 50, Math.random()*(Config.WINDOW_HEIGHT-50) + 50);
        viewController.draw(pear03);
        pear04 = new Pear(Math.random()*(Config.WINDOW_WIDTH-50) + 50, Math.random()*(Config.WINDOW_HEIGHT-50) + 50);
        viewController.draw(pear04);
        pear05 = new Pear(Math.random()*(Config.WINDOW_WIDTH-50) + 50, Math.random()*(Config.WINDOW_HEIGHT-50) + 50);
        viewController.draw(pear05);

        player01 = new Player(50, Config.WINDOW_HEIGHT-100);
        viewController.draw(player01);
        viewController.register(player01);

    }

    /**
     * Aufruf mit jeder Frame
     * @param dt Zeit seit letzter Frame
     */
    public void updateProgram(double dt){
        //TODO 08 Nachdem Sie die TODOs 01-07 erledigt haben: Setzen Sie um, dass im Falle einer Kollision (siehe TODO 06 bzw. 07) zwischen dem Spieler und dem Apfel bzw. dem Spieler und der Birne, die jumpBack()-Methode von dem Apfel bzw. der Birne aufgerufen wird.
        //Weitere TODOs folgen und werden im Unterricht formuliert. Spätestens nach TODO 08 sollte der Aufbau des Projekts durchdacht werden.
        if(checkAndHandleCollision(apple01)){
            apple01.jumpBack();
        }
        if(checkAndHandleCollision(apple02)){
            apple02.jumpBack();
        }
        if(checkAndHandleCollision(apple03)){
            apple03.jumpBack();
        }
        if(checkAndHandleCollision(apple04)){
            apple04.jumpBack();
        }
        if(checkAndHandleCollision(apple05)){
            apple05.jumpBack();
        }

        if(checkAndHandleCollision(pear01)){
            pear01.jumpBack();
        }
        if(checkAndHandleCollision(pear02)){
            pear02.jumpBack();
        }
        if(checkAndHandleCollision(pear03)){
            pear03.jumpBack();
        }
        if(checkAndHandleCollision(pear04)){
            pear04.jumpBack();
        }
        if(checkAndHandleCollision(pear05)){
            pear05.jumpBack();
        }

        if(checkAndHandleCollision(PowerA1)){
            PowerA1.jumpBack();
            player01.speedUp(PowerA1.getSpeedBuff()+ player01.getSpeed());
            player01.setTrueSpeedHIHI();
        }
        if(player01.getSpeedHIHI() == true){
            player01.setSpeedTimer(dt);
        }
        if(player01.getSpeedTimer() > 5){
            player01.speedUp(150);
            player01.setFalseSpeedHIHI();
            player01.resetSpeedTimer();

        }
        System.out.println(player01.getSpeed());
    }
    private boolean checkAndHandleCollision(Apple a){
        return a.collidesWith(player01);
    }
    //TODO 06 Fügen Sie eine Methode checkAndHandleCollision(Apple a) hinzu. Diese gibt true zurück, falls das Apple-Objekt mit dem Player-Objekt kollidiert. Nutzen Sie hierzu die collidesWith-Methode der Klasse GraphicalObject.

    //TODO 07 Fügen Sie eine Methode checkAndHandleCollision(Pear p) hinzu. Diese gibt true zurück, falls das Pear-Objekt mit dem Player-Objekt kollidiert. Nutzen Sie hierzu die collidesWith-Methode der Klasse GraphicalObject.

    private boolean checkAndHandleCollision(Pear p){
        return p.collidesWith(player01);
    }
}
