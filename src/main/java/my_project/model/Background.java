package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
//import my_project.model.Player;

import java.awt.*;


/**
 * Die Background-Klasse stellt die Umsetzung eines Objekts für den Hintergrund des Apple-Games dar.
 * Hier werden sogenannte Arrays (zu deutsch: Felder) verwendet, die im Unterricht noch nicht behandelt wurden.
 */

public class Background extends GraphicalObject {




    //Referenzen
    String chosenPhrase;
    String[] phrases = new String[]{"\"An apple a day keeps the doctor away!\" - England",
            "\"Eat an apple on going to bed, and you’ll keep the doctor from earning his bread.\" - Wales",
            "\"Ein Apfel am Tag, Arzt gespart!\" - Deutschland",
            "\"Una mela al giorno toglie il medico di torno.\" - Italien",
            "\"Jedna jabuka na dan, i doktor ti neće ući u stan.\" - Serbien",
            "\"Günde bir elma Doktoru uzak tutar.\" - Türkei",
            "\"Rojek sêvek doktor dûr dike\" - Kurdisch",
            "\"تفاحة على الريق تغنيك عن الطبيب\" - Arabisch"};


    public Background(){

        chosenPhrase = phrases[(int)(Math.random()*phrases.length)];
    }

    @Override
    public void draw(DrawTool drawTool) {

        drawTool.setCurrentColor(new Color(13, 180, 245));
        drawTool.drawFilledRectangle(0,0,1600,512);
        drawTool.setCurrentColor(new Color(43, 183, 24));
        drawTool.drawFilledRectangle(0,512,1600,512);

    }

    @Override
    public void update(double dt) {

    }


}