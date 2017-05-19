package hellotvxlet;

import java.awt.event.ActionEvent;
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
    public HelloTVXlet() {
        
    }

    public void initXlet(XletContext context) { //720 x 576
      scene=HSceneFactory.getInstance().getDefaultHScene();
     bord=new Playfield();
         UserEventRepository repo=new UserEventRepository("repo");
         repo.addAllArrowKeys();
     EventManager man=EventManager.getInstance();
     man.addUserEventListener(bord, repo);
   
      scene.add(bord);
      scene.validate();
      scene.setVisible(true);

      scene.repaint();
    }

    public void callback()
    {
        bord.run();
    }
    public void startXlet() {
           MijnTimerTask mtt=new MijnTimerTask();
           mtt.setCB(this);
           Timer t=new Timer();
           t.scheduleAtFixedRate(mtt, 0, 500);
    }

    public void pauseXlet() {
     
    }

    public void destroyXlet(boolean unconditional) {
     
    }

    public void actionPerformed(ActionEvent arg0) {
       
        }
        
    }
