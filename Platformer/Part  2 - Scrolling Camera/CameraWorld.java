import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CameraWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CameraWorld extends World
{

    /**
     * Constructor for objects of class CameraWorld.
     * 
     */
    public CameraWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
    }
    
    public CameraWorld(int worldWidth, int worldHeight, int cellSize, boolean bounded) {
        super(worldWidth, worldHeight, cellSize, bounded);
    }
      
}
