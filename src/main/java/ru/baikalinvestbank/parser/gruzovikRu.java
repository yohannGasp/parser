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

    public final static String request1 = "https://www.gruzovik.ru/offers/trucks/refrigerator/";

    public static String parse(String url) {

        String title = null;

        Document doc;
        try {

            doc = Jsoup.connect(request1).userAgent("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:52.0) Gecko/20100101 Firefox/52.0").get();
            title = doc.title();
            Elements table = doc.select("div.text");

            for (Element element : table) {
                System.out.println(element.text());
//                System.out.println(element.getElementsByClass("listing-item__description").text());
                System.out.println("=======================");
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Jsoup Can read HTML page from URL, title : " + title);
        return "";
    }

}
