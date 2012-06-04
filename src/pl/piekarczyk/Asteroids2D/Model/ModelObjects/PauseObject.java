package pl.piekarczyk.Asteroids2D.Model.ModelObjects;

import pl.piekarczyk.Asteroids2D.Model.Common.Types;

public class PauseObject extends ModelObject {
  //@OPT
  public PauseObject() {
    super();
    type = Types.ObjectTypes.PAUSE;
  }
  public PauseObject(PauseObject a) {
    super(a);
  }
  public void step() {
    //if(keys[Keys.p]) gamePaused = !gamePaused;
  }
  public PauseObject copy() {
    return new PauseObject(this);
  }
}
