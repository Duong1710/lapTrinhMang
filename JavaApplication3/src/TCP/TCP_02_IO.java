/*
[Mã câu hỏi (qCode): ZxemzfrW].  Một chương trình server hỗ trợ kết nối qua giao thức TCP tại cổng 2206 
(hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). 
Yêu cầu xây dựng chương trình client thực hiện kết nối tới server trên sử dụng luồng 
byte dữ liệu (InputStream/OutputStream) để trao đổi thông tin theo thứ tự: 
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

public class TCP_02_IO {
    public static void main(String[] args) throws Exception{
        String serverIP = "203.162.10.109";
        String studentCode = "B22DCCN650";
        String qCode = "cchKeSPP";
        String message = studentCode + ";" + qCode;
        Socket socket = new Socket(serverIP, 2206);
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        out.write(message.getBytes());
        out.flush();
        byte[] bytes = new byte[1024];
        
        int len = in.read(bytes);
        String s = new String(bytes, 0, len);
//        System.out.println(s);
        Long res = 0l;
        s = s.trim();
        String[] numbers = s.split("\\|");
        for(String n : numbers){
            res += Long.parseLong(n);
        }
//        System.out.println(res);
        String response = res.toString();
        out.write(response.getBytes());
        out.flush();
        
        out.close();
        in.close();
        socket.close();
        
    }
}
