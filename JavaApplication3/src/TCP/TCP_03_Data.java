/*
[Mã câu hỏi (qCode): jFitPJNc].  Một chương trình máy chủ cho phép kết nối qua TCP tại cổng 2207 (hỗ trợ thời gian liên lạc 
tối đa cho mỗi yêu cầu là 5s), yêu cầu xây dựng chương trình (tạm gọi là client) thực hiện kết nối tới server tại 
cổng 2207, sử dụng luồng byte dữ liệu (DataInputStream/DataOutputStream) để trao đổi thông tin theo thứ tự: 
a.	Gửi chuỗi là mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". Ví dụ: "B15DCCN999;1D25ED92"
b.	Nhận lần lượt hai số nguyên a và b từ server
c.	Thực hiện tính toán tổng, tích và gửi lần lượt từng giá trị theo đúng thứ tự trên lên server
d.	Đóng kết nối và kết thúc
 */
package TCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class TCP_03_Data {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("203.162.10.109", 2207);
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        String message = "B22DCCN169;jFitPJNc";
        // a) Gửi "studentCode;qCode"
        dos.writeUTF(message);
        dos.flush();

        // b) Nhận lần lượt hai số nguyên a, b
        int a = dis.readInt();
        int b = dis.readInt();
//        System.out.println(a,b);
        // c) Tính tổng, tích và gửi lần lượt
        int sum = a + b;
        int prod = a * b;
        dos.writeInt(sum);
        dos.writeInt(prod);
        dos.flush();

        // d) Đóng kết nối
        dos.close();
        dis.close();
        socket.close();
    }
}
