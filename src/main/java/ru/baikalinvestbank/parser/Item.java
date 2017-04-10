/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.baikalinvestbank.parser;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "item")
public class Item {

    @XmlElement
    String mark;

    @XmlElement
    String model;

    @XmlElement
    String category;

    @XmlElement
    String title;

    @XmlElement
    String description;

    @XmlElement
    String year;

    @XmlElement
    String mileage;

    @XmlElement
    String cost;

    int cost_int;

    @XmlElement
    String currency;

    @XmlElement
    String country;

    @XmlElement
    String region;

    @XmlElement
    String city;

    @XmlElement
    String photo;

    @XmlElement
    String link;

    @XmlElement
    String dateAdd;

    public String getYear() {
        return year;
    }

    public String getCost() {
        return cost;
    }

    public String getLink() {
        return link;
    }

}
