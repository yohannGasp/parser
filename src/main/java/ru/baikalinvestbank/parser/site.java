/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.baikalinvestbank.parser;

import java.net.Proxy;
import java.util.List;

/**
 *
 * @author evgeniy
 */
public interface site {
    
    /**
     *
     * @param url
     * @param proxy
     * @return
     */
    public List<Item> parse(String url, Proxy proxy);
    
}
