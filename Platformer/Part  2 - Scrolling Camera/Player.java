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
    private GifImage gifRun;
    private GifImage gifIdle;
    private GifImage gifJump;
    private GifImage gifFall;
    private Animation animation;
    boolean flipped = false;
    
    // Constants
    final int GRAVITY = 1;
    final int JUMP_SPEED = 12;
    final int MOVE_SPEED = 4;
    final int TERMINAL_VELOCITY = 16;
    
    public Player() {
         // Animation
        gifRun = new GifImage("images/run.gif");
        gifIdle = new GifImage("images/idle.gif");
        gifJump = new GifImage("images/jump.png");
        gifFall = new GifImage("images/midair.gif");
        GifImage[] gifImages = {gifRun, gifIdle, gifJump, gifFall};
        for (int i = 0; i < gifImages.length; i++) {
            gifImages[i].resizeImages(2, 2);
        }
        
        /*
        java.util.List<GreenfootImage> imgs = new GifImage("images/run.gif").getImages();
        GreenfootImage[] images = new GreenfootImage[imgs.size()];
        for (int i=0; i<imgs.size(); i++) {
            images[i] = (GreenfootImage)imgs.get(i);
        }
        animation = new Animation(this, images);
        animation.setCycleActs(50);
        animation.run();
        animation.setActiveState(true);
        */
    }
    
    public void act() 
    {
        
        // Obtain inputs
        int key_right = Greenfoot.isKeyDown("right") ? 1 : 0;
        int key_left = Greenfoot.isKeyDown("left") ? -1: 0;
        boolean key_jump = Greenfoot.isKeyDown("space");
        
        // Convert the inputs
        int direction = key_right + key_left;
        dx = direction*MOVE_SPEED;
        
        if (Math.abs(dx) > 0) {
            if (flipped) {
                gifRun.flipImage();
                flipped = !flipped;
            }
            setImage(gifRun.getCurrentImage());
            //animation.setActiveState(true);
            //animation.run();
        } else {
            if (!flipped) {
                gifRun.flipImage();
                flipped = !flipped;
            }
            setImage(gifIdle.getCurrentImage());
            //animation.run();
            //animation.setCycleCount(1);
        }
        
        if (dy < 0) {
            setImage(gifJump.getCurrentImage());
        } else if (dy > 0) {
            setImage(gifFall.getCurrentImage());
        }
        
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
                move(getDirection(dx), 0, true);
            }
            dx = 0;
        }
        
        // Vertical Collision
        if (willCollideAt(getX(), getY() + dy)) {
            while (!willCollideAt(getX(), getY() + getDirection(dy))) {
                //setLocation(getX(), getY() + getDirection(dy));
                move(0, getDirection(dy), true);
            }
            dy = 0;
        }
        
        // Update the location
        
        // For fixed map
        //setLocation(getX() + dx, getY() + dy);
        
        // For side scrolling
        move(dx, dy, false);
    }
    
    public void move(int dx, int dy, boolean collision) {
        MyWorld world = (MyWorld) getWorld();
        
        boolean playerWithinCameraBoundsX = (getX() > 200 || dx > 0) && (getX() < 400 || dx < 0);
        boolean cameraOutOfMapBoundsX = (world.bottomLeft.getX() - dx > 0 && dx <= 0) || (world.bottomRight.getX() - dx < world.getWidth() && dx >= 0);
        if (cameraOutOfMapBoundsX || playerWithinCameraBoundsX) {
            if (cameraOutOfMapBoundsX) {
                int offset = 0;
                if (dx < 0) {
                    offset = 0 - world.bottomLeft.getX();
                } else {
                    offset = world.getWidth() - world.bottomRight.getX();
                }
                setLocation(getX() + offset, getY());
                moveWalls(-offset, 0);
            }
            setLocation(getX() + dx, getY());
        } else {
            moveWalls(dx, 0);
        }

        boolean playerWithinCameraBoundsY = (getY() > 100 || dy > 0) && (getY() < 300 || dy < 0);
        boolean cameraOutOfMapBoundsY =  (world.bottomLeft.getY() - dy < world.getHeight() && dy >= 0) || (world.topRight.getY() - dy > 0 && dy <= 0);
        if (cameraOutOfMapBoundsY || playerWithinCameraBoundsY) {
            if (cameraOutOfMapBoundsY) {
                int offset = 0;
                if (dy < 0) {
                    offset = 0 + world.topRight.getY();
                } else {
                    offset = world.getHeight() - world.bottomLeft.getY();
                }
                setLocation(getX(), getY() + offset);
                moveWalls(0, -offset);
            }
            setLocation(getX(), getY() + dy);
        } else {
            moveWalls(0, dy);
        }
    }
    
    public void moveWalls(int x, int y) {
        List<StaticActor> walls = getWorld().getObjects(StaticActor.class); 
        for (int i = 0; i < walls.size(); i++) {
            StaticActor wall = walls.get(i);
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
