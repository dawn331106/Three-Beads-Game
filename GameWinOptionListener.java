
package Intro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWinOptionListener implements ActionListener{
    GameWinner gamewinner;
    String message;
    GameWinOptionListener(GameWinner gamewinner, String message)
    {
        this.gamewinner=gamewinner;
        this.message=message;
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(message.equals("EXIT"))
        {
            System.exit(0);
        }
        else
        {
            gamewinner.setVisible(false);
            GameWindow beadgameW=new GameWindow();
        }
    }
    
}
