import greenfoot.*;
import javax.swing.*;

/**
 * Die Klasse sorgt fuer die Darstellung der einen KARA-Wiese, 
 * auf der sich Kaefer tummeln koennen.
 * Die Methoden dienen zum Abfragen bestimmter Zustandswerte der Wiese.
 * @author thh 
 * @version 0.4 (15.11.09/4.4./14.2./30.6.08) fuer 3_Kara_Wdh ff
 */

public class KaraWiese extends World {

/** Erzeugt eine neue Wiese mit 13 x 11 Kacheln */
    protected KaraWiese() {
        this(13,11);
    }

/** Erzeugt eine neue Wiese in der angegebenen Groesse: Spalte x Reihe */
    public KaraWiese(int spalten, int reihen) {
        super(spalten < 1 ? 10 : spalten, reihen < 1 ? 10 : reihen, 35);
        setBackground("kachel1.png");
        setActOrder(Kara.class);
        setPaintOrder(Kara.class, Blatt.class, Baum.class);
    }

/* --- Infos zu den Objekten der Welt --*/
/** liefert die Anzahl an Reihen in der Wiese */
    public int getAnzahlReihen() {
        return getHeight();
    }

/** liefert die Anzahl an Spalten in der Wiese */
    public int getAnzahlSpalten() {
        return getWidth();
    }

/** meldet, ob sich auf der Kachel (spalte/reihe) ein Baum befindet; */
    public boolean istBaumDa(int spalte, int reihe) {
        return getObjectsAt(spalte, reihe, Baum.class).size() > 0;
    }
    
/** meldet, ob auf der Kachel (spalte/reihe) ein Blatt liegt;
 *  ausserhalb der Wiese wird immer false gemeldet. */
    public boolean istBlattDa(int spalte, int reihe) {
        return getObjectsAt(spalte, reihe, Blatt.class).size() > 0;
    }

/** meldet, ob auf der Kachel (spalte/reihe) ein Kaefer steht;
 *  ausserhalb der Wiese wird immer false gemeldet. */
    public boolean istKaeferDa(int spalte, int reihe) {
        return getObjectsAt(spalte, reihe, Kara.class).size() > 0;
    }
    
/** liefert die Gesamtzahl an Blaettern, die auf der Wiese verteilt sind */
    public int getAnzahlBlaetter() {
        return getObjects(Blatt.class).size();
    }

/** liefert die Gesamtzahl an Baeumen, die auf der Wiese verteilt sind */
    public int getAnzahlBaeume() {
        return getObjects(Baum.class).size();
    }

/** liefert die Gesamtzahl aller Karas, die auf der Wiese herumwuseln */
    public int getAnzahlKaefer() {
        return getObjects(Kara.class).size();
    }

//----- Warnungen und Abbruch ---
/** Abbruchmoeglichkeit  */
    public void warne(String text, Actor actor) {
        int erg = JOptionPane.showConfirmDialog(null, text, "Warnung!",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
        if (erg == 0) {
            return;
        } else {   
            removeObject(actor); // Notbremse
            Greenfoot.stop();    // nur falls Animation laeuft
            repaint();
        }
    }
    
//------ Hilfsroutinen fuer die Wiese -----*/
    
/** Die Wiese leer machen. */
    public void entferneAlles() {
        removeObjects(getObjects(null));
    }

/** Legt eine vollstaendige Baummauer um die Wiese herum */
    public void einmauern() {
        int br = getWidth();
        int hoe = getHeight();
        int gesZahl = 2*(br+hoe);
        Baum randbaum;
        for (int i = 0; i < br; i++) {
            randbaum = new Baum();
            addObject(randbaum, i, 0);
            randbaum = new Baum();
            addObject(randbaum, i, hoe-1);
        }
        for (int i = 1; i < hoe-1; i++) {
            randbaum = new Baum();  
            addObject(randbaum, 0, i);
            randbaum = new Baum();
            addObject(randbaum, br-1, i);
        }
    }
    

/** verteilt einige Blaetter sowie drei Kaefer in der Wiese */
    public void setze3Kaefer() {
        entferneAlles();
        Kara k1 = new Kara();
        k1.rechtsUm();
        addObject(k1, 0, 0);

        Kara k2 = new Kara();
        k2.linksUm();
        addObject(k2, getWidth() - 1, getHeight() - 1);

        Kara k3 = new Kara();
        addObject(k3, getWidth() / 2, getHeight() / 2);

        verteileBlaetter(getWidth() * getHeight() / 4);
    }
    
/** Platziert zufaellig eine angegebene Anzahl von Kleeblaettern auf der Wiese */
    public void verteileBlaetter(int wieviele) {
        for (int i = 0; i < wieviele; i++) {
            Blatt blatt = new Blatt();
            int x = Greenfoot.getRandomNumber(getWidth());
            int y = Greenfoot.getRandomNumber(getHeight());
            addObject(blatt, x, y);
        }
    }
 
}