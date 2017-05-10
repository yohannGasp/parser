/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.baikalinvestbank.parser;

import static gui.mainJFrame.log;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author evgeniy
 */
public class parser {

    public String rootUrl;
    public List<proxyServer> proxyList = new ArrayList<>();
    int indProxy = 0;

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

    /**
     * getProxy
     * @return
     */
    public proxyServer getProxy() {
        proxyServer res = null;
        
        if (this.indProxy == this.proxyList.size()) {
            this.indProxy = 0;
        }
        
        res = this.proxyList.get(indProxy++);
        log.log(Level.INFO, "getProxy " + res.getServer() + ":" + res.getPort());
        return res;
    }

    /**
     * loadProxy
     *
     * @param json
     */
    public void loadProxy(String json) {
        JSONParser parser = new JSONParser();
        try {
            JSONObject jsonObj = (JSONObject) parser.parse(json);
            JSONArray ja = (JSONArray) jsonObj.get("proxy_servers");
            for (int i = 0, size = ja.size(); i < size; i++) {
                JSONObject jo = (JSONObject) ja.get(i);
                this.proxyList.add(new proxyServer(jo.get("server").toString(), jo.get("port").toString()));
            }
            log.log(Level.INFO, "loadProxy size:" + this.proxyList.size());
            log.log(Level.INFO, "loadProxy ok");

        } catch (Exception e) {

        }

    }

    /**
     * fromFile
     *
     * @param path
     * @return
     */
    public String fromFile(String path) {
        String str = null;
        try {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));) {
                StringBuilder sb = new StringBuilder();
                while ((str = br.readLine()) != null) {
                    sb.append(str);
                }
                str = sb.toString();
            }
        } catch (Exception e) {

        }
        return str;
    }

}
