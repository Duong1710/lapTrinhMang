/*
[Mã câu hỏi (qCode): gtHzIH0f].  Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2208 
(hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu là xây dựng một chương trình client 
tương tác với server sử dụng các luồng byte (BufferedWriter/BufferedReader) theo kịch bản sau: 
a.	Gửi một chuỗi gồm mã sinh viên và mã câu hỏi với định dạng "studentCode;qCode". Ví dụ: "B15DCCN999;BAA62945"
b.	Nhận một chuỗi ngẫu nhiên từ server
Ví dụ: dgUOo ch2k22ldsOo
c.	Liệt kê các ký tự (là chữ hoặc số) xuất hiện nhiều hơn một lần trong chuỗi và số lần xuất hiện của chúng và gửi lên server
Ví dụ: d:2,O:2,o:2,2:3,
d.	Đóng kết nối và kết thúc chương trình.
 */
package TCP_normal;

import java.util.*;
import java.io.*;
import java.net.*;
public class TCP_02_Buffer {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("203.162.10.109", 2208);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        String ma = "B22DCCN169;gtHzIH0f";
        out.write(ma);
        out.newLine();
        out.flush();
        String duLieu = in.readLine();
        System.out.println(duLieu);
        String duLieu2 = "";
        String[] arr = duLieu.trim().split("\\s+");
        for(String x : arr){
            duLieu2 += x;
        }
        System.out.println(duLieu2);
        int[] tanSuat = new int[256];
        for(char x : duLieu2.toCharArray()){
            tanSuat[x]++;
        }
        String kQua = "";
        for(char x : duLieu2.toCharArray()){
            if(tanSuat[x] > 1){
                kQua += x + ":" + tanSuat[x] + ",";
                tanSuat[x] = 0;
            }
        }
        System.out.println(kQua);
        out.write(kQua);
        out.newLine();
        out.flush();
        in.close();
        out.close();
        socket.close();
    }
}
