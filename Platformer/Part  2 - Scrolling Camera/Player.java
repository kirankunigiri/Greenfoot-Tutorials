import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * The player.
 * 
 * @author Kiran
 * @version 1.0
 */
public class Player extends SmoothMover
{
    // Properties
    double dx = 0;
    double dy = 0;
    private GifImage gifRun;
    private GifImage gifIdle;
    private GifImage gifJump;
    private GifImage gifFall;
    private GifImage[] gifImages;
    private Animation animation;
    boolean flipped = false;
    
    // Constants
    final double GRAVITY = 0.8;
    final double JUMP_SPEED = 12;
    final double MOVE_SPEED = 4;
    final double TERMINAL_VELOCITY = 16;
    
    public Player() {
         // Animation
        gifRun = new GifImage("images/run2.gif");
        gifIdle = new GifImage("images/idle2.gif");
        gifJump = new GifImage("images/jump2.png");
        gifFall = new GifImage("images/landing2.png");
        gifImages = new GifImage[] {gifRun, gifIdle, gifJump, gifFall};
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
        double key_right = Greenfoot.isKeyDown("right") ? 1 : 0;
        double key_left = Greenfoot.isKeyDown("left") ? -1: 0;
        boolean key_jump = Greenfoot.isKeyDown("space");
        
        // Convert the inputs
        double direction = key_right + key_left;
        dx = direction*MOVE_SPEED;
        
        if (Math.abs(dx) > 0) {
            if (dx > 0 && flipped) {
                flipImages();
                flipped = !flipped;
            } else if (dx < 0 && !flipped) {
                flipImages();
                flipped = !flipped;
            }
            //animation.setActiveState(true);
            //animation.run();
        } else {
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
        
        // Check if player is on ground
        if (willCollideAt(getExactX(), getExactY() + 1)) {
            if (dx == 0) {
                setImage(gifIdle.getCurrentImage());
            } else {
                setImage(gifRun.getCurrentImage());
            }
            // Jumping
            dy = key_jump ? -JUMP_SPEED : 0;
        }
        
        // Horizontal Collision
        
        if (willCollideAt(getExactX() + dx, getExactY())) {
            while (!willCollideAt(getExactX() + getDirection(dx), getExactY())) {
                //setLocation(getExactX() + getDirection(dx), getExactY());
                move(getDirection(dx), 0, true);
            }
            dx = 0;
        }
        
        // Vertical Collision
        if (willCollideAt(getExactX(), getExactY() + dy)) {
            while (!willCollideAt(getExactX(), getExactY() + getDirection(dy))) {
                //setLocation(getExactX(), getExactY() + getDirection(dy));
                move(0, getDirection(dy), true);
            }
            dy = 0;
        }
        
        // Update the location
        
        // For fixed map
        //setLocation(getExactX() + dx, getExactY() + dy);
        
        // For side scrolling
        move(dx, dy, false);
    }
    
    public void move(double dx, double dy, boolean collision) {
        MyWorld world = (MyWorld) getWorld();
        
        boolean playerWithinCameraBoundsX = (getExactX() > 200 || dx > 0) && (getExactX() < 400 || dx < 0);
        boolean cameraOutOfMapBoundsX = (world.bottomLeft.getExactX() - dx > 0 && dx <= 0) || (world.bottomRight.getExactX() - dx < world.getWidth() && dx >= 0);
        if (cameraOutOfMapBoundsX || playerWithinCameraBoundsX) {
            if (cameraOutOfMapBoundsX) {
                double offset = 0;
                if (dx < 0) {
                    offset = 0 - world.bottomLeft.getExactX();
                } else {
                    offset = world.getWidth() - world.bottomRight.getExactX();
                }
                setLocation(getExactX() + offset, getExactY());
                moveWalls(-offset, 0);
            }
            setLocation(getExactX() + dx, getExactY());
        } else {
            moveWalls(dx, 0);
        }

        boolean playerWithinCameraBoundsY = (getExactY() > 100 || dy > 0) && (getExactY() < 300 || dy < 0);
        boolean cameraOutOfMapBoundsY =  (world.bottomLeft.getExactY() - dy < world.getHeight() && dy >= 0) || (world.topRight.getExactY() - dy > 0 && dy <= 0);
        if (cameraOutOfMapBoundsY || playerWithinCameraBoundsY) {
            if (cameraOutOfMapBoundsY) {
                double offset = 0;
                if (dy < 0) {
                    offset = 0 + world.topRight.getExactY();
                } else {
                    offset = world.getHeight() - world.bottomLeft.getExactY();
                }
                setLocation(getExactX(), getExactY() + offset);
                moveWalls(0, -offset);
            }
            setLocation(getExactX(), getExactY() + dy);
        } else {
            moveWalls(0, dy);
        }
    }
    
    public void flipImages() {
        for (int i = 0; i < gifImages.length; i++) {
            gifImages[i].flipImage();
        }
    }
    
    public void moveWalls(double x, double y) {
        List<StaticActor> walls = getWorld().getObjects(StaticActor.class); 
        for (int i = 0; i < walls.size(); i++) {
            StaticActor wall = walls.get(i);
            wall.setLocation(wall.getExactX() - x, wall.getExactY() - y);
        }
        
    }
    
    /**
     * Returns 1 if it is positive, -1 if it is negative, or 0.
     */
    public double getDirection(double x) {
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
    public boolean willCollideAt(double x, double y) {
        double oldX = getExactX();
        double oldY = getExactY();
        setLocation(x, y);
        boolean isTouching = !getIntersectingObjects(Actor.class).isEmpty();
        setLocation(oldX, oldY);
        return isTouching;
    }
}
