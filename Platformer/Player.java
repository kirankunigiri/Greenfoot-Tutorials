import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * The player.
 * 
 * @author Kiran
 * @version 1.0
 */
public class Player extends Actor
{
    // Properties
    int dx = 0;
    int dy = 0;
    
    // Constants
    final int GRAVITY = 1;
    final int JUMP_SPEED = 12;
    final int MOVE_SPEED = 4;
    final int TERMINAL_VELOCITY = 16;
    
    public void act() 
    {
        // Obtain inputs
        int key_right = Greenfoot.isKeyDown("right") ? 1 : 0;
        int key_left = Greenfoot.isKeyDown("left") ? -1: 0;
        boolean key_jump = Greenfoot.isKeyDown("space");
        
        // Convert the inputs
        int direction = key_right + key_left;
        dx = direction*MOVE_SPEED;
        
        // Limit the speed (terminal velocity)
        if (dy < TERMINAL_VELOCITY) {
            dy += GRAVITY;
        }
        
        // Jumping
        if (willCollideAt(getX(), getY() + 1)) {
            dy = key_jump ? -JUMP_SPEED : 0;
        }
        
        // Horizontal Collision
        if (willCollideAt(getX() + dx, getY())) {
            while (!willCollideAt(getX() + getDirection(dx), getY())) {
                //setLocation(getX() + getDirection(dx), getY());
                move(getDirection(dx), 0);
            }
            dx = 0;
        }
        
        // Vertical Collision
        if (willCollideAt(getX(), getY() + dy)) {
            while (!willCollideAt(getX(), getY() + getDirection(dy))) {
                //setLocation(getX(), getY() + getDirection(dy));
                move(0, getDirection(dy));
            }
            dy = 0;
        }
        
        // Update the location
        
        // For fixed map
        //setLocation(getX() + dx, getY() + dy);
        
        // For side scrolling
        move(dx, dy);
    }
    
    public void move(int dx, int dy) {
        if ((getX() > 200 || dx > 0) && (getX() < 400 || dx < 0)) {
            setLocation(getX() + dx, getY());
        } else {
            moveWalls(dx, 0);
        }
        if ((getY() > 100 || dy > 0) && (getY() < 300 || dy < 0)) {
            setLocation(getX(), getY() + dy);
        } else {
            moveWalls(0, dy);
        }
        System.out.println(dy);
    }
    
    public void moveWalls(int x, int y) {
        List<Wall> walls = getWorld().getObjects(Wall.class); 
        for (int i = 0; i < walls.size(); i++) {
            Wall wall = walls.get(i);
            wall.setLocation(wall.getX() - x, wall.getY() - y);
        }
    }
    
    /**
     * Returns 1 if it is positive, -1 if it is negative, or 0.
     */
    public int getDirection(int x) {
        if (x > 0) {
            return 1;
        } else if (x < 0) {
            return -1;
        }
        return 0;
    }
    
    /**
     * Checks if the player will collide with an object at a new position.
     * Works by moving the player to the new position, checking for a collision, 
     * and moving the player back.
     */
    public boolean willCollideAt(int x, int y) {
        int oldX = getX();
        int oldY = getY();
        setLocation(x, y);
        boolean isTouching = !getIntersectingObjects(Actor.class).isEmpty();
        setLocation(oldX, oldY);
        return isTouching;
    }
}
