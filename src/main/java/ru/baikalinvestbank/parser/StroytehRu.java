package ru.baikalinvestbank.parser;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import util.Convert;

/*
 * XML: https://www.stroyteh.ru/sale/xml/
 * 
 * https://www.stroyteh.ru/sale/xml/?mark=%D0%93%D0%90%D0%97&model=3302&mileageMax=10000
 */
/**
 *
 * @author evgeniy
 */
public class StroytehRu {

    class JaxbParser {

        public Object getObject(URL url, Class c, Proxy proxy) {

            Object object = null;
            JAXBContext jaxbContext = null;
            Unmarshaller jaxbUnmarshaller = null;

            try {

                jaxbContext = JAXBContext.newInstance(c);
                jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            } catch (Exception e) {
                System.out.println(e.toString());
            }

            if (proxy != null) {

                try {

                    HttpURLConnection connection = (HttpURLConnection) url.openConnection(proxy);
                    if (connection != null) {
                        if (connection.getContentLengthLong() != -1) {

                            // content
                            InputStream is = connection.getInputStream();

                            object = jaxbUnmarshaller.unmarshal(is);

                            is.close();
                            connection.disconnect();
                        } else {
                            System.out.println("proxy getContentLengthLong " + connection.getContentLengthLong());
                        }
                    }

                } catch (Exception e) {
                    System.out.println(e.toString());
                }

            } else {

                try {

                    object = jaxbUnmarshaller.unmarshal(url);

                } catch (Exception e) {
                    System.out.println(e.toString());
                }

            }

            return object;
        }

    }

    /**
     *
     * @param url
     * @return
     */
    public List<Item> parse(String url, Proxy proxy) {

        List<Item> mas = new ArrayList<>();
        List<Item> result = new ArrayList<>();

        try {
            Items items = (Items) new JaxbParser().getObject(new URL(url), Items.class, proxy);
            if (items != null) {
                for (Item item : items.getMembers()) {
                    if (item.cost != null) {
                        item.cost_int = new Convert().strToInt(item.cost);
                        mas.add(item);
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
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return result;

    }

}
