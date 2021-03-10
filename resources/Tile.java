import javax.swing.*;

public class Tile
{
    private JButton button;
    private boolean buttonUnchanged;
    private String original;
    private String current;

    public Tile(JPanel panel)
    {

        button = new JButton();
        button.setBorder(null);
        buttonUnchanged = true;

        //provide proper encapsulation - the button shouldnt be accessible in GameBoard
        panel.add(button);

    }


    public void setButton(Picture p)
    {
        String filePath = p.getFilename();
        String[] folderSplit = filePath.split("/");
        String imageName = folderSplit[folderSplit.length];
        current = imageName.substring(0, imageName.length() - 4);
        System.out.println(current);

        if(buttonUnchanged)
        {
            original = current;
            buttonUnchanged = false;
        }
        button.setIcon(p);
    }

    public String getCurrent()
    {
        return current;
    }

    public String getOriginal()
    {
        return original;
    }
}