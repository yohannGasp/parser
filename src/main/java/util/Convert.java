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
public class Convert {

    public static int strToInt(String param) {

        int result;
        String tmp = param.replaceAll(" ", "").trim();

        result = Integer.valueOf(tmp);

        return result;
    }

}
