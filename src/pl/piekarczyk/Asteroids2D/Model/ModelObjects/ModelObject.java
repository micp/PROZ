package pl.piekarczyk.Asteroids2D.Model.ModelObjects;

public abstract class ModelObject {
  protected boolean removable;
  //@OPT
  //private int id;
  public abstract void step();
  public boolean isRemovable() { return removable; }
  public int getX() { return 0; }
  public int getY() { return 0; }
  public int getHeight() { return 0; }
  public int getWidth() { return 0; }
  public int getRot() { return 0; }
  //@OPT
  //public abstract void addObservator();
}
