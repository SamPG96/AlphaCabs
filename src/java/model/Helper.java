/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Conor
 */
public class Helper {
    public static String doubleToCurrencyFormat(double d){
        String s = String.valueOf(d);
        String[] strArr = s.split("\\.");
        
        if(strArr.length != 2){
            return null;
        }
        
        if(strArr[1].endsWith("0") || strArr[1].length() == 1){
            s += "0";
        }
        return s;
    }
}
