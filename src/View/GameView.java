public class GameView {
  public void startGame() {
    Thread t = new Thread(new Runnable() {
      public void run() {
	runGame();
      }
    });
    t.start();
  }
  //needs observators
  //  other file
  private interface Event {}
  private BlockingQueue<Event> eventQueue;
  private interface viewObject {}
  private LinkedList<viewObject> objectList;
  private JPanel gamePanel;
  public void setPanel(Jpanel newGamePanel) {
    gamePanel = newGamePanel;
  }
  private void addObject() {}
  private void updObject() {}
  private void delObject() {}
  private void addAll() {}
  private void updAll() {}
  private void delAll() {}
  private void notifyKeyPress() {}
  private void displayWelcome() {}
  private void displayGameOver() {}
  private void displayPause() {}
  private void displayRecord() {}
}
