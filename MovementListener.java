import java.awt.*;
import java.awt.event.*;

import javax.swing.JButton;

/**
 *
 * MovementListener for SCC.110 course work. ActionListener to deal with input during the course of a game. 
 * 
 * Author: Miran Özdogan
 *
 *
 **/

public class MovementListener implements ActionListener
{
    private GameBoard game;

    private JButton up;
    private JButton down;
    private JButton right; 
    private JButton left; 
    private JButton backToMenu;

    private Tile[][] tiles;

    
    /** @return an instence of a movement listener
     * 
     * @param game The game board whose buttons this listener is added to 
     * 
	 */

    public MovementListener(GameBoard game)
    {
        this.game = game;
        this.up = game.getUpButton();
        this.down = game.getDownButton();
        this.left = game.getLeftButton();
        this.right = game.getRightButton();
        this.tiles = game.getTiles();
        this.backToMenu = game.getBackToMenu();


        up.addActionListener(this);
        down.addActionListener(this);
        left.addActionListener(this);
        right.addActionListener(this);
        backToMenu.addActionListener(this);

        for(int i = 0; i<tiles.length; i++)
        {
            for(int j = 0; j<tiles[i].length; j++)
            {
                tiles[i][j].getButton().addActionListener(this);
            }
        }


    }

    /** Is called when a button this ActionListener was assigend to is pressed
     * 
     * @param e The ActionEvent that lead to the call of this method

	 */
    public void actionPerformed(ActionEvent e)
    {        
        if(e.getSource() == up)
        {
            game.moveCurrentSquirrel(Squirrel.north);
        }
        else if(e.getSource() == right)
        {
            game.moveCurrentSquirrel(Squirrel.east);
        }
        else if(e.getSource() == down)
        {
            game.moveCurrentSquirrel(Squirrel.south);
        }
        else if(e.getSource() == left)
        {
            game.moveCurrentSquirrel(Squirrel.west);
        }
        else if(e.getSource() == backToMenu)
        {
            game.getWindow().dispose();
            new MenuListener();
        }
        else
        {
            
            for(int i = 0; i<tiles.length; i++)
            {
                for(int j = 0; j<tiles[i].length; j++)
                {
                    if(tiles[i][j].getButton() == e.getSource())
                    {
                        game.selectTile(i,j);
                        return;
                        

                    }

                }
            }
        }                
        
    }
}