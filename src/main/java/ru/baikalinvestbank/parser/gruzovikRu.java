/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.baikalinvestbank.parser;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author evgeniy
 */
public class gruzovikRu {

    public static String parse(String url) {

        StringBuilder builder = new StringBuilder();
        String result = "";

        Document doc;
        try {

            doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:52.0) Gecko/20100101 Firefox/52.0").get();
            Elements table = doc.select("div.text");

            for (Element element : table) {
                builder.append(element.text() + ";");
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        result = builder.toString();
        return result;
    }

}
