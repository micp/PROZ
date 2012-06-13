package pl.piekarczyk.Asteroids2D.GUI;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import pl.piekarczyk.Asteroids2D.View.*;

/**
 * The game window containing main game field. Start new game and connects to 
 * the game view. Also provides a simple menu allowing the user to stop the 
 * game.
 */
public class GameWindow {
  /**
   * Returns reference to the game window. Constructs the window if necessary. 
   * If a window already exists, returns reference to that window. An existing
   * window is destroyed only if it is closed from the menu. Note: does not 
   * make the window visible, see {@link #show()} method.
   * 
   * @return A reference to the game window.
   */
  public static GameWindow getGameWindow() {
    if(curWindow == null) {
      curWindow = new GameWindow();
    }
    return curWindow;
  }
  /**
   * Makes the window visible. Also starts a new game (it would be pointless
   * to start it in an invisible window).
   */
  public void show() {
    newGame();
    gameFrame.setVisible(true);
  }
  private GameWindow() {
    gameFrame = new JFrame("Asteroids 2d");
    gameFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    gameFrame.setSize(500, 500);
    gameFrame.setResizable(false);

    panel = new JPanel(new BorderLayout());
    JMenuBar bar = new JMenuBar();
    JMenu gameMenu = new JMenu("Game");
    JMenuItem[] gameMenuItems = {
      new JMenuItem("Quit"),
    };

    for(int i = 0; i < gameMenuItems.length; i++)
      gameMenu.add(gameMenuItems[i]);
    bar.add(gameMenu); 

    gameMenuItems[0].addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent e) {
	delGame();
	gameFrame.dispose();
	curWindow = null;
	AsteroidsGUI.getAsteroidsGUI().setVisible(true);
      }
    });

    panel.add(bar, BorderLayout.NORTH);
    gameFrame.getContentPane().add(panel);
  }
  private void delGame() {
    v.requestClose();
    panel.remove(v);
    v = null;
  }
  private void newGame() {
    v = new AsteroidsView();
    v.runView();
    panel.add(v, BorderLayout.CENTER);
    gameFrame.repaint();
  }

  private AsteroidsView v;
  private static GameWindow curWindow;
  private JPanel panel;
  private JFrame gameFrame;
}
