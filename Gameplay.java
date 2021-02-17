
package Intro;

import java.awt.*;
import java.awt.event.*;
import static java.lang.Thread.sleep;
import javax.swing.*;
public class Gameplay extends JPanel implements ActionListener,KeyListener,MouseListener{
    private int x1=15,y1=28,x2=415,y2=28,x3=815,y3=28;
    private int a1=15,b1=828,a2=415,b2=828,a3=815,b3=828;
    private int px,py,turn=1;
    JLabel label=new JLabel();
    private Timer time=new Timer(100,this);
    private Image image;
    private int mil1=0,sec1=0,min1=5;
    private int mil2=0,sec2=0,min2=5;
    private int turnover=1;
    private int p1lost=1,p2lost=1;
    private int stop=1;
    Object lock=new Object();
    JLabel showtime1=new JLabel();
    JLabel showtime2=new JLabel();
    JLabel play2=new JLabel();
    JButton back=new JButton("Back");
    private int error=0;
    Gameplay()
    {
        time.start();
        clock1();
        clock2();
        addMouseListener(this);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        setBounds(0,0,1200,930);
        back.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                setVisible(false);
                GameWindow2 window2=new GameWindow2();
            }
        });
    }
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
                                if(mil1>950){
                                mil1=0;
                                sec1--;
                                  }
                                if(sec1<0)
                                  {
                                     mil1=0;
                                     sec1=59;
                                     min1--;
                                  }
                                if(min1==0&&sec1==0)
                                  {
                                     p1lost=0;
                                     break;
                                  }
                                if(stop==0) break;
                                mil1++;
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
                                 if(mil2>950){
                                    mil2=0;
                                    sec2--;
                                    }
                                 if(sec2<0)
                                    {
                                       mil2=0;
                                       sec2=59;
                                       min2--;
                                    }
                                if(min2==0&&sec2==0)
                                    {
                                       p2lost=0;
                                       break;
                                    }
                                if(stop==0) break;
                                mil2++;
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
    @Override
    public void paint(Graphics g)
    {
        back.setBounds(900,500,150,65);
        back.setFont(new Font("Arial",Font.ITALIC,40));
        back.setForeground(Color.BLACK);
        add(back);
        showtime1.setText("Time Left P1="+min1+":"+sec1);
        showtime1.setBounds(870,40,350,50);
        Font f1=new Font("Arial",Font.ITALIC,28);
        showtime1.setFont(f1);
        showtime1.setForeground(Color.black);
        add(showtime1);
        showtime2.setText("Time Left P2="+min2+":"+sec2);
        showtime2.setBounds(870,150,350,50);
        Font f2=new Font("Arial",Font.ITALIC,28);
        showtime2.setFont(f2);
        showtime2.setForeground(Color.black);
        add(showtime2);
        super.paint(g);
        setBackground(Color.yellow);
        g.drawImage(new ImageIcon("F:\\bead.png").getImage(),15,20,this);
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
        if(p2lost==0){gameEnded("Congratulations!Player 1 wins!");}
        else if(p1lost==0) {gameEnded("Congratulations!Player 2 wins!");}
        else if((x1==x2&&x2==x3)||((x1!=x2&&x2!=x3&&x3!=x1&&y1!=y2&&y2!=y3&&y3!=y1)&&
                ((x1==415&&y1==428)||(x2==415&&y2==428)||(x3==415&&y3==428))))
            {gameEnded("Congratulations!Player 1 wins!");}
        else if(y1==y2&&y2==y3&&y1!=28)
            {gameEnded("Congratulations!Player 1 wins!");}
        else if((a1==a2&&a2==a3)||((a1!=a2&&a2!=a3&&a3!=a1&&b1!=b2&&b2!=b3&&b3!=b1)&&
                ((a1==415&&b1==428)||(a2==415&&b2==428)||(a3==415&&b3==428))))
            { gameEnded("Congratulations!Player 2 wins!");}
        else if((b1==b2&&b2==b3&&b1!=828))
            {gameEnded("Congratulations!Player 2 wins!");}
    } 

    @Override
    public void mouseClicked(MouseEvent me) {
      px=me.getX();
      py=me.getY();
        if((x1+100)>=px&&(x1-100)<=px&&(y1+100)>=py&&(y1-100)<=py)
        {
            px=x1;
            py=y1;
        }
        else if((x2+100)>=px&&(x2-100)<=px&&(y2+100)>=py&&(y2-100)<=py)
        {
            px=x2;
            py=y2;
        }
        else if((x3+100)>=px&&(x3-100)<=px&&(y3+100)>=py&&(y3-100)<=py)
        {
            px=x3;
            py=y3;
        }
        else if((a1+100)>=px&&(a1-100)<=px&&(b1+100)>=py&&(b1-100)<=py)
        {
            px=a1;
            py=b1;
        }
        else if((a2+100)>=px&&(a2-100)<=px&&(b2+100)>=py&&(b2-100)<=py)
        {
            px=a2;
            py=b2;
        }
        else if((a3+100)>=px&&(a3-100)<=px&&(b3+100)>=py&&(b3-100)<=py)
        {
            px=a3;
            py=b3;
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
    @Override
    public void keyPressed(KeyEvent ke) {
        if(((px==x1&&py==y1)||(px==x2&&py==y2)||(px==x3&&py==y3))&&turn%2==0)
        {
            Invalid move=new Invalid();
        }
        else if(((px==a1&&py==b1)||(px==a2&&py==b2)||(px==a3&&py==b3))&&turn%2!=0)
        {
            Invalid move=new Invalid();
        }
        else if(ke.getKeyCode()==KeyEvent.VK_DOWN)
        {
            int temp_y=updateY('d',px,py);
            if(check(px,temp_y)==false){ Invalid move=new Invalid(); }
            else
            {  assignXandY(px,py,-1,temp_y);}
        }
        else if(ke.getKeyCode()==KeyEvent.VK_UP)
        {
            int temp_y=updateY('u',px,py);
            if(check(px,temp_y)==false){ Invalid move=new Invalid(); }
            else
            { assignXandY(px,py,-1,temp_y);}
        }
        else if(ke.getKeyCode()==KeyEvent.VK_RIGHT)
        {
            int temp_x=updateX('r',px,py);
            if(check(temp_x,py)==false){ Invalid move=new Invalid(); }
            else
            { assignXandY(px,py,temp_x,-1);}
        }
        else if(ke.getKeyCode()==KeyEvent.VK_LEFT)
        {
            int temp_x=updateX('l',px,py);
            if(check(temp_x,py)==false){ Invalid move=new Invalid(); }
            else
            {  assignXandY(px,py,temp_x,-1);}
        }
        else if(ke.getKeyCode()==KeyEvent.VK_I)
        {
            int temp_y=updateY('u',px,py);
            int temp_x=updateX('r',px,py);
            if((px==15&&py==428)||(px==415&&py==828)||check(temp_x,temp_y)==false) { Invalid move=new Invalid(); }
            else
            {  assignXandY(px,py,temp_x,temp_y);}
        }
        else if(ke.getKeyCode()==KeyEvent.VK_Y)
        {
            int temp_y=updateY('u',px,py);
            int temp_x=updateX('l',px,py);
            if((px==815&&py==428)||(px==415&&py==828)||check(temp_x,temp_y)==false) {Invalid move=new Invalid(); }
            else
            { assignXandY(px,py,temp_x,temp_y);}
        }
        else if(ke.getKeyCode()==KeyEvent.VK_M)
        {
            int temp_y=updateY('d',px,py);
            int temp_x=updateX('r',px,py);
            if((px==15&&py==428)||(px==415&&py==28)||check(temp_x,temp_y)==false) { Invalid move=new Invalid(); }
            else
            { assignXandY(px,py,temp_x,temp_y); }
        }
        else if(ke.getKeyCode()==KeyEvent.VK_B)
        {
            int temp_y=updateY('d',px,py);
            int temp_x=updateX('l',px,py);
            if((px==815&&py==428)||(px==415&&py==28)||check(temp_x,temp_y)==false) { Invalid move=new Invalid(); }
            else
            {  assignXandY(px,py,temp_x,temp_y);}
        }
    }
    void gameEnded(String message)
    {
        time.stop();
        stop=0;
        GameWinner win_message=new GameWinner(message);
    }
    int updateY(char key,int x,int y)
    {
        int sum;
        if(key=='d')
        {
            return y+400;
        }
        return y-400;
    }
    int updateX(char key,int x,int y)
    {
        int sum;
        if(key=='r')
        {
            return x+400;
        }
        return x-400;
    }
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
    void assignXandY(int px,int py,int temp_x,int temp_y)
    {
        if(temp_x!=-1&&temp_y!=-1)
        {
            if(px==x1&&py==y1){ x1=temp_x; y1=temp_y; }
            else if(px==x2&&py==y2) {x2=temp_x; y2=temp_y;}
            else if(px==x3&&py==y3) {x3=temp_x; y3=temp_y;}
            else if(px==a1&&py==b1) {a1=temp_x; b1=temp_y;}
            else if(px==a2&&py==b2) {a2=temp_x; b2=temp_y;}
            else if(px==a3&&py==b3) {a3=temp_x; b3=temp_y;}
        }
        else if(temp_x==-1)
        {
            if(px==x1&&py==y1){ y1=temp_y;}
            else if(px==x2&&py==y2) {y2=temp_y; }
            else if(px==x3&&py==y3) {y3=temp_y; }
            else if(px==a1&&py==b1) {b1=temp_y; }
            else if(px==a2&&py==b2) {b2=temp_y; }
            else if(px==a3&&py==b3) {b3=temp_y; }
        }
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
 
    
   
    
