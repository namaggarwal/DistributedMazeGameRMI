@ECHO OFF
REM Compile server.java
cd src
javac server\MazeServer.java
start rmiregistry
START /B java server.MazeServer 10 3
cd ..