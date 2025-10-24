/*
[Mã câu hỏi (qCode): o8leFMLV].  Một chương trình server hỗ trợ kết nối qua giao thức TCP tại cổng 2206 (hỗ trợ thời gian giao 
tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu xây dựng chương trình client thực hiện kết nối tới server sử dụng luồng byte 
dữ liệu (InputStream/OutputStream) để trao đổi thông tin theo thứ tự:
a. Gửi mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode".
Ví dụ: "B16DCCN999;E56FAB67"
b. Nhận dữ liệu từ server là một chuỗi các số nguyên được phân tách bởi ký tự ",".
Ví dụ: " 3,7,2,5,8,1"
c. Tìm vị trí mà độ lệch của tổng bên trái và tổng bên phải là nhỏ nhất -> Gửi lên server vị trí đó, tổng trái, tổng phải và độ lệch. Ví dụ: với dãy " 3,7,2,5,8,1", vị trí 3 có độ lệch nhỏ nhất = 3 → Kết quả gửi server: "3,12,9,3"
d. Đóng kết nối và kết thúc chương trình.
 */
package TCP;

import java.util.*;
import java.io.*;
import java.net.*;

public class TCP_06_test {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("203.162.10.109", 2206);
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();

        String ma = "B22DCCN169;o8leFMLV";
        out.write(ma.getBytes());
        out.flush();

        byte[] buff = new byte[4096];
        int len = in.read(buff);
        String duLieu = new String(buff, 0, len);
        System.out.println(duLieu);

        String[] dsDuLieu = duLieu.trim().split(",");
        ArrayList<Long> danhSachSo = new ArrayList<>();
        Long tongDaySo = 0L;
        for (String x : dsDuLieu) {
            danhSachSo.add(Long.valueOf(x));
            tongDaySo += Long.valueOf(x);
        }
        Long tongTrai = 0L;
        Long tongPhai = tongDaySo - danhSachSo.get(0);
        System.out.println(tongTrai + " " + tongPhai);
        Long doLech = Long.MAX_VALUE;
        int indx = -1;
        Long res2 = -1L;
        Long res1 = -1L;
        for (int i = 1; i < danhSachSo.size()-1; i++) {
            tongTrai += danhSachSo.get(i-1);
            tongPhai -= danhSachSo.get(i);
            Long chenhLech = Math.abs(tongTrai - tongPhai);
            if (doLech > chenhLech) {
                doLech = chenhLech;
                indx = i;
                res1 = tongTrai;
                res2 = tongPhai;
            }
        }
        ////     -> Gửi lên server vị trí đó, tổng trái, tổng phải và độ lệch. 
//Ví dụ: với dãy " 3,7,2,5,8,1", vị trí 3 có độ lệch nhỏ nhất = 3 
//        → Kết quả gửi server: "3,12,9,3"
        String kQuaNop = indx + "," + res1 + "," + res2 + "," + doLech;
        System.out.println(kQuaNop);
        out.write(kQuaNop.getBytes());
        out.flush();
        
        out.close();
        in.close();
        socket.close();
    }
}
