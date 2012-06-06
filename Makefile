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
run: mvn_run
clean: Clean

AsteroidsGUI: src/GUI/AsteroidsGUI.java
	javac src/GUI/AsteroidsGUI.java ${OPTS}
Run: AsteroidsGUI
	java AsteroidsGUI
mvn_run: mvn_build
	@mvn exec:java -Dexec.mainClass="pl.piekarczyk.Asteroids2D.Asteroids2D"
mvn_build:
	@mvn compile
Clean:
	@rm *.class
