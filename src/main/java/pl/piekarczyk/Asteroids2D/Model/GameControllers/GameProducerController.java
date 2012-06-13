package pl.piekarczyk.Asteroids2D.Model.GameControllers;

/**
 * An interface for producer controllers. This type of controllers is meant to
 * provide logic for producing model objects. The objects are usually passed
 * references to these controllers and use methods provided when necessary.
 */
public interface GameProducerController extends GameController {
  /**
   * Notifies the controller that an object has been destroyed.
   */
  void notifyDestroyed();
  /**
   * Notifies the controller that an objects has been created. Necessary in 
   * case that an object itself spawns new objects.
   */
  void notifyCreated();
}
