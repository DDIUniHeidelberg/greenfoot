import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/** 
 * Klasse Verzweiger; Projekt 4_Kara_Verzweiger
 * Ein Anfangskara, der Verzweigungen zeigt 
 * @author FB-Team RP Karlsruhe (thh)
 * @version 11/2010 
 */

public class Verzweiger extends Kara {

    /**
     * Als Vorbereitung erhaelt der Kaefer 10 Kleeblaetter,
     * damit er sie spaeter pflanzen(=ablegen) kann.
     */ 
    public void bereiteVor()  {
        setAnzahlBlaetter(10);
    }
    
        
    /** 
     * Der Kaefer tauscht aus. Wo ein Kleeblatt liegt, hebt er es auf.
     * Wo keines liegt, legt er eines ab, wenn er noch Blaetter 
     * in seinem Vorrat hat.
     */
    public void tausche() {
        if (istBlattDarunter()) { 
            hebeAuf();
        } 
        else {
            legeAb();
        }
    }
    
    /** 
     * Der Kaefer macht mehrere Dinge. Beschreibe in diesem Kommentar. 
     */
    public void tauscheUndVor() {
        tausche();
        if (istVorneFrei()) { 
            einsVor();
        }
        // else ist nicht noetig, weil in dem Fall nichts gemacht wird!                 
    }

    /** 
     * Der Kaefer geht einen Schritt vor, wenn es moeglich ist.
     * Andernfalls dreht er nach links.
     * AB 4 - Nr 3 
     */
    public void vorOderLinks() {
        // if ( ?? ) {       
             // deine Aufgabe 
             // Wo endet was? 
    }
    
} 
// Ende des Programms