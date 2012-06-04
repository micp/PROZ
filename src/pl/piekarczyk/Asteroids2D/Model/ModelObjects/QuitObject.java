package pl.piekarczyk.Asteroids2D.Model.ModelObjects;

import pl.piekarczyk.Asteroids2D.Model.Common.Types;

public class QuitObject extends ModelObject {
  public QuitObject() {
    super();
    type = Types.ObjectTypes.QUIT;
  }
  public QuitObject(QuitObject a) {
    super(a);
  }
  public void step() {
    //@OPT
    //if(keys[Keys.quit]) lives = 0;
  }
  public QuitObject copy() {
    return new QuitObject(this);
  }
}
