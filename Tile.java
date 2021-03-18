import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 *
 * Tile for SCC.110 course work. Each instance represents one field on the game board.
 *
 * Author: Miran Özdogan
 *
 *
 **/
public class Tile
{
    private JButton button;
    private boolean buttonUnchanged;
    private Picture original; //stores the original field in case its covered by a squirrel
    private String originalName; //stores the Name of "Picture original"
    private String currentName;
    private Squirrel squirrel;

    /** Creates a new Tile with a button an adds it to a JPanel

    @param panel the panel the tiles button is added to

	 */
    public Tile(JPanel panel)
    {
        squirrel = null;

        button = new JButton();
        button.setBorder(null);
        buttonUnchanged = true;
        panel.add(button);

    }


    /** Sets a buttons icon automatically extracting the icons name

    @param p the icon

	 */
    public void setButton(Picture p)
    {
        String filePath = p.getFilename();
        String[] folderSplit = filePath.split("/");
        String imageName = folderSplit[folderSplit.length-1];
        currentName = imageName.substring(0, imageName.length() - 4);
        //If this is the first time the icon is bein set save the name as original
        if(buttonUnchanged)
        {

            //If the original tile is a squirrel set empty
            if(currentName.equals("Hole") || currentName.equals("HoleNut") || currentName.equals("Empty") || currentName.equals("Flower"))
            {
                original = p;
                originalName = currentName;
            }
            else
            {
                Picture empty = new Picture("icons/Empty.png", 0);
                original = empty;
                originalName = "Empty";
            }
            
            buttonUnchanged = false;
        }
        button.setIcon(p);
    }

    /** Checks if the given squirrel can be placed on this Tile
     * 
     * @param sq the squirrel that is checked for
     * @return whether the squirrel can be placed here

	 */

    public boolean isAccessible(Squirrel sq)
    {
        //the squirrels tail/head can access a tile if its head/tail is already on it 
        if(sq == squirrel)
        {
            return true;
        }

        return currentName.equals("Hole") || currentName.equals("HoleNut") || currentName.equals("Empty");

    }

    /** Sets this tiles image and squirrel
     * 
     * @param sq the squirrel that this tile is being assigned to
     * @return the image that this button shall display
	 */
    public void setButton(Picture p, Squirrel sq)
    {
        this.squirrel = sq;
        setButton(p);
    }

    /** sets the button to the picture in original
     * 
	 */
    public void displayOriginalButton()
    {
        setButton(original);
    }

    /** sets the original to a given picture
     * @param p the picture
	 */
    public void setOriginal(Picture p)
    {
        original = p;

        //extract name
        String filePath = p.getFilename();
        String[] folderSplit = filePath.split("/");
        String imageName = folderSplit[folderSplit.length-1];
        originalName = imageName.substring(0, imageName.length() - 4);
    }

    /** 
     * @return the name of the current picture on display 
	 */
    public String getCurrentName()
    {
        return currentName;
    }

    /** 
     * @return the picture in original
	 */
    public Picture getOriginal()
    {
        return original;
    }

    /** 
     * @return the name of the original picture 
	 */
    public String getOriginalName()
    {
        return originalName;
    }

    /** 
     * @return the Jbutton of this tile
	 */
    public JButton getButton()
    {
        return button;
    }

    /** 
     * @return the squirrel this tile is assigned to 
	 */
    public Squirrel getSquirrel()
    {
        return squirrel;
    }
}