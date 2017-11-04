#!/bin/sh
cp ../base/build/libs/*.jar ../lib/import/
classPath=`find build/libs -name '*.jar'`
mainClass=com.asa.computer.ui.server.ServerUi
importJar=./
for jar in `find ../lib/import/ -name '*.jar'`;do
    importJar="$importJar:$jar"
done
classPath="$classPath:$importJar"
java -classpath $classPath $mainClass
