package pl.piekarczyk.Asteroids2D.GUI;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

/** The main menu window. Responsible for allowing access to other game 
 * functions.
 */
public class AsteroidsGUI {
  /** Returns reference to the main menu window. Constructs the window if
   * necessary. Replaces the public constructor.
   * @return Reference to the main menu window.
   */
  public static AsteroidsGUI getAsteroidsGUI() {
    if(asteroidsGUI == null)
      asteroidsGUI = new AsteroidsGUI();
    return asteroidsGUI;
  }
  /** Makes the window visible.
   */
  public void show() {
    frame.setVisible(true);
  }
  /** Sets visibility state of the window. Can make the window invisible.
   *
   * @param newState Requested state of the window.
   */
  public void setVisible(boolean newState) {
    frame.setVisible(newState);
  }
  /** Sets clickable state of the window. Provides a way to make the window
   * unclickable by the user.
   * @param newEnabled Requested state of the window.
   */
  public void setEnabled(boolean newEnabled) {
    frame.setEnabled(newEnabled);
  }

  private static AsteroidsGUI asteroidsGUI;
  private final JFrame frame;
  private JButton
    bStart = new JButton("Start new game"),
    bTop10 = new JButton("View TOP10"),
    bQuit = new JButton("Quit game");
  private AsteroidsGUI() {
    frame = new JFrame("Asteroids 2D");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(200, 75);
    frame.setResizable(false);

    JPanel panel = new JPanel();
    GridBagLayout gridbag = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();

    c.fill = GridBagConstraints.BOTH;

    c.weighty = 0.0;
    c.weightx = 1.0;
    c.gridy = 1;
    gridbag.setConstraints(bStart, c);
    c.gridy = 2;
    gridbag.setConstraints(bTop10, c);
    c.gridy = 3;
    gridbag.setConstraints(bQuit, c);

    panel.setLayout(gridbag);
    panel.add(bStart);
    panel.add(bTop10);
    panel.add(bQuit);

    bStart.addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent e) {
	frame.setVisible(false);
	GameWindow.getGameWindow().show();
      }
    });
    bTop10.addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent e) {
	frame.setEnabled(false);
	Top10.getTop10().show();
      }
    });
    bQuit.addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        frame.dispose();
      }
    });

    frame.getContentPane().add(panel);
  }
}
