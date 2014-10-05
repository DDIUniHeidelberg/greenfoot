import greenfoot.*;

import java.util.List;
import java.awt.Color;
import javax.swing.*;

/**
 * Beschreibung von Kaefern fuer unsere Greenfoot-Miniwelt<br> 
 * Basisklasse KARA fuer Uebungen<br> 
 * Der Quelltext ist nicht fuer die Anfaenger gedacht!!
 * @author thh, mt, dz
 * @version 0.412 - 5.2.2010
 */

public class Kara extends Actor {

    public final static int NORD = 3; // neu; 15.11.;passend zu rotation!!
    public final static int OST = 0;  // Blickrichtung Ost 
    public final static int SUED = 1;
    public final static int WEST = 2;
    private int blickrichtung;
    private int anzBlaetter;
    private GreenfootImage kaeferImage;

    /** 
     * Konstruktor zum Erzeugen eines neuen Kaefers mit Blickrichtung
     * OST = rechts; hat bisher kein Blatt im Vorrat 
     */
    public Kara() {
        kaeferImage = getImage();
        setBlickrichtung(OST);
        anzBlaetter = 0;
    }

    /** 
     * Kein Act bei dieser Oberklasse aller Uebungskaefer!  
     */
    //public void act() {
    //Simulationsumgebung nicht genutzt!
    //}

    // Anfragen = Sensoren
    // im Kontextmenue verfuegbar sind die Anfragen:
    // istBlattDarunter()  istVorratLeer()  istVorneFrei()
    // sowie nun auch:
    // istBaumVorne()  istBaumRechts()  istBaumLinks()   istKaraVorne()
    // Pilze werden nicht genutzt.
  
    /** 
     * Liefert genau dann true, wenn sich in Blickrichtung vor dem aufgerufenen Kaefer 
     * ein Baum befindet. Identifizierung der Raender wird beachtet.
     */
    public boolean istBaumVorne() {
        int br = getWorld().getWidth();
        int hoe = getWorld().getHeight();
        int x = getX();
        int y = getY();
        switch (blickrichtung) {
            case SUED:
                y = (y + 1) % hoe;
                break;
            case OST:
                x = (x + 1) % br;
                break;
            case NORD:
                y = (y - 1 + hoe) % hoe;
                break;
            case WEST:
                x = (x - 1 + br) % br;
                break;
        }
        return getWorld().getObjectsAt(x, y, Baum.class).size() != 0;
    }

    /**
     * Liefert genau dann true, wenn sich in Blickrichtung vor dem aufgerufenen Kaefer ein 
     * anderer Kaefer befindet. Identifizierung der Raender wird beachtet.
     */
    public boolean istKaraVorne() {
        int br = getWorld().getWidth();
        int hoe = getWorld().getHeight();
        int x = getX();
        int y = getY();
        switch (blickrichtung) {
            case SUED:
                y = (y + 1) % hoe;
                break;
            case OST:
                x = (x + 1) % br;
                break;
            case NORD:
                y = (y - 1 + hoe) % hoe;
                break;
            case WEST:
                x = (x - 1 + br) % br;
                break;
        }
        return getWorld().getObjectsAt(x, y, Kara.class).size() != 0;
    }

    /** 
     * Liefert true, genau dann wenn in Blickrichtung vor dem aufgerufenen Kaefer 
     * kein Baum und auch kein Kaefer steht. Vermeidet die Negation der Bedingung bei 
     * den ersten Schleifen.  
     * Bei nur einem Kaefer in der Wiese genau die umgekehrte Antwort zu istBaumVorne().
     */
    public boolean istVorneFrei() {
        return !istBaumVorne() && !istKaraVorne();
    }

    /**
     * Liefert genau dann true, wenn sich in Blickrichtung links neben dem aufgerufenen 
     * Kaefer ein Baum befindet. Identifizierung der Raender wird beachtet.
     */
    public boolean istBaumLinks() {
        int br = getWorld().getWidth();
        int hoe = getWorld().getHeight();
        int x = getX();
        int y = getY();
        switch (blickrichtung) {
            case SUED:
                x = (x + 1) % br;
                break;
            case OST:
                y = (y - 1 + hoe) % hoe;
                break;
            case NORD:
                x = (x - 1 + br) % br;
                break;
            case WEST:
                y = (y + 1) % hoe;
                break;
        }
        return getWorld().getObjectsAt(x, y, Baum.class).size() != 0;
    }

    /**
     * Liefert genau dann true, wenn sich in Blickrichtung rechts neben dem aufgerufenen 
     * Kaefer ein Baum befindet. Identifizierung der Raender wird beachtet.
     */
    public boolean istBaumRechts() {
        int br = getWorld().getWidth();
        int hoe = getWorld().getHeight();
        int x = getX();
        int y = getY();
        switch (blickrichtung) {
            case SUED:
                x = (x - 1 + br) % br;
                break;
            case OST:
                y = (y + 1) % hoe;
                break;
            case NORD:
                x = (x + 1) % br;
                break;
            case WEST:
                y = (y - 1 + hoe) % hoe;
                break;
        }
        return getWorld().getObjectsAt(x, y, Baum.class).size() != 0;
    }

