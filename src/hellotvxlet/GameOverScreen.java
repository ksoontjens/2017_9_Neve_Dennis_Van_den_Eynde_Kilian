/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import org.havi.ui.HComponent;
/**
 *
 * @author student
 */
public class GameOverScreen extends HComponent
{
    public boolean mayDraw = false;
    
    public GameOverScreen()
    {
        this.setBounds(0,0,720,576);
    }
    public void paint(Graphics g)
    {
        if(mayDraw)
        {
            g.setColor(Color.RED);
            g.fillRect(0,0,720,576);
            g.drawRect(0, 0, 720, 576);
        }
    }
}
