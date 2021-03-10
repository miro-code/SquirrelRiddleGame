import javax.swing.*;
import java.awt.event.*;
import java.awt.*;


public class Tile
{
    private JButton button;
    private boolean buttonUnchanged;
    private Picture original;
    private String current;
    private Squirrel squirrel;

    /** Creates a new Tile with a button an adds it to a JPanel

    @param panel the panel the tiles button is added to

	 */
    public Tile(JPanel panel, ActionListener actionListener)
    {
        squirrel = null;

        button = new JButton();
        button.setBorder(null);
        buttonUnchanged = true;
        //provide proper encapsulation - the button shouldnt be accessible in GameBoard
        panel.add(button);
        //button.addActionListener(actionListener);

    }


    /** Sets a buttons icon automatically extracting the icons name

    @param p the icon

	 */
    public void setButton(Picture p)
    {
        String filePath = p.getFilename();
        String[] folderSplit = filePath.split("/");
        String imageName = folderSplit[folderSplit.length-1];
        current = imageName.substring(0, imageName.length() - 4);
        //If this is the first time the icon is bein set save the name as original
        if(buttonUnchanged)
        {

            //If the original tile is a squirrel set empty
            if(current.equals("Hole") || current.equals("HoleNut") || current.equals("Empty") || current.equals("Flower") || current.equals("SquirrelFlower") )
            {
                original = p;
            }
            else
            {
                Picture empty = new Picture("icons/Empty.png", 0);
                original = empty;
                System.out.println("damn");
                System.out.println(current);
            }
            
            buttonUnchanged = false;
        }
        button.setIcon(p);
    }

    public void setButton(Picture p, Squirrel sq)
    {
        this.squirrel = sq;
        setButton(p);
    }

    public void setOriginalButton()
    {
        setButton(original);
    }


    public String getCurrent()
    {
        return current;
    }

    public Picture getOriginal()
    {
        return original;
    }
}