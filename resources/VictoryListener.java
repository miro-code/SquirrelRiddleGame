import javax.swing.*;
import java.awt.event.*;

public class VictoryListener implements ActionListener
{
    private VictoryMenu menu;

    public VictoryListener()
    {
        
        JButton menuButton = new JButton("CONGRATULATIONS - YOU WON! PRESS TO RETURN TO MENU");
        menuButton.addActionListener(this);
        menu = new VictoryMenu(menuButton);

    }


    public void actionPerformed(ActionEvent e)
    {
        menu.closeWindow();
        new MenuListener();
    }
}