
package "package name";

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;

/*
  GameWinner class is responsible for showing the winning message
  after game ends and gives options to play again and exit.
*/
public class GameWinner extends JFrame{
    JLabel text;
    JButton playAgain=new JButton("Play Again?");
    JButton exit=new JButton("EXIT");
    GameWinner(String message){
        showMessage(message);
        makeWindow();
        addListeners();
    }
    /* Creates the Game Winner window with 
     * Two Buttons
     * Size -> 550 X 930
     * Background Colour -> White
     */
     private void makeWindow()
    {
        setButtons(playAgain,150,48,250,50,35);
        setButtons(exit,200,100,100,50,30);
        setBounds(600,350,550,210);
        getContentPane().setBackground(Color.white);
        setLayout(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    /* Creates the buttons with their 
     * coordinate(x and y), length and width
     */
    private void setButtons(JButton jbutton,int x,int y,int length,int width,int tSize)
    {
        jbutton.setBounds(x,y,length,width);
        jbutton.setFont(new Font("Arial", Font.BOLD, tSize));
        add(jbutton);
    }
    // To show the message
    private void showMessage(String message)
    {
        text=new JLabel(message);
        text.setBounds(50, 0, 500, 50);
        text.setFont(new Font("Arial",Font.BOLD,30));
        text.setForeground(Color.red);
        add(text);
    }
    /* The method sets what will happen if the buttons are clicked 
    - Selecting "Play again" will open the game play window again
    - Selecting "Exit" will exit from the game.
    */
    private void addListeners()
    {
        playAgain.addActionListener(new GameWinOptionListener(this,"Yes"));
        exit.addActionListener(new GameWinOptionListener(this,"Exit"));
    }
}
