@echo off

set HOME_JAVA="C:\Program Files\Java\jre1.8.0_121\bin"

%HOME_JAVA%\java -DurlStroytehRu="https://www.stroyteh.ru/sale/xml/" -DurlGruzovikRu="https://www.gruzovik.ru/" -Dtarget_dir="C:\123" -Dbrowser="/usr/bin/firefox" -Dpause=7000 -Dhttps.proxyHost=192.168.5.1 -Dhttps.proxyPort=1080 -jar "C:\123\parser-1.0-jar-with-dependencies.jar"