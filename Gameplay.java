package "package name";

import java.awt.*;
import java.awt.event.*;
import static java.lang.Thread.sleep;
import javax.swing.*;
/*
     This class handles the gameplay of two players  
*/
public class Gameplay extends JPanel implements ActionListener,KeyListener,MouseListener{
    // Coordinates of three beads of the first player
    private int x1=15,y1=28,x2=415,y2=28,x3=815,y3=28;
    
     // Coordinates of three beads of the second player
    private int a1=15,b1=828,a2=415,b2=828,a3=815,b3=828;
    
    // Current x and y coordinate and a variable to determine the turn
    private int currX,currY,turn=1;
    
    JLabel label=new JLabel();
    /*
     * Timer will fire an event every 100 milliseconds or 0.1 seconds.
     * Whenever the Timer fires an event, it will call the actionPerformed 
       method of the current object.
     */
    private Timer time=new Timer(100,this);
    
    // An image is required for the board
    private Image image;
    
    //Player 1 inital clock 5 minutes, 0 sec and 0 milliseconds 
    private int minutesP1=5, secondsP1=0, milliSecondsP1=0;
    
    //Player 2 inital clock 5 minutes, 0 sec and 0 milliseconds 
    private int minutesP2=5, secondsP2=0, milliSecondsP2=0;
    
    // To notify the lock
    private int turnover=1;
    
    // To determine the winner depending on whose timer has ended
    private boolean player1Won=false,player2Won=false;
    
    // To stop the game if someone wins
    private int stop=1;
    
    //Used for synchrnization between the two clocks
    Object lock=new Object();
    
    // To show the remaining time of player 1
    JLabel remTime1=new JLabel();
    
    // to show the remaining time of player 2
    JLabel remTime2=new JLabel();
 
    Gameplay()
    {
        time.start();
        clock1();
        clock2();
        setWindow();
    }
    void setWindow()
    {
        addMouseListener(this);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        setBounds(0,0,1200,930);
    }
    // Clock of Player 1
    public void clock1()
    {
        Thread clockp1=new Thread()
           {
             public void run()
                {
                    try{
                         synchronized(lock){
                          for(;;)
                             {
                                sleep(1);
                                while(turnover==0){
                                   lock.wait();
                                  }
                                if(milliSecondsP1>950){
                                milliSecondsP1=0;
                                secondsP1--;
                                  }
                                if(secondsP1<0)
                                  {
                                     milliSecondsP1=0;
                                     secondsP1=59;
                                     minutesP1--;
                                  }
                                if(minutesP1==0&&secondsP1==0)
                                  {
                                     player2Won=true;
                                     break;
                                  }
                                if(stop==0) break;
                                milliSecondsP1++;
                                if(turnover==1){
                                lock.notify();
                                }
                            }
                        }
                   }
               catch(Exception e)
                    {
                         System.out.println("Exception caught");
                    }
                }
          };
        clockp1.start();
    }
    // Clock of Player 2
    public void clock2()
    {
        Thread clockp2=new Thread()
           {
             public void run()
                {
                    try{
                         synchronized(lock){
                           for(;;)
                               {
                                  sleep(1);
                                  while(turnover==1){
                                  lock.wait();
                                      }
                                 if(milliSecondsP2>950){
                                    milliSecondsP2=0;
                                    secondsP2--;
                                    }
                                 if(secondsP2<0)
                                    {
                                       milliSecondsP2=0;
                                       secondsP2=59;
                                       minutesP2--;
                                    }
                                if(minutesP2==0&&secondsP2==0)
                                    {
                                       player1Won=true;
                                       break;
                                    }
                                if(stop==0) break;
                                milliSecondsP2++;
                                if(turnover==0){
                                   lock.notify();
                                 }
                           }
                       }
                   }
                catch(Exception e)
                {
                       System.out.println("Exception caught");
                }
               }
          };
        clockp2.start();
    }
    /*
      To update the game board including coloring the updated beads 
      and time after every turn.
    */
    @Override
    public void paint(Graphics g)
    {
        // Player 1 timer
        remTime1.setText("Time Left P1="+minutesP1+":"+secondsP1);
        remTime1.setBounds(870,40,350,50);
        Font f1=new Font("Arial",Font.ITALIC,28);
        remTime1.setFont(f1);
        remTime1.setForeground(Color.black);
        add(remTime1);
        
        //Player 2 timer
        remTime2.setText("Time Left P2="+minutesP2+":"+secondsP2);
        remTime2.setBounds(870,150,350,50);
        Font f2=new Font("Arial",Font.ITALIC,28);
        remTime2.setFont(f2);
        remTime2.setForeground(Color.black);
        add(remTime2);
        
        super.paint(g);
        
        //Background color        
        setBackground(Color.yellow);
        
        // Game Board source File
        g.drawImage(new ImageIcon("Game board path").getImage(),15,20,this);
        
        // Fill the color of the updated coordinates after a turn
        g.setColor(Color.red);
        g.fillOval(x1, y1, 50, 50);
        g.fillOval(x2, y2, 50, 50);
        g.fillOval(x3, y3, 50, 50);
        g.setColor(Color.blue);
        g.fillOval(a1, b1, 50, 50);
        g.fillOval(a2, b2, 50, 50);
        g.fillOval(a3, b3, 50, 50);
        g.dispose();
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        time.start();
        repaint();
        // Player 2 time has ended
        if(player1Won){gameEnded("Congratulations!Player 1 wins!");}
        // Player 1 time has ended
        else if(player2Won) {gameEnded("Congratulations!Player 2 wins!");}
        /* Three winning conditions are checked for player 1 beads 
         * The beads are in horizontal line
         * The beads are in vertical line
         * The bead are in Diagonal line
        */
        else if ((x1==x2 && x2==x3 && y1+y2+y3==1284) || //Horizontal 
             (y1==y2 && y2==y3 && y1!=28 && x1+x2+x3==1245) || // Vertical 
                ((x1+y1==x2+y2 && x2+y2==x3+y3) || //Diagonal
                (x1==y1-13 && x2==y2-13 && x3==y3-13))) 
        {
            gameEnded("Congratulations!Player 1 wins!");
        }
        // The same conditions are checked for player 2 beads
         else if ((a1==a2 && a2==a3 && b1+b2+b3==1284) || //Horizontal 
             (b1==b2 && b2==b3 && b1!=828 && a1+a2+a3==1245) || // Vertical 
                ((a1+b1==a2+b2 && a2+b2==a3+b3) ||  //Diagonal 
                 (a1==b1-13 && a2==b2-13 && a3==b3-13))) 
        {
            gameEnded("Congratulations!Player 2 wins!");
        }
    } 
     
