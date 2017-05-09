#!/bin/bash

HOME_JAVA="/usr/lib/jvm/java-8-oracle/jre/bin"

$HOME_JAVA/java -DurlStroytehRu="https://www.stroyteh.ru/sale/xml/" -DurlGruzovikRu="https://www.gruzovik.ru/" -DurlAutoRu="https://auto.ru/cars/" -Dtarget_dir=/tmp -Dbrowser="/usr/bin/firefox" -Dpause=7000 -Dhttps.proxyHost=187.44.14.19 -Dhttps.proxyPort=8080 -Dlog_path=/tmp/log.log -jar target/parser-1.0-jar-with-dependencies.jar

