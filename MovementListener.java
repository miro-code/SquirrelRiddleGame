import java.awt.*;
import java.awt.event.*;

import javax.swing.JButton;

public class MovementListener implements ActionListener
{
    private GameBoard game;

    private JButton up;
    private JButton down;
    private JButton right; 
    private JButton left; 

    private Tile[][] tiles;

    public MovementListener(GameBoard game)
    {
        this.game = game;
        this.up = game.getUpButton();
        this.down = game.getDownButton();
        this.left = game.getLeftButton();
        this.right = game.getRightButton();
        this.tiles = game.getTiles();

        up.addActionListener(this);
        down.addActionListener(this);
        left.addActionListener(this);
        right.addActionListener(this);

        for(int i = 0; i<tiles.length; i++)
        {
            for(int j = 0; j<tiles[i].length; j++)
            {
                tiles[i][j].getButton().addActionListener(this);
            }
        }


    }

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