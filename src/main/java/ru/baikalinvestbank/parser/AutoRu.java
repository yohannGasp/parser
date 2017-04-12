/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.baikalinvestbank.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 *
 * @author evgeniy
 */
public class AutoRu {

//    public final static String request = "https://auto.ru/cars/honda/all/";

    public List parse(String url) {
        
        List result = new ArrayList();

        Document doc;
        try {

            doc = Jsoup.connect(url).get();
            Elements table = doc.select(".listing__cell_type_summary");

            for (Element element : table) {
                System.out.println(element.getElementsByTag("a").text());
                System.out.println(element.getElementsByClass("listing-item__description").text());
                System.out.println("=======================");
                result.add(element);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

}
