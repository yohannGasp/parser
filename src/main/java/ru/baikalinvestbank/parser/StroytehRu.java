package ru.baikalinvestbank.parser;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
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

        public Object getObject(URL url, Class c) {

            Object object = null;

            try {

                JAXBContext jaxbContext = JAXBContext.newInstance(c);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                object = jaxbUnmarshaller.unmarshal(url);
//            object = jaxbUnmarshaller.unmarshal(new File("/home/evgeniy/NetBeansProjects/parserSite/src/parsersite/response1.xml"));

            } catch (JAXBException ex) {

            }

            return object;
        }

    }

    /**
     *
     * @param url
     * @return
     */
    public List<Item> parse(String url) {

        List<Item> mas = new ArrayList();
        List<Item> result = new ArrayList<Item>();

        try {

            Items items = (Items) new JaxbParser().getObject(new URL(url), Items.class);
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

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return result;

    }

}
