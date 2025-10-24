/*
[Mã câu hỏi (qCode): hVs8U8Gi].  Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2208.
Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản:
a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode".
Ví dụ: ";B15DCCN009;EF56GH78"
b. Nhận thông điệp là một chuỗi từ server theo định dạng "requestId;data", với:
•	requestId là chuỗi ngẫu nhiên duy nhất.
•	data là một chuỗi ký tự chứa nhiều từ, được phân cách bởi dấu cách.
Ví dụ: "EF56GH78;The quick brown fox"
c. Sắp xếp các từ trong chuỗi theo thứ tự từ điển ngược (z đến a) và gửi thông điệp lên server theo định dạng 
"requestId;word1,word2,...,wordN".
Ví dụ: Với data = "The quick brown fox", kết quả là: "EF56GH78;quick,fox,brown,The"
d. Đóng socket và kết thúc chương trình
 */
package UDP;

import java.util.*;
import java.io.*;
import java.net.*;

public class UDP_05_String {

    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("203.162.10.109");
        int sP = 2208;
        String ma = ";B22DCCN169;hVs8U8Gi";

        DatagramPacket dpGui = new DatagramPacket(ma.getBytes(), ma.length(), sA, sP);
        socket.send(dpGui);

        byte[] buff = new byte[2054];
        DatagramPacket dpNhan = new DatagramPacket(buff, buff.length);
        socket.receive(dpNhan);
        String duLieu = new String(dpNhan.getData());
        System.out.println(duLieu);
        String[] mang = duLieu.trim().split(";");
        String id = mang[0] + ";";
        String data = mang[1];
        System.out.println(id);
        System.out.println(data);
        String[] chuoiCau = data.trim().split("\\s+");
        Arrays.sort(chuoiCau, new Comparator <String>(){
            @Override
            public int compare(String o1, String o2) {
                return o2.toLowerCase().compareTo(o1.toLowerCase());
            }
        });
        for (String x : chuoiCau) {
            id += x + ",";
        }
        id = id.substring(0, id.length() - 1);
        System.out.println(id);

        DatagramPacket dpGui2 = new DatagramPacket(id.getBytes(), id.length(), sA, sP);
        socket.send(dpGui2);
        socket.close();
    }
}
