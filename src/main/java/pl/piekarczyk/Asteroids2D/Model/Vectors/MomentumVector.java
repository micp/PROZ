package pl.piekarczyk.Asteroids2D.Model.Vectors;

public class MomentumVector extends StaticGameVector {
  public MomentumVector(int newMagnitude, int newDirection) {
    super(newMagnitude, newDirection);
  }
  public MomentumVector(MomentumVector a) {
    super(a);
  }
}
