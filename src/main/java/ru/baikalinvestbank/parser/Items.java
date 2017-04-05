/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.baikalinvestbank.parser;

import ru.baikalinvestbank.parser.Item;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Items {

    @XmlAttribute
    String version;

    @XmlAttribute
    String url;

    @XmlAttribute
    String generator;

    private List<Item> members = new ArrayList<Item>();

    @XmlElement(name="item")
    public List<Item> getMembers() {
        return members;
    }

}
