find -name "*.java" > sources.txt
javac -verbose -cp "libs/*:." @sources.txt
java -cp ".:libs/*:./CheesySudoku/src/test/*:./CheesySudoku/src/" org.junit.platform.console.ConsoleLauncher --details=verbose --scan-classpath --fail-if-no-tests --reports-dir=output 

