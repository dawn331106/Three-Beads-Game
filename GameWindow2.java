
package Intro;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public final class GameWindow2 extends JFrame{
    JButton option1=new JButton("PLAYER VS PLAYER!");
    //JButton option2=new JButton("PLAYER VS COMPUTER!");
    GameWindow2()
    {
                setBounds(400,30,1200,930);
                setLayout(null);
                setVisible(true);
                getContentPane().setBackground(Color.red);
                setResizable(false);
                buttonPosAndSize(option1,400,200,350,60,30);
               // buttonPosAndSize(option2,400,300,350,60,25);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                option1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                setVisible(false);
                Window beadgameW=new Window();
            }
        });
              /*  option2.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        setVisible(false);
                        ComWindow1 opening=new ComWindow1();
                    }
                });*/
    }
    void buttonPosAndSize(JButton jbutton,int x,int y,int length,int width,int tSize)
    {
        jbutton.setBounds(x,y,length,width);
        jbutton.setFont(new Font("Arial", Font.PLAIN, tSize));
        add(jbutton);
    }
}
