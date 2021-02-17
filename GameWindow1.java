
package Intro;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public final class GameWindow1 extends JFrame{
    private final JButton option1,option2;
    GameWindow1()
    {
        option1=new JButton("NEW GAME!");
        option2=new JButton("EXIT!");
        buttonPosAndSize(option1,450,200,300,60);
        buttonPosAndSize(option2,450,300,300,60);
        setBounds(400,30,1200,930);
        getContentPane().setBackground(Color.yellow);
        setLayout(null);
        setVisible(true);
        setResizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        option2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                dispose();
            }
        });
        option1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                setVisible(false);
                GameWindow2 window2=new GameWindow2();
            }
        });
    }
    void buttonPosAndSize(JButton jbutton,int x,int y,int length,int width)
    {
        jbutton.setBounds(x,y,length,width);
        jbutton.setFont(new Font("Arial", Font.PLAIN, 45));
        add(jbutton);
    }
}
