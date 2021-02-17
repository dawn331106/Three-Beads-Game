
package Intro;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GameWinner extends JFrame{
    JLabel text;
    JButton Play=new JButton("Play Again?");
    JButton exit=new JButton("EXIT");
    GameWinner(String message){
        showMessage(message);
        buttonPosAndSize(Play,150,48,250,50,35);
        buttonPosAndSize(exit,200,100,100,50,30);
        setBounds(600,350,550,210);
        getContentPane().setBackground(Color.white);
        setLayout(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Play.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                setVisible(false);
                Window beadgameW=new Window();
            }
        });
        exit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });
    }
    void buttonPosAndSize(JButton jbutton,int x,int y,int length,int width,int tSize)
    {
        jbutton.setBounds(x,y,length,width);
        jbutton.setFont(new Font("Arial", Font.BOLD, tSize));
        add(jbutton);
    }
    void showMessage(String message)
    {
        text=new JLabel(message);
        text.setBounds(50, 0, 500, 50);
        text.setFont(new Font("Arial",Font.BOLD,30));
        text.setForeground(Color.red);
        add(text);
    }
}
