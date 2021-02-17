
package Intro;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Invalid extends JFrame{
    JLabel message=new JLabel("Invalid Move!");
    JButton click=new JButton("OK");
    Invalid()
    {
        invalidMessage(message,20,0,250,30,35);
        buttonBackInGame(click,80,80,100,30,30);
        setBounds(700,400,280,150);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        click.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                setVisible(false);
            }
        });
    }
    void invalidMessage(JLabel message,int x,int y,int length,int width,int tSize){
        message.setBounds(x, y,length, width);
        message.setFont(new Font("Arial",Font.ITALIC,tSize));
        message.setForeground(Color.BLACK);
        add(message);
    }
    void buttonBackInGame(JButton button,int x,int y,int length,int width,int tSize)
    {
        button.setBounds(x,y,length,width);
        button.setFont(new Font("Arial",Font.ITALIC,tSize));
        button.setForeground(Color.BLACK);
        add(button);
    }
}
