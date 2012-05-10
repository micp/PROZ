//: AsteroidsGUI.java
// Implements user interface and displays 
// objects. Handles input.
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class AsteroidsGUI {
  public static void main(String[] args) {
    new AsteroidsGUI();
  }
  private JButton
    bStart = new JButton("Start new game"),
    bTop10 = new JButton("View TOP10"),
    bQuit = new JButton("Quit game");
  //@TODO handle this more gracefully
  private JFrame frame;
  //@TODO reduce const() size
  private AsteroidsGUI() {
    frame = new JFrame("Asteroids 2D");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(200, 250);
    frame.setResizable(false);

    JPanel panel = new JPanel();
    GridBagLayout gridbag = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();

    //@TODO temp:
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
    panel.add(bStart);
    panel.add(bTop10);
    panel.add(bQuit);
    panel.add(cnvtmp);

    bStart.addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent e) {
	runGame();
      }
    });
    bTop10.addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent e) {
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
  private void runGame() {
    frame.setVisible(false);

    final JFrame gameFrame = new JFrame("Asteroids 2d");
    //@TODO handle window close
    gameFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    gameFrame.setSize(500, 500);
    //@TODO temporary
    gameFrame.setResizable(false);

    JPanel panel = new JPanel(new BorderLayout());
    JMenuBar bar = new JMenuBar();
    JMenu gameMenu = new JMenu("Game");
    JMenuItem[] gameMenuItems = {
      new JMenuItem("Restart"),
      new JMenuItem("Quit to main menu"),
      new JMenuItem("Quit game"),
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

    gameMenuItems[1].addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent e) {
	gameFrame.setVisible(false);
	gameFrame.dispose();
	frame.setVisible(true);
      }
    });
    gameMenuItems[2].addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent e) {
	gameFrame.dispose();
	frame.dispose();
      }
    });

    panel.add(bar, BorderLayout.NORTH);
    gameFrame.getContentPane().add(panel);
    gameFrame.setVisible(true);
  }
  private void showTop10() {
  }
}
