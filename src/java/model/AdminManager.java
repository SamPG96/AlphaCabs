/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;
import model.tableclasses.*;

/**
 *
 * @author c2-newcombe
 */
public class AdminManager {
    
    public static Configuration[] getConfigurations(Jdbc jdbc) {
        ArrayList<HashMap<String, String>> configsMaps 
                = jdbc.retrieve(Configuration.TABLE_NAME_CONFIGURATIONS);
        Configuration[] configsArr = new Configuration[configsMaps.size()];

        //Map configsMaps to configsArr
        int i = 0;
        for (HashMap<String, String> map : configsMaps) {
            
            configsArr[i++] = new Configuration(Long.parseLong(map.get("ID")),
                    map.get("CONFIGNAME"),
                    map.get("CONFIGVALUE"));
        }

        return configsArr;
    }
    
    public static double getPricePerKM(Jdbc jdbc) {
        Configuration[] configs = getConfigurations(jdbc);
        
        for(Configuration config : configs){
            if(config.getConfigName().equals("PricePerKM")){
                return Double.parseDouble(config.getConfigValue());
            }
        }
        
        return -1;
    }
    
    public static double getShortDistPrice(Jdbc jdbc) {
        Configuration[] configs = getConfigurations(jdbc);
        
        for(Configuration config : configs){
            if(config.getConfigName().equals("ShortDistPrice")){
                return Double.parseDouble(config.getConfigValue());
            }
        }
        
        return -1;
    }
    
    public static int getShortDistance(Jdbc jdbc) {
        Configuration[] configs = getConfigurations(jdbc);
        
        for(Configuration config : configs){
            if(config.getConfigName().equals("ShortDistance")){
                return Integer.parseInt(config.getConfigValue());
            }
        }
        
        return -1;
    }
    
    public static void UpdateVATValue(double VAT, Jdbc jdbc){
        
    }
}
