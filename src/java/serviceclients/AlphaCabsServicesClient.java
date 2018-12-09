/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceclients;

import java.util.HashMap;

/**
 *
 * @author Sam
 */
public class AlphaCabsServicesClient {
    /*
    * Queries the AlphaCab service for calculating a fare
    */
    public static HashMap<String, String> calculateFare(java.lang.String source, java.lang.String destination) {
        HashMap<String, String> HMresults;
        String resultsStr;
        services.Fare_Service service = new services.Fare_Service();
        services.Fare port = service.getFarePort();
        resultsStr = port.calculate(source, destination);

        // The webservice creats a result set as a hashmap but in order to
        // send it back to the client it converts the HashMap to a string.
        // Here the String is converted back to a HashMap.
        resultsStr = resultsStr.substring(1, resultsStr.length() - 1);
        HMresults = new HashMap();
        for (String pair: resultsStr.split(",")){
            HMresults.put(pair.split("=")[0].trim(), pair.split("=")[1].trim());
        }
         
        return HMresults;
    }    
}