    /**
     * Liefert genau dann true, wenn auf der Kachel, auf der sich der
     * aufgerufene Kaefer gerade befindet, ein Blatt liegt.
     */
    public boolean istBlattDarunter() {
        return getWorld().getObjectsAt(getX(), getY(), Blatt.class).size() > 0;
    }

    /**
     * Liefert genau dann true, wenn der aufgerufene Kaefer keine Blaetter bei sich hat.
     */
    public boolean istVorratLeer() {
        return anzBlaetter == 0;
    }


    // Bewegungen
    // einsVor()    linksUm()   hebeAuf()   legeAb()
    // rechtsUm()   dreheUm() 
    
    /**
     * Der Kaefer huepft auf die in Blickrichtung vor ihm liegende Kachel
     * mit Identifizierung der Raender.
     */
    public void einsVor() {
        String txtBaum = "Ich kann keinen Schritt machen,\n weil ein Baum vor mir ist!";
        String txtKara = "Ich kann keinen Schritt machen,\n weil schon ein Kara vor mir ist!";
        KaraWiese myWorld = (KaraWiese) getWorld();

        if (istBaumVorne()) {
            myWorld.warne(txtBaum, this);
            return;
        }
        if (istKaraVorne()) {
            myWorld.warne(txtKara, this);
            return;
        }
        int br = getWorld().getWidth();
        int hoe = getWorld().getHeight();
        switch (blickrichtung) {
            case SUED:
                setLocation(getX(), (getY() + 1) % hoe);
                break;
            case OST:
                setLocation((getX() + 1) % br, getY());
                break;
            case NORD:
                setLocation(getX(), (getY() - 1 + hoe) % hoe);
                break;
            case WEST:
                setLocation((getX() - 1 + br) % br, getY());
                break;
        }
        Greenfoot.delay(1);
    }

    /**
     * Der aufgerufene Kaefer dreht sich um 90 Grad nach links.
     */
    public void linksUm() {
        switch (blickrichtung) {
            case SUED:
                setBlickrichtung(OST);
                break;
            case OST:
                setBlickrichtung(NORD);
                break;
            case NORD:
                setBlickrichtung(WEST);
                break;
            case WEST:
                setBlickrichtung(SUED);
                break;
        }
        Greenfoot.delay(1);
    }

    /**
     * Der aufgerufene Kaefer dreht sich um 90 Grad nach rechts.
     */
    public void rechtsUm() {
        switch (blickrichtung) {
            case SUED:
                setBlickrichtung(WEST);
                break;
            case OST:
                setBlickrichtung(SUED);
                break;
            case NORD:
                setBlickrichtung(OST);
                break;
            case WEST:
                setBlickrichtung(NORD);
                break;
        }
        Greenfoot.delay(1);
    }

    /**
     * Der aufgerufene Kaefer dreht sich um (= halbe Drehung = 180 Grad)
     */
    public void dreheUm() {
        switch (blickrichtung) {
            case SUED:
                setBlickrichtung(NORD);
                break;
            case OST:
                setBlickrichtung(WEST);
                break;
            case NORD:
                setBlickrichtung(SUED);
                break;
            case WEST:
                setBlickrichtung(OST);
                break;
        }
        Greenfoot.delay(1);
    }

    /**
     * Der aufgerufene Kaefer nimmt das Blatt von der Kachel auf, auf der
     * er sich gerade befindet.
     */
    public void hebeAuf() {
        String txtBlatt = "Ich kann kein Blatt aufnehmen,\n weil keines auf der Kachel liegt!";
        KaraWiese myWorld = (KaraWiese) getWorld();

        if (!istBlattDarunter()) {
            myWorld.warne(txtBlatt, this);
            return;
        }
        List l = getWorld().getObjectsAt(getX(), getY(), Blatt.class);
        if (l.size() > 0) {
            Blatt blatt = (Blatt) l.get(0);
            getWorld().removeObject(blatt);  // nur ein Blatt moeglich!!
            anzBlaetter++;
        }
        Greenfoot.delay(1);
    }

    /**
     * Der aufgerufene Kaefer legt ein Blatt auf der Kachel ab, auf der 
     * er sich gerade befindet.
     */
    public void legeAb() {
        String txtBelegt = "Ich kann kein Blatt ablegen,\n weil schon eines da liegt!";
        String txtLeer = "Ich kann kein Blatt ablegen,\n weil ich kein Blatt habe!";
        KaraWiese myWorld = (KaraWiese) getWorld();

        if (istVorratLeer()) {
            myWorld.warne(txtLeer, this);
            return;
        }
        List l = getWorld().getObjectsAt(getX(), getY(), Blatt.class);
        if (l.size() > 0) {
            myWorld.warne(txtBelegt, this);
            return;
        }
        anzBlaetter--;
        getWorld().addObject(new Blatt(), getX(), getY());

        Greenfoot.delay(1);
    }

