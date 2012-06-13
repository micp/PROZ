package pl.piekarczyk.Asteroids2D.GUI;

import java.io.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import pl.piekarczyk.Asteroids2D.Model.RecordList;

/**
 * Displays top 10 scores achieved by the players.
 */
public class Top10 {
  /**
   * Returns reference to the Top10 window. Constructs the window if necessary. 
   * If a window already exists, returns reference to that window. An existing
   * window is destroyed only if it is closed by the button. Also loads the 
   * scores from a file named records, if exists. In other case initializes 
   * the list with 0 scores. Does not create a new records file. Note: does not 
   * make the window visible, see {@link #show()} method.
   * 
   * @return A reference to the game window.
   */
  public static Top10 getTop10() {
    if(top10 == null)
      top10 = new Top10();
    return top10;
  }
  /**
   * Makes the window visible.
   */
  public void show() {
    top10Frame.setVisible(true);
  }
  private Top10() {
    top10Frame = new JFrame("Top 10");

    top10Frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    top10Frame.setSize(250, 250);
    top10Frame.setResizable(false);

    JPanel panel = new JPanel();
    GridBagLayout gridbag = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();

    RecordList rl;
    try {
      rl = RecordList.read("records");
    } catch (Exception i) {
      rl = new RecordList();
    }
    for(int i = 9; i >= 0; --i) {
      Label lScore = new Label(Integer.toString(rl.getScore(i)));
      c.gridy = 9 - i;
      gridbag.setConstraints(lScore, c);
      panel.add(lScore);
    }

    c.gridy = 10;
    gridbag.setConstraints(bQuit, c);
    panel.add(bQuit);
    bQuit.addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent e) {
	close();
	AsteroidsGUI.getAsteroidsGUI().setEnabled(true);
      }
    });

    panel.setLayout(gridbag);
    top10Frame.getContentPane().add(panel);
  }
  private void close() {
    top10Frame.dispose();
    top10 = null;
  }
  private static Top10 top10;
  private JFrame top10Frame;
  private JButton
    bQuit = new JButton("Close");
}
