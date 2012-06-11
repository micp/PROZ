package pl.piekarczyk.Asteroids2D.Test.Model.ModelObjects;

import java.lang.Math;
import org.junit.*;
import static org.junit.Assert.*;
import pl.piekarczyk.Asteroids2D.Model.ModelObjects.PhysicalObject;

public class PhysicalObjectTestHelper {
  public static void 
  constructorTest(PhysicalObject testee, int height, int width) {
    assertEquals("Wrong X", 0, testee.getX(), 0);
    assertEquals("Wrong Y", 0, testee.getY(), 0);
    assertEquals("Wrong rotation", 0, testee.getRot(), 0);
    assertFalse("Wrong removable", testee.isRemovable());
    assertEquals("Wrong height", height, testee.getHeight());
    assertEquals("Wrong width", width, testee.getWidth());
    assertEquals("Wrong vector magnitude", 0, testee.getMagnitude(), 0);
    assertEquals("Wrong vector direction", 0, testee.getDirection(), 0);
  }
  public static void copyConstructorPreTest(PhysicalObject testee) {
    testee.setXY(1, 1);
    //@OPT test trivial in other fun.
    testee.setRot(90);
    testee.setRemovable(true);
    testee.setHeight(1);
    testee.setWidth(1);
    testee.setMagnitude(1);
    testee.setDirection(1);
  }
  public static void copyConstructorTest(PhysicalObject testee) {
    assertEquals("Wrong X", 1, testee.getX(), 0);
    assertEquals("Wrong Y", 1, testee.getY(), 0);
    assertEquals("Wrong rotation", 90, testee.getRot(), 0);
    assertTrue("Wrong removable", testee.isRemovable());
    assertEquals("Wrong height", 1, testee.getHeight());
    assertEquals("Wrong width", 1, testee.getWidth());
    assertEquals("Wrong vector magnitude", 1, testee.getMagnitude(), 0);
    assertEquals("Wrong vector direction", 1, testee.getDirection(), 0);
  }
  public static void compareTest(PhysicalObject a, PhysicalObject b) {
    assertEquals("Different X", a.getX(), b.getX(), 0);
    assertEquals("Different Y", a.getY(), b.getY(), 0);
    assertEquals("Different rotation", a.getRot(), b.getRot(), 0);
    assertEquals("Different removable", a.isRemovable(), b.isRemovable());
    assertEquals("Different height", a.getHeight(), b.getHeight());
    assertEquals("Different width", a.getWidth(), b.getWidth());
    assertEquals("Different vector magnitude",
	a.getMagnitude(), b.getMagnitude(), 0);
    assertEquals("Different vector direction",
	a.getDirection(), b.getDirection(), 0);
  }
  public static void moveNorthTest(PhysicalObject testee) {
    testee.setMagnitude(1);
    testee.setDirection(0);
    testee.step();
    assertTrue(testee.getY() > 0 && Math.rint(testee.getX()) == 0);
    assertEquals("Wrong rotation", 0, testee.getRot(), 0);
  }
  public static void moveSouthTest(PhysicalObject testee) {
    testee.setMagnitude(1);
    testee.setDirection(180);
    testee.step();
    assertTrue(testee.getY() < 0 && Math.rint(testee.getX()) == 0);
    assertEquals("Wrong rotation", 180, testee.getRot(), 0);
  }
  public static void moveEastTest(PhysicalObject testee) {
    testee.setMagnitude(1);
    testee.setDirection(90);
    testee.step();
    assertTrue(Math.rint(testee.getY()) == 0 && testee.getX() > 0);
    assertEquals("Wrong rotation", 90, testee.getRot(), 0);
  }
  public static void moveWestTest(PhysicalObject testee) {
    testee.setMagnitude(1);
    testee.setDirection(270);
    testee.step();
    assertTrue(Math.rint(testee.getY()) == 0 && testee.getX() < 0);
    assertEquals("Wrong rotation", 270, testee.getRot(), 0);
  }
  public static void moveNorthEastTest(PhysicalObject testee) {
    testee.setMagnitude(1);
    testee.setDirection(45);
    testee.step();
    assertTrue(testee.getY() > 0 && testee.getX() > 0);
    assertEquals("Wrong rotation", 45, testee.getRot(), 0);
  }
  public static void moveSouthEastTest(PhysicalObject testee) {
    testee.setMagnitude(1);
    testee.setDirection(135);
    testee.step();
    assertTrue(testee.getY() < 0 && testee.getX() > 0);
    assertEquals("Wrong rotation", 135, testee.getRot(), 0);
  }
  public static void moveSouthWestTest(PhysicalObject testee) {
    testee.setMagnitude(1);
    testee.setDirection(-135);
    testee.step();
    assertTrue(testee.getY() < 0 && testee.getX() < 0);
    assertEquals("Wrong rotation", 360-135, testee.getRot(), 0);
  }
  public static void moveNorthWestTest(PhysicalObject testee) {
    testee.setMagnitude(1);
    testee.setDirection(-45);
    testee.step();
    assertTrue(testee.getY() > 0 && testee.getX() < 0);
    assertEquals("Wrong rotation", 315, testee.getRot(), 0);
  }
}
