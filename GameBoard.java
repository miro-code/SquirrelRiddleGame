import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.util.LinkedList;
/**
 *
 * Game Board for SCC.110 course work. Represents the Board on which the Nut Store Game is played.
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
    private JPanel gamePanel;
    private JPanel fieldPanel;
    private JPanel menuPanel; 

    private BorderLayout layout;
    private GridLayout fieldLayout;

    private Tile[][] tiles;

    private Picture empty;
    private Picture hole;
    private Picture flower;
    private Picture squirrelFlower;

    private Picture arrowUp;
    private Picture arrowDown;
    private Picture arrowRight;
    private Picture arrowLeft;

    private JButton up;
    private JButton down;
    private JButton right;
    private JButton left;

    private JButton backToMenu;

    private JLabel movesDisplay;

    private int xFields = 4;
    private int yFields = 4;

    private int moves = 0;

    private Squirrel currentSquirrel;
    private LinkedList<Squirrel> squirrels;

    private boolean victoryDisplayed;


    /** Creates a new GameBoard

	 */

    public GameBoard()
    {
        moves = 0;
        victoryDisplayed = false;
        squirrels = new LinkedList<Squirrel>();
        
        //Create the Frame
        window = new JFrame("Nut Store");
        //On close exit the program
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //calculate and set the window size; +2 for the arrows
        window.setSize( (xFields + 2 )* iconSize, (yFields+2) * iconSize );
        //set the frame to visible
        window.setVisible(true);


        panel = new JPanel();

        //create gamePanel with border layout for the gamefield and arrows
        gamePanel = new JPanel();
        
        //panel for the hole window
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(gamePanel, BorderLayout.CENTER);

        //panel for the menu
        menuPanel = new JPanel();

        layout = new BorderLayout();
        gamePanel.setLayout(layout);
        window.setContentPane(panel);

        //Create a field panel with grid layout for the game board (without the arrows)
        fieldPanel = new JPanel();
        fieldLayout = new GridLayout(yFields, xFields);
        fieldPanel.setLayout(fieldLayout);

        backToMenu = new JButton("Back to Menu");
        menuPanel.setLayout(new GridLayout(1,2));

        panel.add(menuPanel, BorderLayout.NORTH);
        menuPanel.add(backToMenu);

        movesDisplay = new JLabel("Moves: 0", SwingConstants.CENTER);
        menuPanel.add(movesDisplay);



        //Add the game field in the CENTER
        //This is the preffered way to insert the parameters according to oracles documentation
        gamePanel.add(fieldPanel, BorderLayout.CENTER);

        //initialize the fields array
        tiles = new Tile[xFields][yFields];

        //initialize the game field buttons
        //Introducing a class for them (similar to squirrel) would be unnessecary complicated and over-modling
        empty = new Picture("icons/Empty.png", 0);
        hole = new Picture("icons/Hole.png", 0);
        flower = new Picture("icons/Flower.png", 0);
        squirrelFlower = new Picture("icons/SquirrelFlower.png", 0);
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

        gamePanel.add(up, BorderLayout.NORTH);
        gamePanel.add(right, BorderLayout.EAST);
        gamePanel.add(down, BorderLayout.SOUTH);
        gamePanel.add(left, BorderLayout.WEST);


        for(int i = 0; i < xFields; i++)
        {
            for(int j = 0; j < yFields; j++)
            {
                tiles[j][i] = new Tile(fieldPanel);
            }
        }

        MovementListener movementListener = new MovementListener(this);
        window.pack();

    }

    /** Displays a squirrel on the game board

    @param sq squirrel to be displayed
    @throws IllegalArgumentException if at least one part of the squirrel is off the board
	 */
    public void displaySquirrel(Squirrel sq) throws IllegalArgumentException
    {

            //if there is a flower check if its on the board
            if(sq.getFlower())
            {
                if(sq.getFlowerX() < 0 || sq.getFlowerX() > xFields || sq.getFlowerY() < 0 || sq.getFlowerY() > yFields)
                {
                    throw new IllegalArgumentException("Squirrel located out of game board. Start rescue mission immediatly");
                }
            }
            //Check if the hole squirrel is on the board
            if(sq.getY() < 0 || sq.getY()>= yFields || sq.getX() < 0 || sq.getX() >= xFields || sq.getTailsX() < 0 || sq.getTailsX() >= xFields)
            {
                throw new IllegalArgumentException("Squirrel located out of game board. Start rescue mission immediatly");
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
        getHeadTile(sq).setButton(p1, sq);

        getTailTile(sq).setButton(p2, sq);
        
        if(sq.getFlower())
        {
            Picture p3 = new Picture("icons/SquirrelFlower.png", sq.getDirection() * 90);
            getFlowerTile(sq).setButton(p3, sq);
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


    /** Moves the current selected squirrel
     * 
    @param dir Direction the squirrel should be moved in
    @throws IllegalArgumentException if @param dir < 0 or > 3
	 */

    public void moveCurrentSquirrel(int dir) throws IllegalArgumentException
    {

        Squirrel sq = currentSquirrel; //short name 
        boolean legalMove = true;

        //check if the squirrel can be moved to the target tiles
        switch(dir)
        {
            case Squirrel.north:
                if(sq.getFlower())
                {
                    if(sq.getFlowerY() -1 < 0 )
                    {
                        legalMove = false;
                        break;
                    }
                }
                if(sq.getY() - 1 < 0 || sq.getTailsY() - 1 < 0)
                {
                    legalMove = false;
                    break;
                }
                legalMove = legalMove && tiles[sq.getX()][sq.getY()-1].isAccessible(sq);
                legalMove = legalMove && tiles[sq.getTailsX()][sq.getTailsY()-1].isAccessible(sq);
                if(sq.getFlower())
                {
                    legalMove = legalMove && tiles[sq.getFlowerX()][sq.getFlowerY()-1].isAccessible(sq);
                }
                break;
            case Squirrel.east:
                if(sq.getFlower())
                {
                    if(sq.getFlowerX() + 1 > xFields - 1)
                    {
                        legalMove = false;
                        break;
                    }
                }
                if( sq.getX() +1 > xFields-1 || sq.getTailsX() + 1 > xFields-1 )
                {
                    legalMove = false;
                    break;
                }
                legalMove = legalMove && tiles[sq.getX()+1][sq.getY()].isAccessible(sq);
                legalMove = legalMove && tiles[sq.getTailsX()+1][sq.getTailsY()].isAccessible(sq);
                if(sq.getFlower())
                {
                    System.out.println(legalMove);
                    legalMove = legalMove && tiles[sq.getFlowerX()+1][sq.getFlowerY()].isAccessible(sq);
                }

                break;
            case Squirrel.south:
                if(sq.getFlower())
                {
                    if(sq.getFlowerY() + 1 > yFields - 1)
                    {
                        legalMove = false;
                        break;
                    }
                }
                if( sq.getY() + 1 > yFields-1 ||  sq.getTailsY() + 1 > yFields-1 )
                {
                    legalMove = false;
                    break;
                }
                legalMove = legalMove && tiles[sq.getX()][sq.getY()+1].isAccessible(sq);
                legalMove = legalMove && tiles[sq.getTailsX()][sq.getTailsY()+1].isAccessible(sq);
                if(sq.getFlower())
                {
                    legalMove = legalMove && tiles[sq.getFlowerX()][sq.getFlowerY()+1].isAccessible(sq);                
                }
                break;
            case Squirrel.west:
                if(sq.getFlower())
                {
                    if(sq.getFlowerX() - 1 < 0 )
                    {
                        legalMove = false;
                        break;
                    }
                }
                if(sq.getX() - 1< 0 || sq.getTailsX() - 1 < 0)
                {
                    legalMove = false;
                    break;
                }
                legalMove = legalMove && tiles[sq.getX() - 1][sq.getY()].isAccessible(sq);
                legalMove = legalMove && tiles[sq.getTailsX() - 1][sq.getTailsY()].isAccessible(sq);
                if(sq.getFlower())
                {
                    legalMove = legalMove && tiles[sq.getFlowerX() - 1][sq.getFlowerY()].isAccessible(sq);
                }
                break;     
            default:
                throw new IllegalArgumentException("Squirrel facing into an unrecognized direction. It might have discovered the 3rd dimension. Beware.");
        }

        if(!legalMove)
        {
            System.out.println("Illegal move of " + currentSquirrel.getColor() + " squirrel");
            return;
        }


        getHeadTile(sq).displayOriginalButton();
        getTailTile(sq).displayOriginalButton();

        if(sq.getFlower())
        {
            getFlowerTile(sq).displayOriginalButton();
        }        
        sq.move(dir);
        displaySquirrel(sq);
        moves++;
        movesDisplay.setText("Moves: " + String.valueOf(moves));

        //Check if the squirrel drops its nut in this move
        if(sq.getNut())
        {
            if(getHeadTile(sq).getOriginalName().equals("Hole"))
            {
                dropNut(sq);

                Picture nut = new Picture("icons/HoleNut.png", sq.getDirection() * 90);
                getHeadTile(sq).setOriginal(nut);
            }
        }

        if(checkVictory())
        {
            System.out.println("VICTORY");
            victory();
        }
    }

    /** Displays the Victory Menu
	 */

    public void victory()
    {

        if(!victoryDisplayed)
        {
            new VictoryListener(window, moves);
            victoryDisplayed = true;
            window.setContentPane(gamePanel);
            window.pack();
        }

    }

    /** Checks if the Game is won
    
    @return boolean showing if game is won

	 */
    public boolean checkVictory()
    {
        boolean victory = true;
        for(int i = 0; i<squirrels.size(); i++)
        {
            if(squirrels.get(i).getNut())
            {
                victory = false;
            }
        }
        return victory;
    }

    /** Takes the nut away from the given squirrel 

    @param sq squirrel that drops its nut
	 */
    public void dropNut(Squirrel sq)
    {

        String currentName = getHeadTile(sq).getCurrentName();
        String newName = currentName.substring(0, currentName.length() - 3);
        String newFileName = "icons/" + newName + ".png";
        Picture newHead = new Picture(newFileName, sq.getDirection() * 90);
        getHeadTile(sq).setButton(newHead);
        sq.dropNut();

    }

    /** Adds squirrel to the game board

    @param sq squirrel to be added
	 */
    public void addSquirrel(Squirrel sq)
    {
        squirrels.add(sq);
        displaySquirrel(sq);
    }

    /** returns the Tile on which a squirrels head is displayed

    @param sq squirrel that we need the head of
    @return the tile on which the squirrels head is displayed

	 */
    public Tile getHeadTile(Squirrel sq)
    {
        return tiles[sq.getX()][sq.getY()];
    }

    /** returns the Tile on which a squirrels Tail is displayed

    @param sq squirrel that we need the Tail of
    @return the tile on which the squirrels head is displayed

	 */
    public Tile getTailTile(Squirrel sq)
    {
        return tiles[sq.getTailsX()][sq.getTailsY()];
    }

    /** returns the Tile on which a squirrels flower is displayed

    @param sq squirrel that we need the flower of
    @return the tile on which the squirrels flower is displayed
	 */
    public Tile getFlowerTile(Squirrel sq) throws IllegalArgumentException
    {
        if(!sq.getFlower())
        {
            throw new IllegalArgumentException("Cant return Flower Tile because squirrel has no flower");
        }
        return tiles[sq.getFlowerX()][sq.getFlowerY()];
    }

    /** If a given Tile contains a squirrel it is selected as the current squirrel

    @param i x coordinate of the tile
    @param j y coordinate of the tile

	 */
    public void selectTile(int i, int j)
    {
        if(tiles[i][j].getCurrentName().equals("Hole") || tiles[i][j].getCurrentName().equals("HoleNut") || tiles[i][j].getCurrentName().equals("Empty") || tiles[i][j].getCurrentName().equals("Flower") || tiles[i][j].getCurrentName().equals("SquirrelFlower") )
        {
            return;
        }
        else
        {
            currentSquirrel = tiles[i][j].getSquirrel();
            System.out.println("Set current squirrel to " + currentSquirrel.getColor());
        }
    }

        


    /** Get Method
     * @return the up button
	 */    
    public JButton getUpButton()
    {
        return up;
    }

    /** Get Method
     * @return the down button
	 */  
    public JButton getDownButton()
    {
        return down;
    }

    /** Get Method
     * @return the right button
	 */  
    public JButton getRightButton()
    {
        return right;
    }

    /** Get Method
     * @return the left button
	 */  
    public JButton getLeftButton()
    {
        return left;
    }

    /** Get Method
     * @return the 2D tiles array
	 */  
    public Tile[][] getTiles()
    {
        return tiles;
    }

    /** Get Method
     * @return the 2D tiles array
	 */  
    public JButton getBackToMenu()
    {
        return backToMenu;
    }

    /** Get Method
     * @return the window
	 */  
    public JFrame getWindow()
    {
        return window;
    }

    /** Displays an empty level on the board

    */
    public void displayEmptyLevel()
    {

        displayImage(0,0, empty);
        displayImage(1,0, empty);
        displayImage(2,0, hole);
        displayImage(3,0, empty);

        displayImage(0,1, hole);
        displayImage(1,1, empty);
        displayImage(2,1, empty);
        displayImage(3,1, empty);

        displayImage(0,2, empty);
        displayImage(1,2, hole);
        displayImage(2,2, empty);
        displayImage(3,2, empty);

        displayImage(0,3, empty);
        displayImage(1,3, empty);
        displayImage(2,3, empty);
        displayImage(3,3, hole);


    }

    /** Displays level on the board as described in the course work specification

    */
    public void displayLevel1()
    {        
        displayEmptyLevel();
        displayImage(1,2, flower); //That makes this tiles original hole and current flower but that doesnt matter because the flower doesnt move
   
        Squirrel red = new Squirrel("Red", true, 1,1,Squirrel.west);
        Squirrel grey = new Squirrel("Grey", true, 2,2 ,Squirrel.north);

        addSquirrel(red);
        addSquirrel(grey);
        currentSquirrel = red;

    }


    /** Displays level 2 on the board as described in the course work specification

    */
    public void displayLevel2()
    {
        displayEmptyLevel();

        displayImage(3,3, flower);

        Squirrel brown = new Squirrel("Brown", true, 0, 2, Squirrel.north);
        Squirrel black = new Squirrel("Black", true, 3, 2, Squirrel.south);

        addSquirrel(brown);
        addSquirrel(black);
        currentSquirrel = brown;


    }
   /** Displays level 3 on the board as described in the course work specification

    */
    public void displayLevel3()
    {
        displayEmptyLevel();
        
        Squirrel brown = new Squirrel("Brown", true, 2, 3, Squirrel.south);
        Squirrel black = new Squirrel("Black", true, 2, 1, Squirrel.south);
        Squirrel grey = new Squirrel("Grey", true, 3, 2, Squirrel.south);
        Squirrel red = new Squirrel("Red", true, 0, 2, Squirrel.west);


        addSquirrel(brown);
        addSquirrel(black);
        addSquirrel(grey);
        addSquirrel(red);
        currentSquirrel = brown;

    }

    

    /** Main Method for starting the Game

    */
    public static void main(String[] args)
    {
        new MenuListener();
    }

}