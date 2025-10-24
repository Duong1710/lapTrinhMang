/*
[Mã câu hỏi (qCode): xyTpt1ML].  Thông tin sản phẩm vì một lý do nào đó đã bị sửa đổi thành không đúng, cụ thể:
a.	Tên sản phẩm bị đổi ngược từ đầu tiên và từ cuối cùng, ví dụ: “lenovo thinkpad T520” bị chuyển thành “T520 thinkpad lenovo”
b.	Số lượng sản phẩm cũng bị đảo ngược giá trị, ví dụ từ 9981 thành 1899

Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2209. Yêu cầu là xây dựng một 
chương trình client giao tiếp với server để gửi/nhận các sản phẩm theo mô tả dưới đây:
a.	Đối tượng trao đổi là thể hiện của lớp Product được mô tả như sau
•	Tên đầy đủ của lớp: UDP.Product
•	Các thuộc tính: id String, code String, name String, quantity int
•	Một hàm khởi tạo có đầy đủ các thuộc tính được liệt kê ở trên
•	Trường dữ liệu: private static final long serialVersionUID = 20161107; 
b.	Giao tiếp với server theo kịch bản
•       Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng “;studentCode;qCode”. 
Ví dụ: “;B15DCCN001;EE29C059”

•	Nhận thông điệp chứa: 08 byte đầu chứa chuỗi requestId, các byte còn lại chứa một đối tượng là thể hiện 
của lớp Product từ server. Trong đối tượng này, các thuộc tính id, name và quantity đã được thiết lập giá trị.
•	Sửa các thông tin sai của đối tượng về tên và số lượng như mô tả ở trên và gửi đối tượng vừa được sửa đổi
lên server theo cấu trúc:
08 byte đầu chứa chuỗi requestId và các byte còn lại chứa đối tượng Product đã được sửa đổi.
•	Đóng socket và kết thúc chương trình.
 */
package UDP;

import java.util.*;
import java.io.*;
import java.net.*;
import java.nio.ByteOrder;

public class Main {

    public static String suaTen(String s) {
        String[] mang = s.trim().split("\\s+");
        String kQua = "";
        kQua += mang[mang.length - 1] + " ";
        for (int i = 1; i < mang.length - 1; i++) {
            kQua += mang[i] + " ";
        }
        kQua += mang[0];
        return kQua;
    }

    public static int suaSL(int s) {
        String aa = s + "";
        StringBuilder a = new StringBuilder(aa);
       
        a.reverse();
        return Integer.parseInt(a.toString());

    }

    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("203.162.10.109");
        int sP = 2209;
        String ma = ";B22DCCN169;xyTpt1ML";
        DatagramPacket dpGui = new DatagramPacket(ma.getBytes(), ma.length(), sA, sP);
        socket.send(dpGui);
        byte[] buf = new byte[1096];
        DatagramPacket dpNhan = new DatagramPacket(buf, buf.length);
        socket.receive(dpNhan);
        // Nhận reqId
        String reqId = new String(dpNhan.getData(), 0, 8);
        System.out.println(reqId);
        // Nhận đối tượng
        ByteArrayInputStream inByte = new ByteArrayInputStream(dpNhan.getData(), 8, dpNhan.getLength() - 8);
        ObjectInputStream inObject = new ObjectInputStream(inByte);
        Product sp = (Product) inObject.readObject();
        System.out.println(sp);
        sp.setName(suaTen(sp.getName()));
        sp.setQuantity(suaSL(sp.getQuantity()));
        System.out.println(sp);
        
        ByteArrayOutputStream outByte = new ByteArrayOutputStream();
        ObjectOutputStream outObject = new ObjectOutputStream(outByte);
        outObject.writeObject(sp);
        // Lưu kết quả vào mảng byte
        byte[] kQua = new byte[1096];
        System.arraycopy(reqId.getBytes(), 0, kQua, 0, 8);
        System.arraycopy(outByte.toByteArray(), 0, kQua, 8, outByte.size());
        // Gửi
        DatagramPacket dpGui2 = new DatagramPacket(kQua, kQua.length, sA, sP);
        socket.send(dpGui2);
        socket.close();
    }
}
