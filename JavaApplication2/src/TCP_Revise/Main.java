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
package TCP_Revise;

import java.util.*;
import java.io.*;
import java.net.*;
public class Main {
    public static int giamGia(double s){
        int a = (int)s;
        int tong = 0;
        while(a != 0){
            tong += a % 10;
            a /= 10;
        }
        return tong;
    }
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("203.162.10.109", 2209);
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        String ma = "B22DCCN169;QWSZ93D4";
        out.writeObject(ma);
        out.flush();
        Product sp = (Product)in.readObject();
        System.out.println(sp);
        sp.setDiscount(giamGia(sp.getPrice()));
        System.out.println(sp);
        out.writeObject(sp);
        out.flush();
    }
}
