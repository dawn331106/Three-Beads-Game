
package "package name";

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
/* This class handles the Game Play options window.
Currently only one option is provided that is Player VS Player
*/
public final class GamePlayOptions extends JFrame{
    JButton option=new JButton("PLAYER VS PLAYER!");
    GamePlayOptions()
    {
        makeWindow();
        addListeners();
    }
    /* Creates the Game Play Options Wndow with 
     * A Button
     * Size -> 1200 X 930
     * Background Colour -> Red
     */
    void makeWindow()
    {
        setButtons(option,400,200,350,60,30);
        setBounds(400,30,1200,930);
        setLayout(null);
        setVisible(true);
        getContentPane().setBackground(Color.red);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    /* Creates the buttons with their 
     * coordinate(x and y), length and width
     */
    void setButtons(JButton jbutton,int x,int y,int length,int width,int tSize)
    {
        jbutton.setBounds(x,y,length,width);
        jbutton.setFont(new Font("Arial", Font.PLAIN, tSize));
        add(jbutton);
    }
    /* The method sets what will happen if the buttons are clicked 
    - Selecting Plater Vs Player will start the Game window 
      where the game will be played
    */
    void addListeners()
    {
        option.addActionListener(new GameOptionListener(this));
    }
}
