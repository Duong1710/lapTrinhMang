/*
[Mã câu hỏi (qCode): Z2lSf8ed].  
Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2207. 
Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản:
a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng “;studentCode;qCode”.
Ví dụ: “;B15DCCN004;99D9F604”
b. Nhận thông điệp là một chuỗi từ server theo định dạng “requestId;z1,z2,...,z50” requestId là chuỗi ngẫu nhiên duy nhất
    z1 -> z50 là 50 số nguyên ngẫu nhiên
    c. Thực hiện tính số lớn thứ hai và số nhỏ thứ hai của thông điệp trong z1 -> z50 và gửi thông điệp 
lên server theo định dạng “requestId;secondMax,secondMin”
    d. Đóng socket và kết thúc chương trình
 */
package UDP;
import java.util.*;
import java.io.*;
import java.net.*;
public class UDP_03_Data_Type {
    public static void main(String[] args) throws Exception {
        // Thiết lập tham số cần thiết
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("203.162.10.109");
        int sP = 2207;
        String ma = ";B22DCCN169;Z2lSf8ed";
        // Gửi mã lên server
        DatagramPacket dpGui = new DatagramPacket(ma.getBytes(), ma.length(), sA, sP);
        socket.send(dpGui);
        
        // Nhận dữ liệu
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
        String[] mang = data.trim().split(",");
        Set<Long> tmp = new TreeSet<>();
        for(String x : mang){
            tmp.add(Long.parseLong(x));
        }
        ArrayList<Long> he = new ArrayList<>(tmp);
        Collections.sort(he);
        Long soBeNhi = he.get(1);
        Long soLonNhi = he.get(he.size()-2);
        String kQua = id + ";" + soLonNhi + "," + soBeNhi;
        DatagramPacket dpGui2 = new DatagramPacket(kQua.getBytes(), kQua.length(), sA, sP);
        socket.send(dpGui2);
        socket.close();
    }
}
