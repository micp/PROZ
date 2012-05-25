import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class GameWindow {
  private static GameWindow gameWindow;
  private GameWindow() {};
  //@TODO temporary?
  private final JFrame gameFrame = new JFrame("Asteroids 2d");
  public static GameWindow getGameWindow() {
    if(gameWindow == null)
       gameWindow = new GameWindow();
    return gameWindow;
  }
  public void show() {
    //final JFrame gameFrame = new JFrame("Asteroids 2d");
    //@TODO handle window close
    gameFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    gameFrame.setSize(500, 500);
    //@TODO temporary
    gameFrame.setResizable(false);

    /*temp*/final JPanel panel = new JPanel(new BorderLayout());
    JMenuBar bar = new JMenuBar();
    JMenu gameMenu = new JMenu("Game");
    JMenuItem[] gameMenuItems = {
      new JMenuItem("Restart"),
      new JMenuItem("Quit"),
    };
    JMenu helpMenu = new JMenu("Help");
    JMenuItem[] helpMenuItems = {
      new JMenuItem("Controls"),
      new JMenuItem("About"),
    };

    for(int i = 0; i < gameMenuItems.length; i++)
      gameMenu.add(gameMenuItems[i]);
    for(int i = 0; i < helpMenuItems.length; i++)
      helpMenu.add(helpMenuItems[i]);
    bar.add(gameMenu); 
    bar.add(helpMenu);

    //@TODO jpanel zamiast canv
    //draw in paint component
    //Canvas background = new Canvas();
    //background.setBackground(Color.black);
    //panel.add(background, BorderLayout.CENTER);

    //---
    final Ship ship = new Ship();
    ship.x = ship.y = 100;
    SwingWorker w = new SwingWorker<Void, Void>() {
      public Void doInBackground() {
	//System.out.format("[%d]%n", ship.x);
	//talk("Thread starting, wait 1");
	//try{Thread.sleep(1000);}catch(Exception ign){}
	//ship.x = ship.y = 400;
	//talk("Ship moved to 400, wait 1");
	//try{Thread.sleep(1000);}catch(Exception ign){}
	ship.repaint();
	ship.paint();
	//talk("Ship repainted, wait 1");
	//try{Thread.sleep(1000);}catch(Exception ign){}
	//panel.repaint();
	//talk("Panel repainted, wait 1");
	//try{Thread.sleep(1000);}catch(Exception ign){}
	//talk("All done");
	return null;
      }
      private void talk(String s) {
	String name = Thread.currentThread().getName();
	System.out.format("%s: %s%n", name, s);
      }
    };
    w.execute();
    //---

    panel.add(ship, BorderLayout.CENTER);
    gameMenuItems[1].addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent e) {
	gameFrame.dispose();
	AsteroidsGUI.getAsteroidsGUI().setVisible(true);
      }
    });

    panel.add(bar, BorderLayout.NORTH);
    gameFrame.getContentPane().add(panel);
    gameFrame.setVisible(true);
    //startGame();
  }
}
