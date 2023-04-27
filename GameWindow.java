package "package name";

import java.awt.Color;
import javax.swing.*;

/* The class provides the Game Window 
 where the two players can play
*/
public class GameWindow extends JFrame{
    
    public GameWindow()
    {
        makeWindow();
        Gameplay gameplay=new Gameplay();
        // Add Game Play window instance to this window
        add(gameplay);
    }
    /* Creates the Game window with 
     * Size (1200*930)
     */
    private void makeWindow()
    {
        setBounds(400,30,1200,930);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
