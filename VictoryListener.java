import javax.swing.*;
import java.awt.event.*;

/**
 *
 * Victory Listener for SCC.110 course work. ActionListener to handle a click on the Victory message
 *
 * Author: Miran Özdogan
 *
 *
 **/



public class VictoryListener implements ActionListener
{
    private VictoryMenu menu;
    private JFrame gameWindow;

    /** Returns a VictoryListener after creating a VictoryMenu and assigning the VictoryListener to it

	 */
    public VictoryListener(JFrame gameWindow, int moves)
    {
        
        JButton menuButton = new JButton("CONGRATULATIONS - YOU WON IN " + moves + " MOVES! PRESS TO RETURN TO MENU");
        menuButton.addActionListener(this);
        menu = new VictoryMenu(menuButton);
        this.gameWindow = gameWindow;

    }

    /** Is called when a button this ActionListener was assigend to is pressed
     * 
     * @param e The ActionEvent that lead to the call of this method

	 */
    public void actionPerformed(ActionEvent e)
    {
        menu.closeWindow();
        gameWindow.dispose();   //dispose closes the java vm if the closed one was the only window
        new MenuListener();
    }
}