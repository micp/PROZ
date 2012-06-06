package pl.piekarczyk.Asteroids2D.Model;

//: RecordList.java
// Implements record list
import java.util.prefs.*;
import java.util.*;
import java.io.*;

//@TODO inherit from LinkedList later?
public class RecordList implements Serializable {
  public void addRecord(int score, String name) {
    if(ls.isEmpty())
      ls.addFirst(new Record(score, name));
    else if(ls.getLast().getScore() < score)
      ls.addLast(new Record(score, name));
    else {
      for(ListIterator<Record> i = ls.listIterator(); i.hasNext();) {
	Record cur = i.next();
	if(cur.getScore() > score) {
	  ls.add(i.previousIndex(), new Record(score, name));
	  break;
	}
      }
    }
    if(ls.size() == 11) ls.removeFirst();
    return;
  }
  public String toString() {
    String s = new String();
    int j = 0;
    for(ListIterator<Record> i = ls.listIterator(); i.hasNext();) {
      Record cur = i.next();
      j++;
      s += j + ": " + cur.getScore() + " :: " + cur.getName() + "\n";
    }
    return s;
  }
  public void write(String filename) throws Exception {
    ObjectOutputStream out = new ObjectOutputStream(
	new FileOutputStream(filename));
    out.writeObject(this);
    out.close();
  }
  //@TODO fix up exceptions
  public static RecordList read(String filename) throws Exception {
    ObjectInputStream in = new ObjectInputStream(
	new FileInputStream(filename));
    RecordList result = (RecordList)in.readObject();
    in.close();
    return result;
  }
  public int getScore(int i) {
    return ls.get(i).getScore();
  }
  public String getName(int i) {
    return ls.get(i).getName();
  }

  private LinkedList<Record> ls = new LinkedList<Record>();
  private class Record implements Serializable {
    private int score;
    private String name;
    private Record(int nScore, String nName) {
      score = nScore;
      name = nName;
    }
    public int getScore() {
      return score;
    }
    public String getName() {
      return name;
    }
  }

  public static void main (String[] args) throws Exception {
    RecordList rl = new RecordList();
    rl.addRecord(3, "asd");
    rl.addRecord(1, "asd");
    rl.addRecord(2, "asd");
    rl.addRecord(4, "asd");
    rl.addRecord(0, "asd");
    rl.addRecord(0, "asd");
    rl.addRecord(0, "asd");
    rl.addRecord(0, "asd");
    rl.addRecord(0, "asd");
    rl.addRecord(0, "asd");
    rl.addRecord(0, "asd");
    rl.addRecord(10, "asd");
    rl.addRecord(11, "asd");
    rl.addRecord(16, "asd");
    rl.addRecord(11, "asd");
    rl.addRecord(13, "asd");
    System.out.println(rl);
    rl.write("records");
    RecordList rl2 = RecordList.read("records");
    System.out.println(rl2);
  }
} ///:~
