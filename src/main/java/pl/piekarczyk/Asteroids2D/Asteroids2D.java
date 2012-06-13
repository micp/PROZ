package pl.piekarczyk.Asteroids2D;

import pl.piekarczyk.Asteroids2D.GUI.AsteroidsGUI;

/**
 * Provides a static main for the game.
 * <p>
 * Just starts the game GUI. Doesn't do anything else.
 */
public class Asteroids2D {
  /**
   * Starts the game GUI.
   *
   * @param args Ignored.
   */
  public static void main(String[] args) {
    AsteroidsGUI.getAsteroidsGUI().show();
  }
}
