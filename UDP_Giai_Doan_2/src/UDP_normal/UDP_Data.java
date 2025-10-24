/*
[Mã câu hỏi (qCode): 0AvIlNtH].  Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2207. 
Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản:
a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng ";studentCode;qCode". 
Ví dụ: ";B21DCCN795;ylrhZ6UM".
b. Nhận thông điệp là một chuỗi từ server theo định dạng "requestId;n;k;z1,z2,...,zn", trong đó:
    requestId là chuỗi ngẫu nhiên duy nhất.
    n là số phần tử của mảng.
    k là kích thước cửa sổ trượt (k < n).
    z1 đến zn là n phần tử là số nguyên của mảng.

c. Thực hiện tìm giá trị lớn nhất trong mỗi cửa sổ trượt với kích thước k trên mảng số nguyên nhận được, 
và gửi thông điệp lên server theo định dạng "requestId;max1,max2,...,maxm", trong đó max1 đến maxm là 
các giá trị lớn nhất tương ứng trong mỗi cửa sổ.
Ví dụ: "requestId;5;3;1,5,2,3,4"
Kết quả: "requestId;5,5,4"
d. Đóng socket và kết thúc chương trình.
 */
package UDP_normal;

import java.util.*;
import java.io.*;
import java.net.*;

public class UDP_Data {

    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("203.162.10.109");
        int sP = 2207;
        String ma = ";B22DCCN169;0AvIlNtH";
        DatagramPacket dpGui = new DatagramPacket(ma.getBytes(), ma.length(), sA, sP);
        socket.send(dpGui);
        byte[] duLieu = new byte[1096];
        DatagramPacket dpNhan = new DatagramPacket(duLieu, duLieu.length);
        socket.receive(dpNhan);
        String s = new String(dpNhan.getData());
        System.out.println(s);
        String[] mang = s.trim().split(";");
        String reqId = mang[0] +";";
        System.out.println(reqId);
        int n = Integer.parseInt(mang[1]);
        System.out.println(n);
        int k = Integer.parseInt(mang[2]);
        System.out.println(k);
        String[] chuoiSo = mang[3].trim().split(",");
        List<Integer> chuoi = new ArrayList<>();
        for(String x : chuoiSo){
            chuoi.add(Integer.parseInt(x));
        }
        for(int i = 0; i < n; i++){
            if(i + k > n) break;
            List<Integer> res = new ArrayList<>();
            for(int j = 0; j < k; j++) {
                if(i + j < n){
                    res.add(chuoi.get(i+j));
                }
                else break;
            }
            int b = Collections.max(res);
            reqId += b + ",";
        }
        reqId = reqId.substring(0,reqId.length()-1);
        System.out.println(reqId);
        DatagramPacket dpGui2 = new DatagramPacket(reqId.getBytes(), reqId.length(), sA, sP);
        socket.send(dpGui2);
        socket.close();
    }
}
