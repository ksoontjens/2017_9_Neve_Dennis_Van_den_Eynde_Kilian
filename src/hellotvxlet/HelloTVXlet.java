package hellotvxlet;

import java.awt.Color;
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
import org.havi.ui.HState;
import org.havi.ui.HStaticText;
import org.havi.ui.HVisible;
import org.havi.ui.event.HActionListener;

public class HelloTVXlet implements Xlet, HActionListener {

    HScene scene;
    Playfield bord;
    Random r = new Random();
    Enemy enemy1;
    private int minWidth = 32;
    private int minHeight = 32;
    
    private int asteroidsAmount = 10;
    private int score = 0;
    ArrayList asteroids = new ArrayList();
    ArrayList asteroidPoints = new ArrayList();
    ArrayList asteroidSizes = new ArrayList();
    ArrayList asteroidRichtingen = new ArrayList();
    HStaticText lblScore;
    
    private boolean gameOver = false;
    

    public HelloTVXlet() {
    }

    public void initXlet(XletContext context) 
    { //720 x 576

        scene = HSceneFactory.getInstance().getDefaultHScene();
        Laser laser = new Laser();
        bord = new Playfield(laser);
        UserEventRepository repo = new UserEventRepository("repo");
        repo.addAllArrowKeys();
        EventManager man = EventManager.getInstance();
        man.addUserEventListener(bord, repo);
        
        Background bg = new Background();
        lblScore = new HStaticText("Score: " + score,10,10,200,25);
        lblScore.setForeground(Color.WHITE);
        
        scene.add(bord);
        scene.add(bg);
        scene.add(lblScore);
        scene.popToFront(lblScore);
        scene.add(laser);
        scene.popToFront(laser);
        
        for (int i = 0; i < asteroidsAmount; i++) 
        {
            asteroidPoints.add(new DoublePoint(r.nextInt(720), r.nextInt(576)));
            asteroidSizes.add(new DoublePoint(minWidth + r.nextInt(32),minHeight + r.nextInt(32)));
            asteroidRichtingen.add(new DoublePoint(r.nextInt(360),r.nextInt(1)+1));
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
        //bord.asteroids = asteroid
   
        scene.validate();
        scene.setVisible(true);

        scene.repaint();
    }

    public void callback() {
        bord.run();
        score++;
        lblScore.setTextContent("Score: " + score, HState.NORMAL_STATE);
       
        if(!gameOver)
        {
            //move each enemy to the player
            for(int i = 0; i < asteroids.size(); i++)
            {
                Enemy asteroid = (Enemy)asteroids.get(i);
                asteroid.Update();
            }
        }
        
        scene.repaint();
    }

    public void startXlet() {
        MijnTimerTask mtt = new MijnTimerTask();
        mtt.setCB(this);
        Timer t = new Timer();
        t.scheduleAtFixedRate(mtt, 0, 10);
    }

    public void pauseXlet() {
    }

    public void destroyXlet(boolean unconditional) {
    }

    public void actionPerformed(ActionEvent arg0) {
    }
}
