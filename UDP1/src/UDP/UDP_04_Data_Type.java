/*
[Mã câu hỏi (qCode): 1ANrxCSD].  Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2207. 
Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản:
a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng ";studentCode;qCode".
Ví dụ: ";B15DCCN010;D3F9A7B8"
b. Nhận thông điệp là một chuỗi từ server theo định dạng "requestId;a;b", với:
•	requestId là chuỗi ngẫu nhiên duy nhất.
•	a và b là chuỗi thể hiện hai số nguyên lớn (hơn hoặc bằng 10 chữ số).
Ví dụ: "X1Y2Z3;9876543210;123456789"
c. Tính tổng và hiệu của hai số a và b, gửi thông điệp lên server theo định dạng "requestId;sum;difference".Ví dụ: 
Nếu nhận được "X1Y2Z3;9876543210,123456789", tổng là 9999999999 và hiệu là 9753086421.
Kết quả gửi lại sẽ là "X1Y2Z3;9999999999,9753086421".
d. Đóng socket và kết thúc chương trình
 */
package UDP;

import java.util.*;
import java.net.*;
import java.io.*;
import java.math.BigInteger;
public class UDP_04_Data_Type {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("203.162.10.109");
        int sP = 2207;
        String ma = ";B22DCCN169;1ANrxCSD";
        // Gửi dữ liệu
        DatagramPacket dpGui = new DatagramPacket(ma.getBytes(), ma.length(), sA, sP);
        socket.send(dpGui);
        // Nhận dữ liệu
        byte[] buff = new byte[2056];
        DatagramPacket dpNhan = new DatagramPacket(buff, buff.length);
        socket.receive(dpNhan);
        String duLieu = new String(dpNhan.getData());
        System.out.println(duLieu);
        String[] mang1 = duLieu.trim().split(";");
        String id = mang1[0];
        String soLon11 = mang1[1];
        String soLon12 = mang1[2];
        System.out.println(id);
        System.out.println(soLon11);
        System.out.println(soLon12);
        BigInteger soLon1 = new BigInteger(soLon11);
        BigInteger soLon2 = new BigInteger(soLon12);
        BigInteger tong = soLon1.add(soLon2);
        BigInteger hieu = soLon1.subtract(soLon2);
        String kQua = id + ";" + tong + "," + hieu;
        System.out.println(kQua);
        
        DatagramPacket dpGui2 = new DatagramPacket(kQua.getBytes(), kQua.length(), sA, sP);
        socket.send(dpGui2);
        socket.close();
    }
}
