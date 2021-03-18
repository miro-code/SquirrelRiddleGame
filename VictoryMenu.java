import javax.swing.*;

/**
 *
 * Victory Menu for SCC.110 course work. Instances display a victory message
 *
 * Author: Miran Özdogan
 *
 *
 **/

public class VictoryMenu
{
    private JFrame window;
    private JPanel panel;

    /** @return an instance of VictoryMenu displaying a Victory Message on a button that can be clicked to return to the Level Menu

	 */
    public VictoryMenu(JButton menuButton)
    {
        //Create the Frame
        window = new JFrame("VICTORY!");
        //On close exit the program
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //set the frame to visible
        window.setVisible(true);

        panel = new JPanel();
        window.setContentPane(panel);

        panel.add(menuButton);

        //autosize
        window.pack();

    }

    /** Disposes the games window
	 */
    public void closeWindow()
    {
        window.dispose();
    }

}