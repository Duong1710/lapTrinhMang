/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package WS_Object;

import java.util.List;
import vn.medianews.*;

/**
 *
 * @author AD
 */
public class Main {
    public static void main(String[] args) {
        ObjectService_Service objectService_Service = new ObjectService_Service();
        ObjectService objectService = objectService_Service.getObjectServicePort();
        
        String studentCode = "B22DCCN613";
        String qCode = "9ngmQPts";
        
        List<EmployeeY> employeeYs = objectService.requestListEmployeeY(studentCode, qCode);
        
        employeeYs.sort((e1, e2) -> {
            return e1.getStartDate().compare(e2.getStartDate());
        });
        
        objectService.submitListEmployeeY(studentCode, qCode, employeeYs);
    }
}
