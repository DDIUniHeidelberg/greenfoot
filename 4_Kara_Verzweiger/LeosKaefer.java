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
}
