@ECHO OFF
REM Compile client.java
cd src
javac client\MazeClient.java
java client.MazeClient
cd ..