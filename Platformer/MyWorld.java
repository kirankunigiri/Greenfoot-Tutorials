import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Player player = new Player();
        addObject(player,77,178);
        Wall wall = new Wall();
        addObject(wall,374,304);
        Wall wall2 = new Wall();
        addObject(wall2,448,167);
        Wall wall3 = new Wall();
        addObject(wall3,302,125);
        Wall wall4 = new Wall();
        addObject(wall4,239,124);
        Wall wall5 = new Wall();
        addObject(wall5,508,106);
        Wall wall6 = new Wall();
        addObject(wall6,396,76);
        Wall wall7 = new Wall();
        addObject(wall7,503,70);
        wall.setLocation(200,346);
        player.setLocation(96,204);
        wall.setLocation(106,375);
        wall4.setLocation(156,376);
        wall4.setLocation(156,375);
        wall3.setLocation(206,375);
        wall6.setLocation(256,375);
        wall2.setLocation(256,325);
        wall7.setLocation(306,325);
        wall2.setLocation(256,325);
        wall7.setLocation(306,325);
        wall5.setLocation(306,375);
        Wall wall8 = new Wall();
        addObject(wall8,389,252);
        Wall wall9 = new Wall();
        addObject(wall9,397,188);
        wall8.setLocation(356,325);
        wall9.setLocation(356,275);
        Wall wall10 = new Wall();
        addObject(wall10,362,232);
        wall10.setLocation(356,225);
        player.setLocation(138,286);
    }
}
