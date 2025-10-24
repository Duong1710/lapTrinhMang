/*
[Mã câu hỏi (qCode): 3BIRpvK1].  Một chương trình (tạm gọi là RMI Server) cung cấp các mã khuyến mãi 
sản phẩm ngẫu nhiên cho sinh viên, được mô tả như sau:
•	Giao diện từ xa
    public interface ObjectService extends Remote {
        public Serializable requestObject(String studentCode, String qAlias) throws RemoteException;

        public void submitObject(String studentCode, String qAlias, Serializable object) throws RemoteException;
    }
•	Lớp ProductX gồm các thuộc tính id String, code String, discountCode String, discount int.
o	Một hàm khởi dựng với đầy đủ các thuộc tính liệt kê ở trên
o	Trường dữ liệu: private static final long serialVersionUID = 20171107; 
•	Đối tượng triệu gọi từ xa được đăng ký RegistryServer với tên: RMIObjectService
•	Tất cả các lớp được viết trong package RMI

Yêu cầu là xây dựng một chương trình client thực hiện các tương tác với hệ thống phần mềm ở trên theo kịch bản dưới đây:
1.	  Triệu gọi phương thức từ xa requestObject từ RMI Server với tham số đầu vào là mã sinh viên,
mã câu để nhận về đối tượng ProductX
2.    Nhận về đối tượng ProductX từ RMI Server với giá trị ban đầu đã được thiết lập. Tính tổng các chữ số nằm 
trong chuỗi mã giảm giá (discountCode) để ra giá trị được khuyến mãi của sản phẩm và cập nhật giá trị của khuyến mãi (discount)
3.	Triệu gọi phương thức từ xa submitObject với tham số đầu vào là đối tượng Product đã được cập nhật đầy đủ 
thông tin giá trị khuyến mãi
4.	Kết thúc chương trình
 */
package RMI;

import java.util.*;
import java.rmi.*;
import java.rmi.registry.*;

public class RMI_Object_Service {

    public static int xuLy(String s) {
        int tong = 0;
        for (char x : s.toCharArray()) {
            if (Character.isDigit(x) ) {
                String a = x + "";
                tong += Integer.parseInt(a);
            }
        }
        return tong;
    }

    public static void main(String[] args) throws Exception {
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        ObjectService sv = (ObjectService) rg.lookup("RMIObjectService");
        String ma = "B22DCCN169";
        String qCode = "3BIRpvK1";
        ProductX sp = (ProductX) sv.requestObject(ma, qCode);
        System.out.println(sp);
        System.out.println(xuLy("156"));
        System.out.println(sp.getDiscountCode());
        int a = xuLy(sp.getDiscountCode());
        System.out.println(a);
//        System.out.println(xuLy(sp.getDiscountCode());
        sp.setDiscount(a);
        System.out.println(sp);
        sv.submitObject(ma, qCode, sp);
    }
}
//public class RMI_Object_Service {
//    public static void main(String[] args) throws Exception {
//        String qCode = "3BIRpvK1";
//        String studentCode = "B22DCCN169";
//        String host = "203.162.10.109";
//        int port = 1099;
//        Registry registry = LocateRegistry.getRegistry(host, port);
//        ObjectService objectService = (ObjectService) registry.lookup("RMIObjectService");
//        ProductX productX = (ProductX) objectService.requestObject(studentCode, qCode);
//        System.out.println(productX);
//        productX.discount = sumDigit(productX.discountCode);
//        objectService.submitObject(studentCode, qCode, productX);
//        System.out.println(productX);
//        
//    }
//    public static int sumDigit(String s){
//        int len = s.length();
//        int sum = 0;
//        for(int i=0;i<len;i++){
//            try{
//                int digit = Integer.valueOf(s.charAt(i) + "");
//                sum+= digit;
//            }
//            catch(Exception e){
//                continue;
//            }
//        }
//        return sum;
//    }
//}
