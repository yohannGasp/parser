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
public class itemModel {

    public itemModel(String name, String href) {
        this.name = name;
        this.href = href;
    }

    String name;
    String href;

    public String getName() {
        return name;
    }

    public String getHref() {
        return href;
    }
    
    
}
