import java.awt.*;
import java.awt.event.*;

import javax.swing.JButton;

public class MovementListener implements ActionListener
{
    GameBoard game;

    JButton up;
    JButton down;
    JButton right; 
    JButton left; 

    Tile[][] tiles;

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
        System.out.println("registered action");
        if(e.getSource() == up)
        {
                game.moveCurrentSquirrel(0);
        }
        else if(e.getSource() == right)
        {
            game.moveCurrentSquirrel(1);
        }
        else if(e.getSource() == down)
        {
            game.moveCurrentSquirrel(2);
        }
        else if(e.getSource() == left)
        {
            game.moveCurrentSquirrel(3);
        }
        else
        {
            
            for(int i = 0; i<tiles.length; i++)
            {
                for(int j = 0; j<tiles[i].length; j++)
                {
                    System.out.print("Checked ");
                    System.out.print(i);
                    System.out.println(j);
                    if(tiles[i][j].getButton() == e.getSource())
                    {
                        game.selectTile(i,j);
                        System.out.print("Selected: ");
                        System.out.print(i);
                        System.out.println(j);
                        break;
                        

                    }

                }
            }
        }                
        
    }
}