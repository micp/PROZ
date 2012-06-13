package pl.piekarczyk.Asteroids2D.Model;

import java.util.prefs.*;
import java.util.*;
import java.io.*;

/**
 * A helper class to manage the record list. Provides utilities for saving
 * and loading the list from a file.
 */
public class RecordList implements Serializable {
  /**
   * Produces a list of ten 0 scores.
   */
  public RecordList() {
    for(int i = 0; i < 10; ++i)
      addRecord(0);
  }
  /**
   * Attempts to insert new score into the list. Checks if the score specified
   * qualifies to be put on the list. Takes no action if it isn't.
   * @param score The score to be put on the list.
   */
  public void addRecord(int score) {
    if(ls.isEmpty())
      ls.addFirst(new Record(score));
    else {
      ListIterator<Record> i = ls.listIterator();
      while(i.hasNext()) {
	Record cur = i.next();
	if(cur.getScore() >= score) {
	  ls.add(i.previousIndex(), new Record(score));
	  break;
	}
      }
      if(!i.hasNext())
	ls.add(i.nextIndex(), new Record(score));
    }
    if(ls.size() == 11) ls.removeFirst();
  }
  /**
   * Attempts to write the list on the disk. Throws an exception in case of 
   * failure.
   * @param filename The name of the file to be modified or created.
   * @throws IOEXception In case of write failure.
   */
  public void write(String filename) throws IOException {
    ObjectOutputStream out = new ObjectOutputStream(
	new FileOutputStream(filename));
    out.writeObject(this);
    out.close();
  }
  /**
   * Attempts to read the list from the disk. Throws an exception in case of 
   * failure
   * @param filename The name of the file to be read.
   * @throws IOEXception In case of read failure, usually if the file doesn't
   * exist.
   * @throws 
   */
  public static RecordList read(String filename) 
  throws IOException, ClassNotFoundException {
    ObjectInputStream in = new ObjectInputStream(
	new FileInputStream(filename));
    RecordList result = (RecordList)in.readObject();
    in.close();
    return result;
  }
  public int getScore(int i) {
    return ls.get(i).getScore();
  }

  private class Record implements Serializable {
    private int score;
    private Record(int nScore) {
      score = nScore;
    }
    public int getScore() {
      return score;
    }
  }

  private LinkedList<Record> ls = new LinkedList<Record>();
}
