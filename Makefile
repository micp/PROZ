all: AsteroidsGUI
run: Run

AsteroidsGUI: AsteroidsGUI.java
	javac AsteroidsGUI.java
Run: AsteroidsGUI
	java AsteroidsGUI
