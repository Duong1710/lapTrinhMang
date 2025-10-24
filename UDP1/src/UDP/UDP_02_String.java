/*
[Mã câu hỏi (qCode): CGApn89b].  Một chương trình server cho phép kết nối qua giao thức UDP tại cổng 2208. 
;Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản dưới đây:
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
package UDP;

import java.util.*;
import java.io.*;
import java.net.*;

public class UDP_02_String {

    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("203.162.10.109");
        int sP = 2208;
        String ma = ";B22DCCN169;CGApn89b";
        DatagramPacket dpGui = new DatagramPacket(ma.getBytes(), ma.length(), sA, sP);
        socket.send(dpGui);

        byte[] buff = new byte[1096];
        DatagramPacket dpNhan = new DatagramPacket(buff, buff.length);
        socket.receive(dpNhan);

        String duLieu = new String(dpNhan.getData());
        System.out.println(duLieu);
        String[] chuoiDuLieu = duLieu.trim().split(";");
        String id = chuoiDuLieu[0];
        String data = chuoiDuLieu[1];
        System.out.println(id);
        System.out.println(data);
        String[] mang = data.trim().split("\\s+");
        String tmp = "";
        for (String x : mang) {
            tmp += Character.toUpperCase(x.charAt(0)) + x.substring(1).toLowerCase() + " ";
        }
        tmp = id + ";" + tmp;
        System.out.println(tmp);
        tmp = tmp.substring(0, tmp.length() - 1);
        //        System.out.println(tmp);

        DatagramPacket dpGui2 = new DatagramPacket(tmp.getBytes(), tmp.length(), sA, sP);
        socket.send(dpGui2);
        socket.close();
    }
}
