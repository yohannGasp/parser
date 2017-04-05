package ru.baikalinvestbank.parser;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
    public String parse(String url) {

        try {

            String base_url = url;
            
            base_url = "https://www.stroyteh.ru/sale/xml/?mark=%D0%93%D0%90%D0%97&model=3302&mileageMax=10000";

            List<Item> mas = new ArrayList();

            Items items = (Items) new JaxbParser().getObject(new URL(base_url), Items.class);
            for (Item item : items.getMembers()) {
                mas.add(item);
            }

            // Sorting
//        Collections.sort(mas, new Comparator<Item>() {
//
//            @Override
//            public int compare(Item item2, Item item1) {
//                return item2.cost - item1.cost;
//            }
//        });
            for (Item item : mas) {
                System.out.println(item.title + " " + item.cost);
                System.out.println(item.link);
            }

        } catch (IOException ex) {
            
        }
        
        return "";

    }

}
