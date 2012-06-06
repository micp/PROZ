package pl.piekarczyk.Asteroids2D;

import pl.piekarczyk.Asteroids2D.GUI.AsteroidsGUI;

public class Asteroids2D {
  public static void main(String[] args) {
    AsteroidsGUI.setThread(Thread.currentThread());
    AsteroidsGUI.getAsteroidsGUI();
  }
}
