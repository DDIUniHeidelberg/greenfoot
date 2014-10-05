import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/** Klasse: Spielwiese 
 *  fuer die ersten Uebungsaufgaben z.B. fuer BabyKara
 *  @author thh
 *  @version  15.11.09 (3_Kara_Wdh)
 */
public class Spielwiese extends KaraWiese
{

    /** Konstruktor der Spielwiese. */
    public Spielwiese()    {    
        super();
        macheAnfangsgegend();   
    }
    
    
    /** Anfangssituation herstellen: 3 Blaetter und 2 Baeume werden verteilt */
    public void macheAnfangsgegend() { 
        entferneAlles();
        Blatt bl1 = new Blatt();  addObject(bl1, 7, 6);  
        Blatt bl2 = new Blatt();  addObject(bl2, 4, 6);
        Blatt bl3 = new Blatt();  addObject(bl3, 8, 3);
        
        Baum b1 = new Baum();   addObject(b1, 9, 6);
        Baum b2 = new Baum();   addObject(b2, 8, 2);        
    }   
    
    /** Stadtmauer aussen herum aufbauen */    
    public void macheStadtmauer() { 
        entferneAlles();
        einmauern();
    }

    
}
