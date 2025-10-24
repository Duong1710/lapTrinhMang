/*
 [Mã câu hỏi (qCode): pNlIhlT7].  Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2207.
Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản:
a.	Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng “;studentCode;qCode”.
Ví dụ: “;B15DCCN001;DC73CA2E”
b.	Nhận thông điệp là một chuỗi từ server theo định dạng “requestId;a1,a2,...,a50” 
-	requestId là chuỗi ngẫu nhiên duy nhất
-	a1 -> a50 là 50 số nguyên ngẫu nhiên
c.	Thực hiện tìm giá trị lớn nhất và giá trị nhỏ nhất thông điệp trong a1 -> a50 và gửi thông điệp 
lên lên server theo định dạng “requestId;max,min”
d.	Đóng socket và kết thúc chương trình
 */
package UDP;
import java.util.*;
import java.io.*;
import java.net.*;
public class UDP_01_Data_Type {
    public static void main(String[] args)throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("203.162.10.109"); 
        int sP = 2207;
        String ma = ";B22DCCN169;pNlIhlT7";
        // Gửi mã lên server để nhận dữ liêu
        DatagramPacket dpGui = new DatagramPacket(ma.getBytes(), ma.length(), sA, sP);
        socket.send(dpGui);
        
        // Nhận dữ liệu
        byte[] buffer = new byte[1024];
        DatagramPacket dpNhan = new DatagramPacket(buffer, buffer.length);
        socket.receive(dpNhan);
        // In ra dữ liệu
        String s1 = new String(dpNhan.getData());
        System.out.println(s1);
        String[] tmp = s1.trim().split(";");
        String requestID = tmp[0];
        System.out.println(requestID);
        String xuLy = tmp[1];
        System.out.println(xuLy);
//        xuLy = xuLy.replaceAll("[^a-zA-Z0-9]", " ").trim();
//        System.out.println(xuLy);
        String[] daySo = xuLy.trim().split(",");
        int soBe = Integer.MAX_VALUE;
        int soLon = Integer.MIN_VALUE;
        for(String x : daySo){
            if(soBe > Integer.parseInt(x)){
                soBe = Integer.parseInt(x);
            }
            if(soLon < Integer.parseInt(x)){
                soLon = Integer.parseInt(x);
            }
        }
        String kQua = requestID + ";" + soLon + ","+ soBe;
        DatagramPacket dpGui2 = new DatagramPacket(kQua.getBytes(), kQua.length(), sA, sP);
        socket.send(dpGui2);
        socket.close();
    }
}
