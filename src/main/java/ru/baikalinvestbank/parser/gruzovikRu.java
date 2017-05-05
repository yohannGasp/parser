/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.baikalinvestbank.parser;

import java.net.Proxy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author evgeniy
 */
public class gruzovikRu implements site {

    @Override
    public List<Item> parse(String url, Proxy proxy) {

        List<Item> mas = new ArrayList<>();
        List<Item> result = new ArrayList<>();

        Document doc;
        try {
            doc = Jsoup
                    .connect(url)
                    .proxy(proxy)
                    .userAgent("Mozilla/5.0 (X11; Windows NT; NT 8.0; rv:52.0) Gecko/20100101 Firefox/52.0")
                    .get();
            
            Elements table = doc.select("div.text");

            for (Element element : table) {
                if (element.toString().length() > 800) {
                    Item item = new Item();
                    item.link = "https:".concat(element.getElementsByClass("title").attr("href"));
                    String price = element.getElementsByClass("price").text();
                    price = price.substring(0, price.indexOf("руб") - 1).trim();
                    item.cost = price;
                    if (item.cost != null) {
                        String tmp = price.replaceAll(" ", "").trim(); // тут свой пробел
                        item.cost_int = Integer.valueOf(tmp);
                        //item.cost_int = new Convert().strToInt(item.cost);
                        mas.add(item);
                    }

                }
            }

            // Sorting min sum
            Collections.sort(mas, new Comparator<Item>() {
                @Override
                public int compare(Item item2, Item item1) {
                    return item2.cost_int - item1.cost_int;
                }
            });

            for (Item item : mas) {
                result.add(item);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

}
