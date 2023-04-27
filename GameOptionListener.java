package Intro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/* This class receives a GameOptionListener Instance
 and performs the action after an option is clicked
or selected
*/ 

public class GameOptionListener implements ActionListener{
    GamePlayOptions gpo;
    GameOptionListener(GamePlayOptions gpo)
    {
        this.gpo=gpo;
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        // Creates the GameWindow instance where the game is actually played
        gpo.setVisible(false);
        GameWindow beadgameW=new GameWindow();
    }  
}
