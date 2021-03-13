import javax.swing.*;

public class Menu
{
    private JFrame window;
    private JPanel panel;

    public Menu(JButton level1, JButton level2, JButton level3)
    {
        //Create the Frame
        window = new JFrame("Main Menu");
        //On close exit the program
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //set the frame to visible
        window.setVisible(true);

        panel = new JPanel();
        window.setContentPane(panel);

        panel.add(level1);
        panel.add(level2);
        panel.add(level3);


        //autosize
        window.pack();

    }

    public void closeWindow()
    {
        window.dispose();
    }

}