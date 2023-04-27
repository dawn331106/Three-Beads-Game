package Intro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/* This class receives a Menu Instance and a message
 to determine what action to perform after an option is clicked
or selected
*/ 
public class MenuOptionListener implements ActionListener{
    private Menu menu;
    private String message;
    MenuOptionListener(Menu menu,String message)
    {
        this.menu=menu;
        this.message=message;
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        /* Exits fromt he Menu if Exit is selected */
        if(message.equals("Dispose"))
        {
            menu.dispose();
        }
        else{
            /* Creates a new Game Play Options Window
               when New Game is clicked from Menu
            */
            menu.setVisible(false);
            GamePlayOptions newgame=new GamePlayOptions();
        }
    }
    
}

    
