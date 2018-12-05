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
    
    public static double getVAT(Jdbc jdbc) {
        Configuration[] configs = getConfigurations(jdbc);
        
        for(Configuration config : configs){
            if(config.getConfigName().equals("VAT")){
                return Double.parseDouble(config.getConfigValue());
            }
        }
        
        return -1;
    }
    
    public static Configuration getVATConfig(Jdbc jdbc) {
        Configuration[] configs = getConfigurations(jdbc);
        
        for(Configuration config : configs){
            if(config.getConfigName().equals("VAT")){
                return config;
            }
        }
        
        return null;
    }
    
    public static double getPricePerMile(Jdbc jdbc) {
        Configuration[] configs = getConfigurations(jdbc);
        
        for(Configuration config : configs){
            if(config.getConfigName().equals("PricePerMile")){
                return Double.parseDouble(config.getConfigValue());
            }
        }
        
        return -1;
    }
    
    public static Configuration getPricePerMileConfig(Jdbc jdbc) {
        Configuration[] configs = getConfigurations(jdbc);
        
        for(Configuration config : configs){
            if(config.getConfigName().equals("PricePerMile")){
                return config;
            }
        }
        
        return null;
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
    
    public static Configuration getShortDistPriceConfig(Jdbc jdbc) {
        Configuration[] configs = getConfigurations(jdbc);
        
        for(Configuration config : configs){
            if(config.getConfigName().equals("ShortDistPrice")){
                return config;
            }
        }
        
        return null;
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
    
    public static Configuration getShortDistanceConfig(Jdbc jdbc) {
        Configuration[] configs = getConfigurations(jdbc);
        
        for(Configuration config : configs){
            if(config.getConfigName().equals("ShortDistance")){
                return config;
            }
        }
        
        return null;
    }
    
    public static long updateVAT(double newVal, Jdbc jdbc){
        Configuration config = getVATConfig(jdbc);
        
        config.setConfigValue(String.valueOf(newVal));
        
        return jdbc.update(config);
    }
    
    public static long updatePricePerMile(double newVal, Jdbc jdbc){
        Configuration config = getPricePerMileConfig(jdbc);
        
        config.setConfigValue(String.valueOf(newVal));
        
        return jdbc.update(config);
    }
    
    public static long updateShortDistPrice(double newVal, Jdbc jdbc){
        Configuration config = getShortDistPriceConfig(jdbc);
        
        config.setConfigValue(String.valueOf(newVal));
        
        return jdbc.update(config);
    }
    
    public static long updateShortDistance(double newVal, Jdbc jdbc){
        Configuration config = getShortDistanceConfig(jdbc);
        
        config.setConfigValue(String.valueOf(newVal));
        
        return jdbc.update(config);
    }
}
