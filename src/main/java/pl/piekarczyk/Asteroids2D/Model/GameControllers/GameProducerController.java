package pl.piekarczyk.Asteroids2D.Model.GameControllers;

public interface GameProducerController extends GameController {
  void notifyDestroyed();
  void notifyCreated();
}
