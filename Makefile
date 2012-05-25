all: AsteroidsGUI
run: Run
clean: Clean

AsteroidsGUI: AsteroidsGUI.java
	javac AsteroidsGUI.java
Run: AsteroidsGUI
	java AsteroidsGUI
Clean:
	@rm *.class
