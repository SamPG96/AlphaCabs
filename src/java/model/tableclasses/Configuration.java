/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.tableclasses;

/**
 *
 * @author Conor
 */
public class Configuration {
    public static final String TABLE_NAME_CONFIGURATIONS = "Configurations";
    private long id;
    private String configName;
    private String configValue;

    public Configuration(long id, String configName, String configValue) {
        this.id = id;
        this.configName = configName;
        this.configValue = configValue;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    
}
