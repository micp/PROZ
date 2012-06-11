package pl.piekarczyk.Asteroids2D.Model.Vectors;

import pl.piekarczyk.Asteroids2D.Common.Types;
import java.lang.Math;

public class PSAccelVector extends DynamicAsteroidsVector {
  public PSAccelVector(double newSpeedBase, double newDirection) {
    super(newSpeedBase, newDirection);
  }
  public PSAccelVector(DynamicGameVector a) {
    super(a);
  }
  //public PSAccelVector(PSAccelVector a) {
  //  super(a);
  //}
  public void accelerate() { 
    //speedBase+= 0.5;
    //if(speedBase > 10) speedBase = 10;
    //magnitude = Math.log1p( speedBase );
    magnitude = Math.log1p( 30 );
  }
  public void decelerate() {
    //speedBase-= 1.0;
    //if(speedBase < 0) speedBase = 0;
    //magnitude = Math.log1p( speedBase );
    magnitude = Math.log1p( 0 );
  }
  public void rotate(StaticGameVector v, Types.Keys key) {
    if(key == Types.Keys.LEFT) {
      this.setDirection(v.getDirection() + 45);
    }
    if(key == Types.Keys.RIGHT) {
      this.setDirection(v.getDirection() - 45);
    }
  }
  public void rotate(StaticGameVector v) {
    this.setDirection(v.getDirection());
  }
}
