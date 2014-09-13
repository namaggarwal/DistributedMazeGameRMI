@ECHO OFF
REM Compile server.java
cd src
javac server\MazeServer.java
start rmiregistry
START /B java server.MazeServer
cd ..