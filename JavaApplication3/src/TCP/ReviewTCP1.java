/*
[Mã câu hỏi (qCode): ZxemzfrW].  Một chương trình server hỗ trợ kết nối qua giao thức TCP 
tại cổng 2206 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). 
Yêu cầu xây dựng chương trình client thực hiện kết nối tới server trên sử dụng luồng byte dữ liệu (InputStream/OutputStream) 
để trao đổi thông tin theo thứ tự: 
a.	Gửi mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". Ví dụ: "B16DCCN999;C64967DD"
b.	Nhận dữ liệu từ server là một chuỗi gồm các giá trị nguyên được phân tách với nhau bằng  "|"
Ex: 2|5|9|11
c.	Thực hiện tìm giá trị tổng của các số nguyên trong chuỗi và gửi lên server
Ex: 27
d.	Đóng kết nối và kết thúc
 */
package TCP;

import java.util.*;
import java.io.*;
import java.net.*;

public class ReviewTCP1 {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("203.162.10.109", 2206);
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();

        String ma = "B22DCCN169;ZxemzfrW";
        out.write(ma.getBytes());
        out.flush();

        byte[] buffer = new byte[4096];
        int len = in.read(buffer);
        String duLieu = new String(buffer, 0, len);
        System.out.println(duLieu);
        String[] chuoi = duLieu.trim().split("|");
        Long tong = 0L;
        for(String x : chuoi){
            tong += Long.parseLong(x);
        }
        out.write(tong.toString().getBytes());
        out.flush();
        in.close();
        out.close();
        socket.close();
    }
}
