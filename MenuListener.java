import javax.swing.*;
import java.awt.event.*;

public class MenuListener implements ActionListener
{
    private Menu menu;
    private JButton level1;
    private JButton level2;
    private JButton level3;

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