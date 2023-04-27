package "package name";

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
/*
   The class is for showing a pop up invalid message for invalid moves. 
*/
public class InvalidMessage extends JFrame{
    JLabel message=new JLabel("Invalid Move!");
    JButton okButton=new JButton("OK");
    InvalidMessage()
    {
        makeWindow();
        okButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                setVisible(false);
            }
        });
    }
    void makeWindow()
    {
        invalidMessage(message,20,0,250,30,35);
        buttonBackInGame(okButton,80,80,100,30,30);
        setBounds(700,400,280,150);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
