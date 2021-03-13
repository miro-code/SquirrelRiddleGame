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
    private boolean nut;
    private int x;
    private int y;
    private int direction;
    private boolean flower;

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

        this.nut = nut;
        this.color = color;
        this.x = x;
        this.y = y;
        this.direction = direction;

        if(!(color.equals("Red") || color.equals("Black") || color.equals("Grey") || color.equals("Brown")))
        {
            throw new IllegalArgumentException("Tried to create squirrel with unknown color");
        }
        if(direction < 0 || direction > 3)
        {
            throw new IllegalArgumentException("Tried to create squirrel with unknown direction");
        }

        //Set the appropriate flower attributes for black and brown squirrels
        if( color.equals("Black") || color.equals("Brown") )
        {
            flower = true;
        }
        else
        {
            flower = false;
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
            case north:
                y--;
                break;
            case east:
                x++;
                break;
            case south:
                y++;
                break;
            case west:
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

    public boolean getFlower()
    {
        return flower;
    }

    public int getFlowerX() 
    {
        if(!flower)
        {
            System.out.println("Something went wrong: this specific squirrel doesnt have a flower");
        }

        if( color.equals("Black"))
        {
            switch(direction)
                {
                    case north:
                        return x + 1;
                    case east:
                        return getTailsX();
                    case south:
                        return x - 1;
                    default:
                        return getTailsX();
                }
        }
        else
        {
            if(!color.equals("Brown"))
            {
                System.out.println("Something went wrong: this color squirrel doesnt have a flower");
            }
            switch(direction)
                {
                    case north:
                        return x + 1;
                    case east:
                        return x;
                    case south:
                        return x - 1;
                    default:
                        return x;
                }
        }
        
    }

    public int getFlowerY() 
    {
        if(!flower)
        {
            System.out.println("Something went wrong: this specific squirrel doesnt have a flower");
        }

        if( color.equals("Black"))
        {
            switch(direction)
                {
                    case north:
                        return getTailsY();
                    case east:
                        return y + 1;
                    case south:
                        return getTailsY();
                    default:
                        return y - 1;
                }
        }
        else
        {
            if(!color.equals("Brown"))
            {
                System.out.println("Something went wrong: this color squirrel doesnt have a flower");
            }
            switch(direction)
                {
                    case north:
                        return y;
                    case east:
                        return y + 1;
                    case south:
                        return y;
                    default:
                        return y - 1;
                }
        }
 
    }
    public String getColor()
    {
        return color;
    }

    public boolean getNut()
    {
        return nut;
    }

    public void dropNut()
    {
        nut = false;
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