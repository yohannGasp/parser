#!/bin/bash

HOME_JAVA="/usr/lib/jvm/java-8-oracle/jre/bin"

$HOME_JAVA/java -DurlStroytehRu="https://www.stroyteh.ru/sale/xml/" -DurlGruzovikRu="https://www.gruzovik.ru/offers/trucks/refrigerator/" -Dtarget_dir=/tmp -jar target/parser-1.0-jar-with-dependencies.jar
