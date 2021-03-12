public class Squirrel
{

/**
 *
 * Squirrel for SCC.110 course work.
 *
 * Author: Miran Özdogan
 *
 *
 **/

    public static final int north = 0;
    public static final int east = 1;
    public static final int south = 2;
    public static final int west = 3;
    
    private String color;
    boolean nut;
    private int x;
    private int y;
    private int direction;

    /** Creates a new Squirrel

    @param color Color of the squirrel
    @param nut indicates whether the squirrel carries a nut
    @param x x coordinate of the squirrels head on the game board
    @param y y coordinate of the squirrels head on the game board
    @param direction indicates the direction the squirrel faces on the game board
    @throws IllegalArgumentException if the color or direction is unrecognized 

	 */
    public Squirrel(String color, boolean nut, int x, int y, int direction) throws IllegalArgumentException
    {

        if(!(color == "Red" || color == "Black" || color == "Grey" || color == "Brown"))
        {
            throw new IllegalArgumentException("Tried to create squirrel with unknown color");
        }
        if(direction < 0 || direction > 3)
        {
            throw new IllegalArgumentException("Tried to create squirrel with unknown direction");
        }
        this.nut = nut;
        this.color = color;
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    //Getter and setter methods

    public void move(int direction)
    {
        switch(direction)
        {
            case 0:
                y--;
                break;
            case 1:
                x++;
                break;
            case 2:
                y++;
                break;
            case 3:
                x--;
                break;            
        }
    }


    /**
     @return the x coordinate of the squirrels tail
     */
    public int getTailsX()
    {
        switch(direction)
        {
            case 0:
                return x;
            case 1:
                return x-1;
            case 2:
                return x;
            //case 3
            default:
                return x+1;
        }
    }


    
    /**
     @return the y coordinate of the squirrels tail
     */
    public int getTailsY()
    {
        switch(direction)
        {
            case 0:
                return y+1;
            case 1:
                return y;
            case 2:
                return y-1;
            //case 3
            default:
                return y;            
        }
    }


    //Getter and setter methods

    public void setNut(boolean nut)
    {
        this.nut = nut;
    }
    public void setX(int x)
    {
        this.x = x;
    }
    public void setY(int y)
    {
        this.y = y;
    }
    public void setDirection(int direction) throws IllegalArgumentException
    {
        if(direction < 0 || direction > 3)
        {
            throw new IllegalArgumentException("Unknown direction");
        }
        this.direction = direction;
    }

    public String getColor()
    {
        return color;
    }

    public boolean getNut()
    {
        return nut;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getDirection()
    {
        return direction;
    }

}