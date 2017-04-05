/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.baikalinvestbank.parser;

/**
 *
 * @author evgeniy
 */
public class Main {

    public static void main(String[] args) {

        String request = "https://www.gruzovik.ru/offers/trucks/refrigerator/";

        new gruzovikRu().parse(request);
        

    }

}
