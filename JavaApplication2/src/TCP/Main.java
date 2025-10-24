/*
[Mã câu hỏi (qCode): QWSZ93D4].  Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2209 
(hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5 giây). Yêu cầu là xây dựng một 
chương trình client tương tác với server sử dụng các luồng đối tượng (ObjectOutputStream/ObjectInputStream) theo kịch bản dưới đây:
Biết lớp TCP.Product gồm các thuộc tính (id int, name String, price double, int discount)
và private static final long serialVersionUID = 20231107;
a. Gửi đối tượng là một chuỗi gồm mã sinh viên và mã câu hỏi với định dạng "studentCode;qCode". Ví dụ: "B15DCCN999;1E08CA31"
b. Nhận một đối tượng là thể hiện của lớp TCP.Product từ server.
c. Tính toán giá trị giảm giá theo price theo nguyên tắc: Giá trị giảm giá (discount) bằng tổng 
các chữ số trong phần nguyên của giá sản phẩm (price). Thực hiện gán giá trị cho thuộc tính discount 
và gửi lên đối tượng nhận được lên server.
d. Đóng kết nối và kết thúc chương trình.
 */
package TCP;

import java.io.*;
import java.util.*;
import java.net.*;
public class Main {
    public static void main(String[] args)throws Exception {
        Socket socket = new Socket("203.162.10.109", 2209);
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        
        String ma = "B22DCCN169;QWSZ93D4";
        out.writeObject(ma);
        out.flush();
        
        Product p =(Product)in.readObject();
        System.out.println(p);
        double gia = p.getPrice();
        long giaCa = (long)Math.floor(gia);
        int giamGia = 0;
        while(giaCa >= 10){
            giamGia += giaCa % 10;
            giaCa /= 10;
        }
        giamGia += giaCa;
        p.setDiscount(giamGia);
        System.out.println(p);
        out.writeObject(p);
        out.flush();
        
        in.close(); out.close(); socket.close();
    }
}
