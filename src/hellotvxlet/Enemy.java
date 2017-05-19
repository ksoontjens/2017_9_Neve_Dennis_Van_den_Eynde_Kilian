/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet;
import java.awt.*;
import org.havi.ui.HComponent;
/**
 *
 * @author student
 */
public class Enemy extends HComponent
{
    private Image image;
    private MediaTracker mtrack;
    
    public Enemy(String file,int x, int y)
    {
        image = this.getToolkit().getImage(file);
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
        this.setBounds(x,y,image.getWidth(null),image.getWidth(null));
    }
    
    public void paint(Graphics g)
    {
        g.drawImage(image,0,0,null);
    }
}
