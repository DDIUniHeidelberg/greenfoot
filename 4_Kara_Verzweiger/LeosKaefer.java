import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LeosKaefer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LeosKaefer extends Kara
{
    /**
     * Act - do whatever the LeosKaefer wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void einsVor() 
    {
        if(istVorneFrei()){
            super.einsVor();
        }
        else{
            melde("Besetzt");// Add your action code here.
        }
    }
    
    public void hungrigerKaefer(){
        melde("Ich habe Hunger!");
        einsVor();
        linksUm();
        einsVor();
        linksUm();
    }
    
    public void sehrHungrigerKaefer(){
        for(int i = 0; i<5; i++){
            hungrigerKaefer();
        }
    }
    /** 
     * Der Kaefer gibt seinen aktuellen Gefuehlsstand an 
     */
    public void GluecksstandAnzeigen() {
        System.out.println(":-)))");
    }
}
