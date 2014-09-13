@ECHO OFF
REM Compile client.java
cd src
javac client\MazeClient.java
Start /B java client.MazeClient
cd ..