    // Hilfsroutinen zur Blaetteranzahl sowie zur Blickrichtung des Kaefers
    // bzgl. der Position siehe setLocation(x,y) am Ende sowie getX(), getY() in Actor
    
    /**
     * Liefert die Anzahl der Blaetter, die der Kaefer aktuell mitschleppt.
     */
    public int getAnzahlBlaetter() {
        return anzBlaetter;
    }

    /** 
     * Fuellt den Vorrat auf die genannte Anzahl an Blaettern.
     * max. aber 1000 Blaetter; min. 0 Blaetter
     */
    public void setAnzahlBlaetter(int anzahl) {
        if (anzahl >= 0) {
            anzBlaetter = anzahl;
        } else {
            anzBlaetter = 0;
        }
        if (anzahl > 1000) {
            anzBlaetter = 1000;
        }  //Korrektur bei zu grosser Zahl
    }

    /**
     * Liefert die Blickrichtung, in die der aufgerufene Kaefer gerade schaut.
     * (die gelieferten Werte entsprechen den obigen Konstanten).
     */
    public int getBlickrichtung() {
        return blickrichtung;
    }

    /** 
     * Die Variable  blickrichtung  neu festsetzen.
     * Erlaubte Eingabewerte sind nur:  richtung = 0..3;
     * OST = 0; SUED = 1; WEST = 2; NORD = 3;
     * Bei unerlaubten Werten wird blickrichtung nicht veraendert.
     */
    public void setBlickrichtung(int richtung) {  // neu 4.1.2010
        if (richtung >= 0 && richtung < 4) { 
            blickrichtung = richtung;
            super.setRotation(90*blickrichtung);
        }
    }
    /* Dokumentation:  GFKara_Doku100130.odt
     * Die Beschreibung im Dokumentationskommentar ist nicht vollstaendig.
     * Sie ist formuliert, um den Nutzer zu leiten. Ablenkendes wird nicht genannt.
     * Es werden beide Variable:  blickrichtung UND rotation  neu gesetzt!
     * und dadurch immer synchron gehalten!
     * Bei unerlaubten Werten werden blickrichtung und rotation nicht veraendert.
     * Details und Gruende siehe Dokumentation.
     */

    // Meldungen und Warnungen    

    /**
     * gibt den uebergebenen String in Dialogbox auf dem Bildschirm aus
     */
    protected void melde(String text) {
        JOptionPane.showMessageDialog(null, text, "Meldung!",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * gibt den uebergebenen String und dahinter die Zahl in Dialogbox aus
     */
    protected void melde(String text, int z) {
        JOptionPane.showMessageDialog(null, text + " " + z, "Meldung!",
                JOptionPane.INFORMATION_MESSAGE);
    }

    // Warnung mit Abbruchmoeglichkeit wird durch die Welt erledigt! (9.2./13.2.09)
    // Systembedingte Routinen    

    /**
     * Wird aufgerufen, wenn der Kaefer in die Welt gesetzt wird; 
     * erweitert nach Vorschlag P.Henriksen 29.6.08
     */
    protected void addedToWorld(World world) {
        // Kaefer kann nicht auf eine Kachel mit Baum oder anderem Kaefer gesetzt werden
        Actor baum = getOneObjectAtOffset(0, 0, Baum.class);
        Actor kaefer = getOneObjectAtOffset(0, 0, Kara.class);
        if (baum != null || kaefer != null) {
            world.removeObject(this);
        }
    }

    /**
     * setLocation(x,y) fuer Karas wird ueberschrieben, um nicht auf Baum oder
     * anderen Kaefer ziehen zu koennen!
     * -- setzt den Kaefer auf eine Kachel, deren Spalte x sowie Reihe y du 
     * hier vorgibst. Die Nummerierung beginnt jeweils bei 0 !!) 
     */
    public void setLocation(int x, int y) {
        List baum = getWorld().getObjectsAt(x, y, Baum.class);
        List kaefer = getWorld().getObjectsAt(x, y, Kara.class);
        if (baum.isEmpty() && kaefer.isEmpty()) {
            super.setLocation(x, y);
        }
        Greenfoot.delay(1);
    }
    
    // setLocation(x,y) und setRotation(x) koennen nicht verborgen werden, 
    // weil sie schon in der Oberklasse Actor public sind. 
    //

    /**
     * setRotation() sollte fuer Karas _nicht_ genutzt werden, 
     * wird ersetzt durch setBlickrichtung(); vgl. Dokumentation
     * akzeptiert nur positive Gradzahlen x, die in Himmelsrichtungen 
     * umgerechnet werden.
     */
    public void setRotation(int x) {
        if (x>=0) {
            setBlickrichtung(((x+45)/90)%4);
        }
    }
    /* Die Methode setRotation() fuer Karas ist so abgesichert, dass sie 
     * auf die vier Blickrichtungen eingeengt wird und rotation immer synchron 
     * zur genutzten Variable blickrichtung verwendet! 
     * Details und Gruende:  Dokumentation:  GFKara_Doku100130.odt
     */

}