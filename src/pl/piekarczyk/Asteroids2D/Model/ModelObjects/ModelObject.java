package pl.piekarczyk.Asteroids2D.Model.ModelObjects;

import pl.piekarczyk.Asteroids2D.Model.Common.Types;

public abstract class ModelObject {
  protected boolean removable;
  protected Types.ObjectTypes type;
  //@OPT
  //private int id;
  public abstract void step();
  public abstract ModelObject copy();
  public ModelObject() {
    removable = false;
  }
  public ModelObject(ModelObject a) {
    this.removable = a.isRemovable();
    this.type = a.getType();
  }
  public boolean isRemovable() { return removable; }
  public void setRemovable(boolean nr) { removable = nr; }
  public int getX() { return 0; }
  public int getY() { return 0; }
  public int getHeight() { return 0; }
  public int getWidth() { return 0; }
  public int getRot() { return 0; }
  public Types.ObjectTypes getType() { return type; }
  //@OPT
  //public abstract void addObservator();
}
