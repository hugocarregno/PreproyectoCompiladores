@echo off
call jflex lcalc.flex
call java -jar java-cup-11.jar ycalc.cup
call javac Main.java
call java Main test.txt
pause