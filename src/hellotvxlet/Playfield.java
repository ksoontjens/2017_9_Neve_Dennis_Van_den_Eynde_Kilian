/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.bluray.ui.event.HRcEvent;
import org.dvb.event.UserEvent;
import org.dvb.event.UserEventListener;
import org.dvb.event.UserEventRepository;
import org.havi.ui.HComponent;

/**
 *
 * @author student
 */
public class Playfield extends HComponent implements UserEventListener {

    ArrayList slang=new ArrayList();
    double hx=300;
    double hy=250;
    double hoek=0;
    double gr=25;
    ArrayList asteroids =new ArrayList();
    ArrayList asteroidpoints=new ArrayList();
    ArrayList asteroidhoek=new ArrayList();
    private Image image;
    Random r=new Random();
    private MediaTracker mtrack;
    
    
    public Playfield()
    {
        this.setBounds(0,0,720,576); // full scree 
        /*
        for (int i=0;i<10;i++)
        {
            
        
               
                image = this.getToolkit().getImage("Asteroid classic 1.png");
        mtrack = new MediaTracker(this);
        mtrack.addImage(image,0);
        
        try
        {
            mtrack.waitForAll();
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
        asteroids.add(image);
        
        asteroidpoints.add(new DoublePoint(r.nextInt(720),r.nextInt(576)));
        asteroidhoek.add(new DoublePoint(r.nextInt(360),r.nextInt(3)+1));
        }*/
        
    }
    
   
    public void paint(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.fillRect(0,0,720,576);
     
        g.setColor(Color.WHITE);
                double radhoek=(hoek/360.0)*2*Math.PI;
                double hoek2=hoek+200;
                double radhoek2=(hoek2/360.0)*2*Math.PI;
                double hoek3=hoek-200;
                double radhoek3=(hoek3/360.0)*2*Math.PI;
                
                int x[]=new int[3];
                int y[]=new int[3];
                 x[0]=(int)(hx+gr*Math.cos(radhoek));
                 x[1]=(int)(hx+gr*Math.cos(radhoek2));
                 x[2]=(int)(hx+gr*Math.cos(radhoek3));
                
                 y[0]=(int)(hx+gr*Math.sin(radhoek));
                 y[1]=(int)(hx+gr*Math.sin(radhoek2));
                 y[2]=(int)(hx+gr*Math.sin(radhoek3));
    
                
            g.drawPolygon(x, y, 3);
            
            /*
          for (int i=0;i<asteroids.size();i++)
          {
              DoublePoint p=(DoublePoint)asteroidpoints.get(i);
              g.drawImage((Image)asteroids.get(i), (int)p.x, (int)p.y, null);
          }*/
        
    }

    
    public void run()
    {
        /*
          for (int i=0;i<asteroids.size();i++)
          {
              DoublePoint rich=(DoublePoint)asteroidhoek.get(i); // x= hoek y=snelheid
              DoublePoint p=(DoublePoint)asteroidpoints.get(i);
               double radhoek=(rich.x/360.0)*2*Math.PI;
                       p.x+=(rich.y*Math.cos(radhoek));
                    p.y+=(rich.y*Math.sin(radhoek));
                      
          }*/
        this.repaint();
    }
    public void userEventReceived(UserEvent e) {
  if (e.getType()==HRcEvent.KEY_PRESSED)
  {
      if (e.getCode()==HRcEvent.VK_LEFT)
      {
          hoek--;
          this.repaint();
      }
      
      if (e.getCode()==HRcEvent.VK_RIGHT)
      {
          hoek++;
          this.repaint();
      }
  }
}
}

