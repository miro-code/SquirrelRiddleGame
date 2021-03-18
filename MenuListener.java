import javax.swing.*;
import java.awt.event.*;


/**
 *
 * Menu Listener for SCC.110 course work. Action Listener to deal with user input on the menu
 *
 * Author: Miran Özdogan
 *
 *
 **/
public class MenuListener implements ActionListener
{
    private Menu menu;
    private JButton level1;
    private JButton level2;
    private JButton level3;

    /** @return a MenuListener after creating a Menu and assigning the MenuListener to it

	 */
    public MenuListener()
    {

        level1 = new JButton("Level 1");
        level2 = new JButton("Level 2");
        level3 = new JButton("Level 3");

        level1.addActionListener(this);
        level2.addActionListener(this);
        level3.addActionListener(this);

        menu = new Menu(level1, level2, level3);

    }


    /** Is called when a button this ActionListener was assigend to is pressed
     * 
     * @param e The ActionEvent that lead to the call of this method

	 */

    public void actionPerformed(ActionEvent e)
    {
        menu.closeWindow();

        if(e.getSource() == level1)
        {
            (new GameBoard()).displayLevel1();
        }

        if(e.getSource() == level2)
        {
            (new GameBoard()).displayLevel2();
        }

        if(e.getSource() == level3)
        {
            (new GameBoard()).displayLevel3();
        }
    }

}