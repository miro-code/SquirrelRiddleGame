import javax.swing.*;

public class VictoryMenu
{
    private JFrame window;
    private JPanel panel;

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

    public void closeWindow()
    {
        window.dispose();
    }

}