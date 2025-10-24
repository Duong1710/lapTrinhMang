/*
[Mã câu hỏi (qCode): OsrJIjN6].  Một chương trình server cho phép kết nối qua giao thức UDP tại cổng 2208.
Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản dưới đây:
a.	Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng “;studentCode;qCode”. 
Ví dụ: “;B15DCCN001;5B35BCC1”
b.	Nhận thông điệp từ server theo định dạng “requestId;data” 
-	requestId là một chuỗi ngẫu nhiên duy nhất
-	data là chuỗi dữ liệu cần xử lý
c.	Xử lý chuẩn hóa chuỗi đã nhận thành theo nguyên tắc 
i.	Ký tự đầu tiên của từng từ trong chuỗi là in hoa
ii.	Các ký tự còn lại của chuỗi là in thường
Gửi thông điệp chứa chuỗi đã được chuẩn hóa lên server theo định dạng “requestId;data”
d.	Đóng socket và kết thúc chương trình
 */
package UDP_normal;

import java.util.*;
import java.io.*;
import java.net.*;
public class UDP_String {
    public static void main(String[] args)throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("203.162.10.109");
        int sP = 2208;
        String ma = ";B22DCCN169;OsrJIjN6";
        DatagramPacket dpGui = new DatagramPacket(ma.getBytes(), ma.length(), sA, sP);
        socket.send(dpGui);
        byte[] duLieu = new byte[1096];
        DatagramPacket dpNhan = new DatagramPacket(duLieu, duLieu.length);
        socket.receive(dpNhan);
        String duLieu2 = new String(dpNhan.getData());
        System.out.println(duLieu2);
        String[] mang = duLieu2.trim().split(";");
        String reqId = mang[0];
        String data = mang[1];
        System.out.println(reqId);
        reqId += ";";
        System.out.println(reqId);
        System.out.println(data);
        String[] mang2 = data.trim().split("\\s+");
        for(String x : mang2){
            String a = "";
            a += Character.toUpperCase(x.charAt(0)) + x .substring(1, x.length()).toLowerCase();
            reqId += a + " ";
        }
        reqId = reqId.substring(0,reqId.length()-1);
        System.out.println(reqId);
        DatagramPacket dpGui2 = new DatagramPacket(reqId.getBytes(), reqId.length(), sA, sP);
        socket.send(dpGui2);
        socket.close();
    }
}
