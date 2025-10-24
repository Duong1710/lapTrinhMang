/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package WS_data;

import java.util.ArrayList;
import java.util.List;
import data.DataService_Service;
import data.DataService;

/**
 *
 * @author AD
 */
public class Main {
    public static void main(String[] args) {
        DataService_Service dataService_Service = new DataService_Service();
        DataService dataService = dataService_Service.getDataServicePort();
        
        String studentCode = "B22DCCN613";
        String qCode = "zKnU5viv";
        
        List<Integer> data = dataService.getData(studentCode, qCode);
        List<String> result = new ArrayList<>();
        
        for(int a : data) {
            String x = Integer.toOctalString(a).toUpperCase();
            String y = Integer.toHexString(a).toUpperCase();
            String res = x + "|" + y;
            result.add(res);
        }
        
        dataService.submitDataStringArray(studentCode, qCode, result);
    }
}
