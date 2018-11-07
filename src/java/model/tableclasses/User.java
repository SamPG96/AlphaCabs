/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.tableclasses;

import model.tableclasses.Customer;
import model.tableclasses.GenericItem;
import model.tableclasses.Driver;

/**
 *
 * @author Sam
 */
public class User {
    private int id;
    private String username;
    private String password;
    private GenericItem userType;
    private Customer customer;
    private Driver driver;
    private GenericItem userStatus;
}
