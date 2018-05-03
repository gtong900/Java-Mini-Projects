#!/usr/bin/env bash

# TODO - ensure `target/classes` exists (creating it if it doesn't)
if [ ! -d "$target/classes" ]; then
	if [ ! -d "$target" ]; then
		mkdir -p target/classes
	else
		mkdir target/classes
	fi
fi
# TODO - compile all the Java files within the project and output them into `target/classes`
javac -sourcepath src -d target/classes src/main/java/edu/nyu/cs9053/homework2/model/*.java
javac -sourcepath src -d target/classes -classpath target/classes src/main/java/edu/nyu/cs9053/homework2/*.java
