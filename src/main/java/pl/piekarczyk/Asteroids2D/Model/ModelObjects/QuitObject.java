package pl.piekarczyk.Asteroids2D.Model.ModelObjects;

import pl.piekarczyk.Asteroids2D.Model.*;
import pl.piekarczyk.Asteroids2D.Model.Common.Types;

public class QuitObject extends ModelObject {
  public QuitObject(AsteroidsModel thisGame) {
    super(thisGame);
    type = Types.ObjectTypes.QUIT;
  }
  public QuitObject(QuitObject a) {
    super(a);
  }
  public void step() {
    if(game.getKeyState(Types.Keys.Q)) game.stopGame();
  }
  public QuitObject copy() {
    return new QuitObject(this);
  }
}
