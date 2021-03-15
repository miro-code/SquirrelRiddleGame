import javax.swing.*;
import java.awt.event.*;

public class VictoryListener implements ActionListener
{
    private VictoryMenu menu;
    private JFrame gameWindow;

    public VictoryListener(JFrame gameWindow)
    {
        
        JButton menuButton = new JButton("CONGRATULATIONS - YOU WON! PRESS THIS BUTTON TO RETURN TO THE LEVEL SELECTION");
        menuButton.addActionListener(this);
        menu = new VictoryMenu(menuButton);
        this.gameWindow = gameWindow;

    }


    public void actionPerformed(ActionEvent e)
    {
        menu.closeWindow();
        gameWindow.dispose();   //dispose closes the java vm if the closed one was the only window
        new MenuListener();
    }
}