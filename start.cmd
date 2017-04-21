@echo off

set HOME_JAVA="C:\Program Files\Java\jre1.8.0_121\bin"

%HOME_JAVA%\java -DurlStroytehRu="https://www.stroyteh.ru/sale/xml/" -DurlGruzovikRu="https://www.gruzovik.ru/offers/trucks/refrigerator/" -Dtarget_dir="C:\123" -jar "C:\123\parser-1.0-jar-with-dependencies.jar"
