import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends CameraWorld
{

    Point topLeft = new Point();
    Point topRight = new Point();
    Point bottomLeft = new Point();
    Point bottomRight = new Point();
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        super(600, 400, 1, false); 
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        
        int[][] map = new int[][]{
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            { 1, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1},
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1},
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 1},
            { 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1},
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };
        
        int blockWidth = 50;
        
        addObject(topLeft, 0, 0);
        addObject(topRight, map[0].length * blockWidth, 0);
        addObject(bottomLeft, 0, map.length * blockWidth);
        addObject(bottomRight, map[0].length * blockWidth, map.length * blockWidth);
        
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                int a = map[i][j];
                if (a == 1) {
                    Wall wall = new Wall();
                    addObject(wall, blockWidth*j + blockWidth/2, blockWidth*i + blockWidth/2);
                } else if (a == 2) {
                    Player player = new Player();
                    addObject(player, blockWidth*j + blockWidth/2, blockWidth*i + blockWidth/2);
                } else {
                    // Emptiness
                }
            }
            System.out.println("");
        }
    }
}
