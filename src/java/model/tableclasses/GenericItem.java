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
public class GenericItem {
    public static final String TABLE_NAME_USERSTATUS = "UserStatus";
    public static final String TABLE_NAME_BOOKINGSTATUS = "BookingStatus";
    public static final String TABLE_NAME_USERTYPE = "UserType";
    
    private long id;
    private String name;

    public GenericItem() {
    }
    
    public GenericItem(long id) {
        this.id = id;
    }

    public GenericItem(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
