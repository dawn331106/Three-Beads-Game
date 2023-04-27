

package "package name" ;

import java.awt.*;
import javax.swing.*;

/* 
The class is resposible for organizing the Menu Window like -
* Menu display
   - Number of Buttons,Button Size and their positions
   - Background Colour
* Adding and Removing Menu Options
*/
public final class Menu extends JFrame{
    // Two options added "New Game" and "Exit"
    private final JButton menuOption1=new JButton("NEW GAME");
    private final JButton menuOption2=new JButton("EXIT");
    Menu()
    {
        makeWindow();
        addListeners();
    }
    /* Creates the menu window with 
     * Two Buttons
     * Size (1200*930)
     * Background Colour(Yellow)
     */
    private void makeWindow()
    {
        setButtons(menuOption1,450,200,300,60);
        setButtons(menuOption2,450,300,300,60);
        setBounds(400,30,1200,930);
        getContentPane().setBackground(Color.yellow);
        setLayout(null);
        setVisible(true);
        setResizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }
    /* Creates the buttons with their 
     * coordinate(x and y), length and width
     */
    private void setButtons(JButton jbutton,int x,int y,int length,int width)
    {
        jbutton.setBounds(x,y,length,width);
        jbutton.setFont(new Font("Arial", Font.PLAIN, 45));
        add(jbutton);
    }
    /* The method sets what will happen if the buttons are clicked 
    - Selecting Menuoption1(New Game) will open the Game Play Options window
    - Selecting Menuoption2(Exit) will exit from the Menu.
    */
    private void addListeners()
    {
        menuOption1.addActionListener(new MenuOptionListener(this,"Continue"));
        menuOption2.addActionListener(new MenuOptionListener(this,"Dispose"));
    }
}
