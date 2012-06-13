all: mvn_run
clean: Clean
doc: javadoc

mvn_run: mvn_compile
	@mvn exec:java -Dexec.mainClass="pl.piekarczyk.Asteroids2D.Asteroids2D"
mvn_compile:
	@mvn compile
javadoc:
	(cd src/main/java && javadoc -subpackages pl.piekarczyk.Asteroids2D -d ../../../target/doc)

.PHONY : Clean
Clean:
	-rm -r target records
