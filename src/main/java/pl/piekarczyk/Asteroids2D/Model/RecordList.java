package pl.piekarczyk.Asteroids2D.Model;

import java.util.prefs.*;
import java.util.*;
import java.io.*;

public class RecordList implements Serializable {
  public RecordList() {
    for(int i = 0; i < 10; ++i)
      addRecord(0);
  }
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
  public void write(String filename) throws IOException {
    ObjectOutputStream out = new ObjectOutputStream(
	new FileOutputStream(filename));
    out.writeObject(this);
    out.close();
  }
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
