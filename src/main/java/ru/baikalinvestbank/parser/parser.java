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
public class parser {

    public String rootUrl;

    /**
     *
     * @param site Interface
     * @param url
     * @param proxy
     * @return
     */
    public List<Item> parse(site site, String url, Proxy proxy) {

        List<Item> res = null;

        res = site.parse(url, proxy);

        return res;
    }

    /**
     * identifyCategory
     *
     * @return url
     */
    public static String identifyCategory(String nameCategory) {
        String result = null;

        switch (nameCategory) {

            case "Грузовые тягачи зарубежные":
                result = "https://auto.ru/artic/";
                break;

            case "Грузовые тягачи отечественные":
                result = "https://auto.ru/artic/";
                break;

            case "Самосвалы зарубежные":
                result = "https://auto.ru/trucks/";
                break;

            case "Самосвалы отечественные":
                result = "https://auto.ru/trucks/";
                break;

            case "Автобусы зарубежные":
                result = "https://auto.ru/bus/";
                break;

            case "Автобусы отечественные":
                result = "https://auto.ru/bus/";
                break;

            case "Специальные зарубежные":
                result = "";
                break;

            case "Специальные отечественные":
                result = "";
                break;

            case "Дорожно - строительная техника зарубежная":
                result = "";
                break;

            case "Дорожно - строительная техника отечественная":
                result = "";
                break;

            case "Грузовики зарубежные":
                result = "https://auto.ru/trucks/";
                break;

            case "Грузовики отечественные":
                result = "https://auto.ru/trucks/";
                break;

            case "Легковая техника зарубежная":
                result = "https://auto.ru/cars/";
                break;

            case "Легковая техника отечественная":
                result = "https://auto.ru/cars/";
                break;

            case "Прицепы и полуприцепы зарубежные":
                result = "https://auto.ru/drags/";
                break;

            case "Прицепы и полуприцепы отечественные":
                result = "https://auto.ru/drags/";
                break;

            default:
                result = "";
                break;

        }

        return result;
    }

    /**
     * translitMarka to Upper
     *
     * @return
     */
    public static String translitMarka(String marka) {
        String result = null;

        switch (marka) {

            case "ЗИЛ":
                result = "zil";
                break;

            case "ГАЗ":
                result = "gaz";
                break;

            case "КАМАЗ":
                result = "kamaz";
                break;

            case "МАЗ":
                result = "maz";
                break;

            case "БЕЛАЗ":
                result = "belaz";
                break;

            case "ГАЗ-САЗ":
                result = "gaz_saz";
                break;

            case "КРАЗ":
                result = "kraz";
                break;

            case "МЗКТ":
                result = "mzkt";
                break;

            case "НЕФАЗ":
                result = "nefaz";
                break;

            case "УРАЛ":
                result = "ural";
                break;

            case "ВЕЗДЕХОДЫ ГВА":
                result = "gva";
                break;

            case "ЯРОВИТ МОТОРС":
                result = "yarovit_motors";
                break;

            default:
                result = marka.toLowerCase();
                break;

        }

        return result;
    }

}
