import RMI.ObjectService;
import RMI.Order;

import java.io.Serializable;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry("203.162.10.109", 1099);
        ObjectService objectService = (ObjectService) registry.lookup("RMIObjectService");
        String studentCode = "B22DCCN613";
        String qCode = "jcDtSWZd";
        Serializable object = objectService.requestObject(studentCode, qCode);
        Order order = (Order) object;
        order.setOrderCode(orderCode(order.getShippingType(), order.getCustomerCode(), order.getOrderDate()));

        objectService.submitObject(studentCode, qCode, order);
    }

    private static String orderCode(String shipping, String customer, String date) {
        String shippingType = shipping.substring(0,2).toUpperCase();
        String customerCode = customer.substring(customer.length()-3, customer.length());
        String[] words = date.split("-");
        String orderDate = words[2] + words[1];
        return shippingType + customerCode + orderDate;
    }
}