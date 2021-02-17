package Intro;
import javax.swing.*;

public class Window extends JFrame{
    
    public Window()
    {
        setBounds(400,30,1200,930);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Gameplay gameplay=new Gameplay();
        add(gameplay);
    }
}