import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class Top10 {
  private static Top10 top10;
  private JButton
    bReset = new JButton("Reset scores"),
    bQuit = new JButton("Close");
  private Top10() {};
  public static Top10 getTop10() {
    //@TODO better singleton / something else?
    if(top10 == null)
      top10 = new Top10();
    return top10;
  }
  public void show() {
    final JFrame top10Frame = new JFrame("Top 10");

    //@TODO handle window close
    top10Frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    top10Frame.setSize(250, 250);
    //@TODO temporary
    top10Frame.setResizable(false);

    JPanel panel = new JPanel();
    GridBagLayout gridbag = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();

    //@TODO handle file read errors
    try {
      RecordList rl = RecordList.read("records");
      for(int i = 9; i >= 0; --i) {
	Label lName = new Label(rl.getName(i));
	Label lScore = new Label(Integer.toString(rl.getScore(i)));
	c.gridx = 0;
	c.gridy = 9 - i;
	gridbag.setConstraints(lName, c);
	c.gridx = 1;
	gridbag.setConstraints(lScore, c);
	panel.add(lName);
	panel.add(lScore);
      }
    }
    catch (Exception ignore) {}

    c.gridx = 0;
    c.gridy = 10;
    gridbag.setConstraints(bReset, c);
    panel.add(bReset);
    bReset.addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent e) {
	//@TODO implement rl.clear();
      }
    });

    c.gridx = 1;
    gridbag.setConstraints(bQuit, c);
    panel.add(bQuit);
    bQuit.addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent e) {
	top10Frame.dispose();
	AsteroidsGUI.getAsteroidsGUI().setEnabled(true);
      }
    });

    panel.setLayout(gridbag);
    top10Frame.getContentPane().add(panel);
    top10Frame.setVisible(true);
  }
}
