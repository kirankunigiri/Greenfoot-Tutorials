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
        Wall wall11 = new Wall();
        addObject(wall11,62,370);
        wall11.setLocation(58,375);
        Wall wall12 = new Wall();
        addObject(wall12,15,381);
        wall12.setLocation(8,375);
        Wall wall13 = new Wall();
        addObject(wall13,35,293);
        wall13.setLocation(12,326);
        Wall wall14 = new Wall();
        addObject(wall14,45,242);
        wall14.setLocation(12,277);
        Wall wall15 = new Wall();
        addObject(wall15,23,226);
        wall15.setLocation(12,228);
        wall9.setLocation(404,375);
        wall10.setLocation(356,275);
        Wall wall16 = new Wall();
        addObject(wall16,490,381);
        wall16.setLocation(454,376);
        Wall wall17 = new Wall();
        addObject(wall17,536,341);
        wall16.setLocation(454,375);
        wall17.setLocation(504,375);
        Wall wall18 = new Wall();
        addObject(wall18,496,246);
        wall18.setLocation(554,375);
        Wall wall19 = new Wall();
        addObject(wall19,532,268);
        wall19.setLocation(532,268);
        wall19.setLocation(532,268);
        wall19.setLocation(532,268);
        wall19.setLocation(532,268);
        wall19.setLocation(532,268);
        wall19.setLocation(532,268);
        wall19.setLocation(532,268);
        wall19.setLocation(532,268);
        wall19.setLocation(532,268);
        wall19.setLocation(532,268);
        wall19.setLocation(532,268);
        wall19.setLocation(532,268);
        wall19.setLocation(532,268);
        wall19.setLocation(532,268);
        wall19.setLocation(532,268);
        wall19.setLocation(532,268);
        wall19.setLocation(532,268);
        wall19.setLocation(532,268);
        wall19.setLocation(532,268);
        wall19.setLocation(532,268);
        wall19.setLocation(532,268);
        wall19.setLocation(532,268);
        wall19.setLocation(532,268);
        wall19.setLocation(532,268);
        wall19.setLocation(532,268);
        wall19.setLocation(532,268);
        wall19.setLocation(532,268);
        wall19.setLocation(532,268);
        wall19.setLocation(532,268);
        wall19.setLocation(532,268);
        wall19.setLocation(532,268);
        wall19.setLocation(532,268);
        wall19.setLocation(532,268);
        wall19.setLocation(532,268);
        wall19.setLocation(532,268);
        wall19.setLocation(532,268);
        wall19.setLocation(532,268);
        wall19.setLocation(532,268);
        wall19.setLocation(532,268);
        wall19.setLocation(532,268);
        wall19.setLocation(532,268);
        wall19.setLocation(532,268);
        wall19.setLocation(532,268);
        wall19.setLocation(532,268);
        wall19.setLocation(532,268);
        wall19.setLocation(532,268);
        wall19.setLocation(532,268);
        wall19.setLocation(532,268);
        wall19.setLocation(554,325);
        Wall wall20 = new Wall();
        addObject(wall20,549,251);
        wall20.setLocation(554,275);
        Wall wall21 = new Wall();
        addObject(wall21,472,256);
        wall21.setLocation(406,325);
        Wall wall22 = new Wall();
        addObject(wall22,418,199);
        wall22.setLocation(356,375);
        Wall wall23 = new Wall();
        addObject(wall23,462,133);
        wall23.setLocation(554,225);
    }
}
