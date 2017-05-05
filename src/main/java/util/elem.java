/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.InputStream;

/**
 *
 * @author evgeniy
 */
public class elem {

    private String price;
    private String link;
    private InputStream image;

    public elem(String price, String link) {
        this.price = price;
        this.link = link;
        this.image = null;
    }

    public elem(String price, String link, InputStream image) {
        this.price = price;
        this.link = link;
        this.image = image;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setImage(InputStream image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public String getLink() {
        return link;
    }

    public InputStream getImage() {
        return image;
    }

}
