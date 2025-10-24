/*
[Mã câu hỏi (qCode): kMqmrJGZ].  Một chương trình server hỗ trợ kết nối qua giao thức TCP tại cổng 2206 
(hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu xây dựng chương trình client
thực hiện kết nối tới server sử dụng luồng byte dữ liệu (InputStream/OutputStream) 
để trao đổi thông tin theo thứ tự:
a. Gửi mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode".
Ví dụ: "B16DCCN999;C89DAB45"
b. Nhận dữ liệu từ server là một chuỗi các số nguyên được phân tách bởi ký tự ",".
Ví dụ: "8,4,2,10,5,6,1,3"
c. Tính tổng của tất cả các số nguyên tố trong chuỗi và gửi kết quả lên server.
Ví dụ: Với dãy "8,4,2,10,5,6,1,3", các số nguyên tố là 2, 5, 3, tổng là 10. Gửi lên server chuỗi "10".
d. Đóng kết nối và kết thúc chương trình.
 */
package TCP;

import java.util.*;
import java.net.*;
import java.io.*;
public class TCP_04_test {
    public static boolean isPrime(long n){
        if(n <2) return false;
        for(long i = 2 ; i <= Math.sqrt(n); i++){
            if(n % i == 0) return false;
        }
        return true;
    }
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("203.162.10.109", 2206);
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        
        String ma = "B22DCCN169;kMqmrJGZ";
        out.write(ma.getBytes());
        out.flush();
        
        byte[] duLieu = new byte[1024];
        int len = in.read(duLieu);
        String str = new String(duLieu, 0 , len);
        System.out.println(str);
        
        String[] dsSo = str.trim().split(",");
//        for(String x : dsSo){
//            System.out.print(x + " ");
//        }
        Integer res = 0;
        for(String x : dsSo){
            int kqua = Integer.parseInt(x);
            if(isPrime(kqua)){
                res += kqua;
            }
        }
        System.out.println(res);
        out.write(res.toString().getBytes());
        out.flush();
        in.close();
        out.close();
        socket.close();
    }
}
