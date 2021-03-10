import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 *
 * Game Board for SCC.110 course work.
 *
 * Author: Miran Özdogan
 *
 *
 **/

public class GameBoard
{

    public static final int iconSize = 100;

    private JFrame window;
    private JPanel panel;
    private JPanel fieldPanel;
    private BorderLayout layout;
    private GridLayout fieldLayout;

    private Tile[][] tiles;

    private Picture empty;
    private Picture hole;
    private Picture flower;
    private Picture doubleFlower;

    private Picture arrowUp;
    private Picture arrowDown;
    private Picture arrowRight;
    private Picture arrowLeft;

    private JButton up;
    private JButton down;
    private JButton right;
    private JButton left;


    private int xFields = 4;
    private int yFields = 4;


    /** Creates a new GameBoard

	 */

    public GameBoard(ActionListener actionListener)
    {
        //Create the Frame
        window = new JFrame("Nut Store");
        //On close exit the program
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //calculate and set the window size; +2 for the arrows
        window.setSize( (xFields + 2 )* iconSize, (yFields+2) * iconSize );
        //set the frame to visible
        window.setVisible(true);

        //create panel with border layout for the hole window
        panel = new JPanel();

        layout = new BorderLayout();
        window.setContentPane(panel);

        //Create a field panel with grid layout for the game board (without the arrows)
        fieldPanel = new JPanel();
        fieldLayout = new GridLayout(xFields, yFields);
        fieldPanel.setLayout(fieldLayout);



        panel.setLayout(layout);

        //Add the game field in the CENTER
        //This is the preffered way to insert the parameters according to oracles documentation
        panel.add(fieldPanel, BorderLayout.CENTER);

        //initialize the fields array
        tiles = new Tile[xFields][yFields];

        //initialize the game field buttons
        //Introducing a class for them (similar to squirrel) would be unnessecary complicated and over-modling
        empty = new Picture("icons/Empty.png", 0);
        hole = new Picture("icons/Hole.png", 0);
        flower = new Picture("icons/Flower.png", 0);
        doubleFlower = new Picture("icons/SquirrelFlower.png", 0);
        arrowUp = new Picture("icons/BigArrow.png", 0);
        arrowDown = new Picture("icons/BigArrow.png", 180);
        arrowRight = new Picture("icons/Arrow.png", 90);
        arrowLeft = new Picture("icons/Arrow.png", 270);

        //create the arrow buttons
        up = new JButton(arrowUp);
        //the button has no border outside the image
        up.setBorder(null);
        
        //up.addActionListener(actionListener);

        down = new JButton(arrowDown);
        down.setBorder(null);
        //down.addActionListener(actionListener);

        right = new JButton(arrowRight);
        right.setBorder(null);
        //right.addActionListener(actionListener);

        left = new JButton(arrowLeft);
        left.setBorder(null);
        //left.addActionListener(actionListener);


        panel.add(up, BorderLayout.NORTH);
        panel.add(right, BorderLayout.EAST);
        panel.add(down, BorderLayout.SOUTH);
        panel.add(left, BorderLayout.WEST);


        for(int i = 0; i < xFields; i++)
        {
            for(int j = 0; j < yFields; j++)
            {
                tiles[j][i] = new Tile(fieldPanel, actionListener);
            }
        }


    }

