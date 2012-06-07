import org.junit.*;
import pl.piekarczyk.Asteroids2D.Model.*;
import pl.piekarczyk.Asteroids2D.Model.Input.Keys.*;
import pl.piekarczyk.Asteroids2D.Model.Common.Types;

public class KeyTest {
  private AsteroidsModel testgame;

  @Before public void setUp() {
    testgame = AsteroidsModel.getAsteroidsModel();
  }
  @Test public void LeftAction() {
    KeyLeft key = new KeyLeft();
    key.reaction(testgame);
    Assert.assertTrue(testgame.getKey(Types.Keys.LEFT));
  }
  @Test public void RightAction() {
    KeyRight key = new KeyRight();
    key.reaction(testgame);
    Assert.assertTrue(testgame.getKey(Types.Keys.RIGHT));
  }
  @Test public void UpAction() {
    KeyUp key = new KeyUp();
    key.reaction(testgame);
    Assert.assertTrue(testgame.getKey(Types.Keys.UP));
  }
  @Test public void PAction() {
    KeyP key = new KeyP();
    key.reaction(testgame);
    Assert.assertTrue(testgame.getKey(Types.Keys.P));
  }
  @Test public void QAction() {
    KeyQ key = new KeyQ();
    key.reaction(testgame);
    Assert.assertTrue(testgame.getKey(Types.Keys.Q));
  }
  @Test public void SpaceAction() {
    KeySpace key = new KeySpace();
    key.reaction(testgame);
    Assert.assertTrue(testgame.getKey(Types.Keys.SPACE));
  }
}