    /* 
       To get the coordinate of where the mouse is clicked.
       The current x and y coordinate will be set with the values
       of the nearest bead.
    */
    @Override
    public void mouseClicked(MouseEvent clickedHere) {
      currX=clickedHere.getX();
      currY=clickedHere.getY();
      if((x1+100)>=currX&&(x1-100)<=currX&&(y1+100)>=currY&&(y1-100)<=currY)
        {
            currX=x1;
            currY=y1;
        }
      else if((x2+100)>=currX&&(x2-100)<=currX&&(y2+100)>=currY&&(y2-100)<=currY)
        {
            currX=x2;
            currY=y2;
        }
      else if((x3+100)>=currX&&(x3-100)<=currX&&(y3+100)>=currY&&(y3-100)<=currY)
        {
            currX=x3;
            currY=y3;
        }
      else if((a1+100)>=currX&&(a1-100)<=currX&&(b1+100)>=currY&&(b1-100)<=currY)
        {
            currX=a1;
            currY=b1;
        }
      else if((a2+100)>=currX&&(a2-100)<=currX&&(b2+100)>=currY&&(b2-100)<=currY)
        {
            currX=a2;
            currY=b2;
        }
      else if((a3+100)>=currX&&(a3-100)<=currX&&(b3+100)>=currY&&(b3-100)<=currY)
        {
            currX=a3;
            currY=b3;
        }
    }
    @Override
    public void mousePressed(MouseEvent me) {}

    @Override
    public void mouseReleased(MouseEvent me) {}
    @Override
    public void mouseEntered(MouseEvent me) {}
    @Override
    public void mouseExited(MouseEvent me) {}
    @Override
    public void keyTyped(KeyEvent ke) {}
    @Override
    public void keyReleased(KeyEvent ke){}
    
