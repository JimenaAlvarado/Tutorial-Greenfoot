import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Dolphin here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Dolphin extends Actor
{
    private final int GRAVITY = 1;
    private int velocity;
    public Dolphin(){
        velocity = 3;
    }
    
    /**
     * Act - do whatever the Dolphin wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        fall();
        if (Greenfoot.isKeyDown("space") && isOnSolidGround()) jump();
        move();
    }
    
    public void fall()
    {
        setLocation(getX(), getY() + velocity);
        if (isOnSolidGround())
        {
            velocity = 0;
            while( isOnSolidGround() )
            {
                setLocation(getX(), getY() - 1);
            }
            setLocation(getX(), getY() + 1);
        }
        else if (velocity < 0 && didBumpHeah()) velocity = 0;
        else velocity += GRAVITY;
    }
    
    public void jump()
    {
        velocity = -15;
    }
    
    public void move()
    {
        int x = getX(), y = getY();
        if(Greenfoot.isKeyDown("left") && canMoveLeft() ) x-=3;
        if(Greenfoot.isKeyDown("right") && canMoveRight() ) x+=3;
        setLocation(x, y);
    }   
    
    public boolean isOnSolidGround()
    {
        boolean isOnGround = false;
        if(getY() > getWorld().getHeight() - 70) isOnGround = true;
        int imageWidth = getImage().getWidth();
        int imageHeight = getImage().getHeight();
        if (getOneObjectAtOffset(imageWidth / -2, imageHeight / 2, Platform.class) != null ||
            getOneObjectAtOffset(imageWidth / 2, imageHeight / 2, Platform.class) != null)
            isOnGround = true;
        
        return isOnGround;
    }
    
    public boolean didBumpHeah()
    {
        boolean bumpedHead = false;
        int imageWidth = getImage().getWidth();
        int imageHeight = getImage().getHeight(); 
        if (getOneObjectAtOffset(imageWidth / -2, imageHeight / -2, Platform.class) != null || 
            getOneObjectAtOffset(imageWidth / 2, imageHeight / -2, Platform.class) != null)
            bumpedHead = true;
         
        return bumpedHead;
    }
    
    public boolean canMoveLeft()
    {
        boolean moveLeft = true; 
        int imageWidth = getImage().getWidth(); 
        int imageHeight = getImage().getHeight(); 
        if (getOneObjectAtOffset((imageWidth / -2) - 3, imageHeight / -2, Platform.class) != null || 
            getOneObjectAtOffset((imageWidth / -2) - 3, (imageHeight / 2) - 1, Platform.class) != null) 
            moveLeft = false; 
       
        return moveLeft;
    }
    
    public boolean canMoveRight()
    { 
        boolean moveRight = true; 
        int imageWidth = getImage().getWidth(); 
        int imageHeight = getImage().getHeight(); 
        if (getOneObjectAtOffset((imageWidth / 2) + 3, imageHeight / -2, Platform.class) != null || 
            getOneObjectAtOffset((imageWidth / 2) + 3, (imageHeight / 2) - 1, Platform.class) != null) 
            moveRight = false; 
          
        return moveRight; 
    }
}
