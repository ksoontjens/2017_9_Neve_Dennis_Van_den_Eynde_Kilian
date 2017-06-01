package hellotvxlet;

import java.awt.MediaTracker;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import javax.tv.xlet.*;
import org.dvb.event.EventManager;
import org.dvb.event.UserEventRepository;
import org.havi.ui.HContainer;
import org.havi.ui.HScene;
import org.havi.ui.HSceneFactory;
import org.havi.ui.HStaticText;
import org.havi.ui.event.HActionListener;

public class HelloTVXlet implements Xlet, HActionListener {

    HScene scene;
    Playfield bord;
    Random r = new Random();
    Enemy enemy1;
    private int minWidth = 32;
    private int minHeight = 32;
    
    private int asteroidsAmount = 20;
    ArrayList asteroids = new ArrayList();
    ArrayList asteroidPoints = new ArrayList();
    ArrayList asteroidSizes = new ArrayList();
    ArrayList asteroidRichtingen = new ArrayList();
    
    

    public HelloTVXlet() {
    }

    public void initXlet(XletContext context) 
    { //720 x 576

        scene = HSceneFactory.getInstance().getDefaultHScene();
        bord = new Playfield();
        UserEventRepository repo = new UserEventRepository("repo");
        repo.addAllArrowKeys();
        EventManager man = EventManager.getInstance();
        man.addUserEventListener(bord, repo);

        scene.add(bord);
        
        for (int i = 0; i < asteroidsAmount; i++) 
        {
            asteroidPoints.add(new DoublePoint(r.nextInt(720), r.nextInt(576)));
            asteroidSizes.add(new DoublePoint(minWidth + r.nextInt(32),minHeight + r.nextInt(32)));
            asteroidRichtingen.add(new DoublePoint(r.nextInt(360),r.nextInt(3)+1));
        }

        for (int i = 0; i < asteroidsAmount; i++) 
        {
            DoublePoint spawnPoint = (DoublePoint)asteroidPoints.get(i);
            DoublePoint size = (DoublePoint)asteroidSizes.get(i);
            DoublePoint richting = (DoublePoint)asteroidRichtingen.get(i);
            
            asteroids.add(new Enemy(r,(int)spawnPoint.x, (int)spawnPoint.y,(int)size.x,(int)size.y,richting));
            scene.add((Enemy) asteroids.get(i));
            scene.popToFront((Enemy) asteroids.get(i));
        }

        scene.validate();
        scene.setVisible(true);

        scene.repaint();
    }

    public void callback() {
        bord.run();
        
        //move each enemy to the player
        for(int i = 0; i < asteroids.size(); i++)
        {
            Enemy asteroid = (Enemy)asteroids.get(i);
            asteroid.Update();
        }
    }

    public void startXlet() {
        MijnTimerTask mtt = new MijnTimerTask();
        mtt.setCB(this);
        Timer t = new Timer();
        t.scheduleAtFixedRate(mtt, 0, 75);
    }

    public void pauseXlet() {
    }

    public void destroyXlet(boolean unconditional) {
    }

    public void actionPerformed(ActionEvent arg0) {
    }
}