    //Move the current bead
    @Override
    public void keyPressed(KeyEvent ke) {
        /* 
           If current coordinate belongs to first player bead
           and its not his turn, then its an invalid move
        */
        if(((currX==x1&&currY==y1)||(currX==x2&&currY==y2)||(currX==x3&&currY==y3))&&turn%2==0)
        {
            InvalidMessage message=new InvalidMessage();
        }
        /* 
           If current coordinate belongs to second player bead
           and its not his turn, then its an invalid move
        */
        else if(((currX==a1&&currY==b1)||(currX==a2&&currY==b2)||(currX==a3&&currY==b3))&&turn%2!=0)
        {
            InvalidMessage message=new InvalidMessage();
        }
        // Move down
        else if(ke.getKeyCode()==KeyEvent.VK_DOWN)
        {
            int temp_y=updateY('d',currY);
            if(!check(currX,temp_y))
            { 
                InvalidMessage message=new InvalidMessage(); 
            }
            else
            {  
                assignXandY(currX,currY,-1,temp_y);
            }
        }
        // Move up
        else if(ke.getKeyCode()==KeyEvent.VK_UP)
        {
            int temp_y=updateY('u',currY);
            if(!check(currX,temp_y))
            { 
                InvalidMessage message=new InvalidMessage(); 
            }
            else
            { assignXandY(currX,currY,-1,temp_y);}
        }
        // Move right
        else if(ke.getKeyCode()==KeyEvent.VK_RIGHT)
        {
            int temp_x=updateX('r',currX);
            if(!check(temp_x,currY))
            {
                InvalidMessage message=new InvalidMessage(); 
            }
            else
            { 
                assignXandY(currX,currY,temp_x,-1);
            }
        }
        // Move left
        else if(ke.getKeyCode()==KeyEvent.VK_LEFT)
        {
            int temp_x=updateX('l',currX);
            if(!check(temp_x,currY))
            { 
                InvalidMessage message=new InvalidMessage(); 
            }
            else
            {  
                assignXandY(currX,currY,temp_x,-1);
            }
        }
        // Move up-right
        else if(ke.getKeyCode()==KeyEvent.VK_I)
        {
            int temp_y=updateY('u',currY);
            int temp_x=updateX('r',currX);
            if((currX==15&&currY==428)||(currX==415&&currY==828)||
                check(temp_x,temp_y)==false) 
            { 
                InvalidMessage message=new InvalidMessage();
            }
            else
            {  
                assignXandY(currX,currY,temp_x,temp_y);
            }
        }
        // Move up-left
        else if(ke.getKeyCode()==KeyEvent.VK_Y)
        {
            int temp_y=updateY('u',currY);
            int temp_x=updateX('l',currX);
            if((currX==815&&currY==428)||(currX==415&&currY==828)||
                    check(temp_x,temp_y)==false) 
            {
                InvalidMessage message=new InvalidMessage(); 
            }
            else
            { 
                assignXandY(currX,currY,temp_x,temp_y);
            }
        }
        // Move down-right
        else if(ke.getKeyCode()==KeyEvent.VK_M)
        {
            int temp_y=updateY('d',currY);
            int temp_x=updateX('r',currX);
            if((currX==15&&currY==428)||(currX==415&&currY==28)||
                    check(temp_x,temp_y)==false) 
            { 
                InvalidMessage message=new InvalidMessage(); 
            }
            else
            { 
                assignXandY(currX,currY,temp_x,temp_y); 
            }
        }
        // Move down-left
        else if(ke.getKeyCode()==KeyEvent.VK_B)
        {
            int temp_y=updateY('d',currY);
            int temp_x=updateX('l',currX);
            if((currX==815&&currY==428)||(currX==415&&currY==28)||
               check(temp_x,temp_y)==false) 
            { 
                InvalidMessage message=new InvalidMessage(); 
            }
            else
            {  
                assignXandY(currX,currY,temp_x,temp_y);
            }
        }
    }
    // Game winner message
    void gameEnded(String message)
    {
        time.stop();
        stop=0;
        GameWinner win_message=new GameWinner(message);
    }
    // Update y coordinate
    int updateY(char direction,int y)
    {
        return direction=='d'?y+400:y-400;
    }
    // Update x coordinate
    int updateX(char direction,int x)
    {
        return direction=='r'?x+400:x-400;
    }
    /* 
       This function checks if 
       updated x and y coordinate has a bead or not.
    */
    boolean check(int x,int y)
    {
        int sum=x*y;
        if((sum!=(x1*x2))&&(sum!=(x2*y2))&&(sum!=(x3*y3))&&(sum!=(a1*b1))&&
               (sum!=(a2*b2))&&(sum!=(a3*b3))&&x>=15&&x<=815&&y>=28&&y<=828)
        {
            turn++;
            if(turn%2!=0) { turnover=1; }
            else { turnover=0; }
            return true;
        }
        else{ return false; }
    }
    // This method assigns the updated coordinate
    void assignXandY(int px,int py,int temp_x,int temp_y)
    {
        // Assigning both x and y for diagonal movements
        if(temp_x!=-1&&temp_y!=-1)
        {
            if(px==x1&&py==y1){ x1=temp_x; y1=temp_y; }
            else if(px==x2&&py==y2) {x2=temp_x; y2=temp_y;}
            else if(px==x3&&py==y3) {x3=temp_x; y3=temp_y;}
            else if(px==a1&&py==b1) {a1=temp_x; b1=temp_y;}
            else if(px==a2&&py==b2) {a2=temp_x; b2=temp_y;}
            else if(px==a3&&py==b3) {a3=temp_x; b3=temp_y;}
        }
        
        // Assigning only y coordinate
        else if(temp_x==-1)
        {
            if(px==x1&&py==y1){ y1=temp_y;}
            else if(px==x2&&py==y2) {y2=temp_y; }
            else if(px==x3&&py==y3) {y3=temp_y; }
            else if(px==a1&&py==b1) {b1=temp_y; }
            else if(px==a2&&py==b2) {b2=temp_y; }
            else if(px==a3&&py==b3) {b3=temp_y; }
        }
        
        // Assigning only x coordinate
        else
        {
            if(px==x1&&py==y1){ x1=temp_x;}
            else if(px==x2&&py==y2) {x2=temp_x; }
            else if(px==x3&&py==y3) {x3=temp_x; }
            else if(px==a1&&py==b1) {a1=temp_x; }
            else if(px==a2&&py==b2) {a2=temp_x; }
            else if(px==a3&&py==b3) {a3=temp_x; }
        }
    }
}
 
    
   
    
