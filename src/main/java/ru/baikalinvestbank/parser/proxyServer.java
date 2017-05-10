/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.baikalinvestbank.parser;

public class proxyServer {

    String server;
    String port;

    public proxyServer(String server, String port) {
        this.server = server;
        this.port = port;
    }

    public String getServer() {
        return server;
    }

    public String getPort() {
        return port;
    }

}
