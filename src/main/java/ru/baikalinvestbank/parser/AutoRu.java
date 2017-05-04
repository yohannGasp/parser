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
public class AutoRu {

    public List<Item> parse(String url, Proxy proxy) {

        List<Item> mas = new ArrayList<>();
        List<Item> result = new ArrayList<>();

        Document doc;
        try {

            doc = Jsoup
                    .connect(url)
                    .proxy(proxy)
                    .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                    .header("Accept-Encoding", "gzip, deflate, sdch, br")
                    .header("Accept-Language", "ru-RU,ru;q=0.8,en-US;q=0.6,en;q=0.4")
                    .header("Cache-Control", "max-age=0")
                    .header("Connection", "keep-alive") //from_lifetime=1493708922010
                    .header("Cookie", "suid=d4913b48d70dedbe20e22a90bde96575.5ce8d089ef617fc6151dbbf89e67c8c5; autoru_sid=a:dc31e550a88d0ad8238a06a32d2b9878.0cb100052927cc8b5d1cf6ec2e2dbae7|1493706248324.604800.A86X9qfp9C7PCnwW6Alufg.ITK0FU6Hs-0c4pxvKLFcLVEatBsWl6vJHmccyVkk_2k; autoruuid=dc31e550a88d0ad8238a06a32d2b9878.0cb100052927cc8b5d1cf6ec2e2dbae7; yandexuid=2366842291493695463; _ym_uid=14937062511019534966; _ym_isad=1; los=win; af_lpdid=2:391171837; from_lifetime=1493710235430; from=yandex")
                    .header("Host", "auto.ru")
                    .header("Referer", "https://auto.ru/")
                    .header("Upgrade-Insecure-Requests", "1")
                    .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.82 Safari/537.36 Vivaldi/1.9.818.44")
                    .get();

            Elements table = doc.select(".listing__row");

            for (Element element : table) {
                if (element.toString().length() > 10000) {

                    String href = element.getElementsByTag("a").attr("href");
                    String price = element.getElementsByClass("listing-item__price").text();
                    price = price.substring(0, price.indexOf("₽") - 1).trim();

                    System.out.println(href);
                    System.out.println(price);
                    System.out.println("=======================");
                    
                    Item item = new Item();
                    item.link = href;
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
