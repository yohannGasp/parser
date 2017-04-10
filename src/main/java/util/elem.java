/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author evgeniy
 */
public class elem {

    private String price;
    private String link;

    public elem(String price, String link) {
        this.price = price;
        this.link = link;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPrice() {
        return price;
    }

    public String getLink() {
        return link;
    }

}
