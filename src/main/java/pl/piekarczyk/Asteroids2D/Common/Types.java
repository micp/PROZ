package pl.piekarczyk.Asteroids2D.Common;

/** 
 * Defines types of various objects used in the game.
 */
public class Types {
  /**
   * Defines types of keys supported by the game.
   */
  public static enum Keys { 
    /**
     * Represents keyboard UP arrow.
     */
    UP, 
    /**
     * Represents keyboard LEFT arrow.
     */
    LEFT, 
    /**
     * Represents keyboard RIGHT arrow.
     */
    RIGHT, 
    /**
     * Represents space key.
     */
    SPACE, 
    /**
     * Represents Q key.
     */
    Q, 
    /**
     * Used to calculate enum size, c++ style.
     */
    _SIZE}
  /**
   * Defines types of objects supported by the game.
   */
  public static enum ObjectTypes { 
    /**
     * Represents player ship.
     */
    SHIP, 
    /**
     * Represents asteroid.
     */
    ASTEROID, 
    /**
     * Represents 'tiny' asteroid. Supposed to be created after regular 
     * asteroid is destroyed by the player.
     */
    TINYASTEROID, 
    /**
     * Represents enemy ship.
     */
    ENEMY, 
    /**
     * Represents missile. Produced by either the player or enemy ship.
     */
    MISSILE }
}
