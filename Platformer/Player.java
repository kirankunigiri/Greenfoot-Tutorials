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
    int GRAVITY = 1;
    int hsp = 0;
    int vsp = 0;
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
        int move = key_right + key_left;
        hsp = move*MOVE_SPEED;
        
        // Limit the speed (terminal velocity)
        if (vsp < TERMINAL_VELOCITY) {
            vsp += GRAVITY;
        }
        
        // Jumping
        if (place_meeting(getX(), getY() + 1)) {
            vsp = key_jump ? -JUMP_SPEED : 0;
        }
        
        // Horizontal Collision
        if (place_meeting(getX() + hsp, getY())) {
            while (!place_meeting(getX() + sign(hsp), getY())) {
                setLocation(getX() + sign(hsp), getY());
            }
            hsp = 0;
        }
        
        // Vertical Collision
        if (place_meeting(getX(), getY() + vsp)) {
            while (!place_meeting(getX(), getY() + sign(vsp))) {
                setLocation(getX(), getY() + sign(vsp));
            }
            vsp = 0;
        }
        
        // Update the location
        setLocation(getX() + hsp, getY() + vsp);
    }
    
    /**
     * Returns 1 if it is positive, -1 if it is negative, or 0.
     */
    public int sign(int x) {
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
    public boolean place_meeting(int x, int y) {
        int oldX = getX();
        int oldY = getY();
        setLocation(x, y);
        boolean isTouching = !getIntersectingObjects(Actor.class).isEmpty();
        setLocation(oldX, oldY);
        return isTouching;
    }
}
