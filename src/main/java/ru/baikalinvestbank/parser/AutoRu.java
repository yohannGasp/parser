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
public class AutoRu {

    public final static String request = "https://auto.ru/cars/honda/all/";

    public String parse(String url) {
        String title = null;

        Document doc;
        try {

            doc = Jsoup.connect(request).userAgent("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:52.0) Gecko/20100101 Firefox/52.0").get();
            title = doc.title();
            Elements table = doc.select(".listing__cell_type_summary");

            for (Element element : table) {
                System.out.println(element.getElementsByTag("a").text());
                System.out.println(element.getElementsByClass("listing-item__description").text());
                System.out.println("=======================");
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Jsoup Can read HTML page from URL, title : " + title);
        return "";
    }

}