    /** Displays a squirrel on the game board

    @param sq squirrel to be displayed
    @throws IllegalArgumentException if the squirrel can not be displayed due to its attributes 
	 */
    public void displaySquirrel(Squirrel sq) throws IllegalArgumentException
    {
        switch (sq.getDirection()) 
        {
            case 0:
                if(sq.getY() < 0 || sq.getY() + 1 >= yFields || sq.getX() < 0 || sq.getX() >= xFields)
                {
                    throw new IllegalArgumentException("Squirrel located out of game board. Start rescue mission immediatly");
                }
                break;
            case 1:
            if(sq.getY() < 0 || sq.getY() >= yFields || sq.getX() - 1 < 0 || sq.getX() >= xFields)
            {
                throw new IllegalArgumentException("Squirrel located out of game board. Start rescue mission immediatly");
            }
            break;
            case 2:
            if(sq.getY() - 1 < 0 || sq.getY() >= yFields || sq.getX() < 0 || sq.getX() >= xFields)
            {
                throw new IllegalArgumentException("Squirrel located out of game board. Start rescue mission immediatly");
            }
            break;
            case 3:
            if(sq.getY() < 0 || sq.getY() + 1 >= yFields || sq.getX() < 0 || sq.getX() + 1 >= xFields)
            {
                throw new IllegalArgumentException("Squirrel located out of game board. Start rescue mission immediatly");
            }
            break;
        
            default:
                throw new IllegalArgumentException("Squirrel facing into an unrecognized direction. It might have discovered the 3rd dimension. Beware.");
        }

        //Build the appropriate image file names
        StringBuffer imageName1 = new StringBuffer("icons/");
        imageName1.append(sq.getColor());
        imageName1.append("Squirrel");

        StringBuffer imageName2 = new StringBuffer(imageName1);

        imageName1.append('1');
        imageName2.append('2');

        if(sq.getNut())
        {
            imageName1.append("Nut");
        }

        imageName1.append(".png");
        imageName2.append(".png");

        Picture p1 = new Picture(imageName1.toString(), sq.getDirection() * 90);
        Picture p2 = new Picture(imageName2.toString(), sq.getDirection() * 90);

        //Let the images appear in the appropriate places
        tiles[sq.getX()][sq.getY()].setButton(p1, sq);

        switch(sq.getDirection())
        {
            case 0:
                tiles[sq.getX()][sq.getY()+1].setButton(p2);
                break;
            case 1:
                tiles[sq.getX()-1][sq.getY()].setButton(p2);
                break;
            case 2:
                tiles[sq.getX()][sq.getY()-1].setButton(p2);
                break;
            case 3:
                tiles[sq.getX()+ 1][sq.getY()].setButton(p2);
                break;            
        }
        

    }

    /** Displays a image on field of the board

    @param x coordinate of the field
    @param y coordinate of the field
    @param image is the image to be displayed
    @throws IllegalArgumentException if the given coordinates are outside the game board
	 */
    public void displayImage(int x, int y, Picture image) throws IllegalArgumentException 
    {
        if(x < 0 || x>= xFields || y < 0 || y >= yFields)
        {
            throw new IllegalArgumentException("Coordinates outside the game board");
        }

        tiles[x][y].setButton(image);
    }


    public void moveSquirrel(Squirrel sq, int dir)
    {
        tiles[sq.getX()][sq.getY()].setOriginalButton();
        tiles[sq.getTailsX()][sq.getTailsY()].setOriginalButton();
        sq.move(dir);
        displaySquirrel(sq);
    }

    /** Displays level 1 on the board as described in the course work specification

    */
    public void displayLevel1()
    {
        displayImage(0,0, empty);
        displayImage(1,0, empty);
        displayImage(2,0, hole);
        displayImage(3,0, empty);

        displayImage(0,1, hole);
        displayImage(3,1, empty);

        displayImage(0,2, empty);
        displayImage(1,2, flower);
        displayImage(3,2, empty);

        displayImage(0,3, empty);
        displayImage(1,3, empty);
        displayImage(3,3, hole);


        Squirrel red = new Squirrel("Red", true, 1,1,Squirrel.west);
        Squirrel grey = new Squirrel("Grey", true, 2,2 ,Squirrel.north);

        displaySquirrel(red);
        displaySquirrel(grey);
        moveSquirrel(grey, 1);
        moveSquirrel(grey, 3);
    }


    

    public static void main(String[] args)
    {
        GameBoard g = new GameBoard(null);
        g.displayLevel1();


    }

}