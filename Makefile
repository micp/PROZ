OPTS=
MAINPATH=src/pl/piekarczyk/Asteroids2D

GUIP= $(MAINPATH)/GUI
GUIJ= AsteroidsGUI.java GameWindow.java Top10.java
MODELP= $(MAINPATH)/Model
MODELJ= 
#Include
CP= .:src:src/GUI
OPTS+=-cp ${CP}

#Move .class files to ./
CDIR= .
OPTS+= -d ${CDIR}

all: AsteroidsGUI
run: Run
clean: Clean

AsteroidsGUI: src/GUI/AsteroidsGUI.java
	javac src/GUI/AsteroidsGUI.java ${OPTS}
Run: AsteroidsGUI
	java AsteroidsGUI
Clean:
	@rm *.class
