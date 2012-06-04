//: AsteroidsGUI.java
// Implements user interface and displays 
// objects. Handles input.
package pl.piekarczyk.Asteroids2D.GUI;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

//@TODO interface for GUI classes?
//@TODO split program into packages
//@TODO windows should move to middle of the screen
//@TODO change fonts, sizes, backgrounds, icons, buttons
//@TODO show = decieving name (constructs, doesn't just show)
public class AsteroidsGUI {
  public static void main(String[] args) {
    //@TODO constructor should only prepare data, not run anything
    AsteroidsGUI.setThread(Thread.currentThread());
    AsteroidsGUI.getAsteroidsGUI();
  }
  static public void setThread(Thread t) {
    mainT = t;
  }
  static public Thread mainT;
  private static AsteroidsGUI asteroidsGUI;
  private final JFrame frame;
  private JButton
    bStart = new JButton("Start new game"),
    bTop10 = new JButton("View TOP10"),
    bQuit = new JButton("Quit game");
  private AsteroidsGUI() {
    //@TODO remove final?
    frame = new JFrame("Asteroids 2D");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(200, 250);
    frame.setResizable(false);

    JPanel panel = new JPanel();
    GridBagLayout gridbag = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();

    //@TODO temp/fill with picture:
    Canvas cnvtmp = new Canvas();

    c.fill = GridBagConstraints.BOTH;
    c.weighty = 1.0;
    c.gridy = 0;
    gridbag.setConstraints(cnvtmp, c);

    c.weighty = 0.0;
    c.weightx = 1.0;
    c.gridy = 1;
    gridbag.setConstraints(bStart, c);
    c.gridy = 2;
    gridbag.setConstraints(bTop10, c);
    c.gridy = 3;
    gridbag.setConstraints(bQuit, c);

    panel.setLayout(gridbag);
    panel.add(cnvtmp);
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
	//@TODO indicate disability (new fun)
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
    frame.setVisible(true);
  }
  public static AsteroidsGUI getAsteroidsGUI() {
    if(asteroidsGUI == null)
      asteroidsGUI = new AsteroidsGUI();
    //if(mainT.isAlive())
    //  System.out.println("Main still alive!");
    //else
    //  System.out.println("Main is dead!");
    //System.out.println(Thread.currentThread().getName());
    return asteroidsGUI;
  }
  public void setVisible(boolean newState) {
    frame.setVisible(newState);
  }
  public void setEnabled(boolean newEnabled) {
    frame.setEnabled(newEnabled);
  }
}
