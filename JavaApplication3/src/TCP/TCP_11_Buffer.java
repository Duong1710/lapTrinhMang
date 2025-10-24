/*
[Mã câu hỏi (qCode): 39yHI4W0].  Một chương trình server cho phép kết nối qua giao thức TCP 
tại cổng 2208 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5 giây). Yêu cầu là xây dựng 
một chương trình client tương tác với server sử dụng các luồng ký tự (BufferedReader/BufferedWriter) theo kịch bản sau:
a. Gửi một chuỗi chứa mã sinh viên và mã câu hỏi với định dạng "studentCode;qCode".
Ví dụ: "B15DCCN999;1D08FX21"
b. Nhận từ server một chuỗi chứa nhiều từ, các từ được phân tách bởi khoảng trắng.
Ví dụ: "hello world programming is fun"
c. Thực hiện đảo ngược từ và mã hóa RLE để nén chuỗi ("aabb" nén thành "a2b2"). 
Gửi chuỗi đã được xử lý lên server. Ví dụ: "ol2eh dlrow gnim2argorp si nuf".
d. Đóng kết nối và kết thúc chương trình
 */
package TCP;

import java.util.*;
import java.io.*;
import java.net.*;

public class TCP_11_Buffer {

    public static String bienDoi(String x) {
//        StringBuilder str = new StringBuilder();
//        int i = 0;
//        while(i < x.length()){
//            char c = x.charAt(i);
//            int j = i + 1;
//            while(j < x.length() && x.charAt(j) == c){
//                j++;
//            }
//            int so = j -i ;
//            str.append(c);
//            if(so > 1){
//                str.append(so);
//            }
//            i = j; // tiếp tục tính từ phần tử tại vị trí j 
//        }
//        return str.toString();
        StringBuilder a = new StringBuilder();
        int i = 0;
        while(i < x.length()){
            char c = x.charAt(i);
            int j = i +1;
            while(j < x.length() && x.charAt(j) == c){
                j++;
            }
            int soLanLap = j- i;
            a.append(c);
            if(soLanLap >1){
                a.append(soLanLap);
            }
            i = j;
        }
        return a.toString();
    }

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("203.162.10.109", 2208);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        String ma = "B22DCCN169;39yHI4W0";
        out.write(ma);
        out.newLine();
        out.flush();

        String duLieu = in.readLine();
        System.out.println(duLieu);
        String[] a = duLieu.trim().split("\\s+");
        ArrayList<String> b = new ArrayList<>();
        for (String x : a) {
            StringBuilder x2 = new StringBuilder(x);
            b.add(x2.reverse().toString());
//            System.out.println(x);
//            System.out.println(x2);
        }
        ArrayList<String> finalFour = new ArrayList<>();
        for (String x : b) {

            String kiTuMoi = bienDoi(x);

            System.out.println(kiTuMoi);
            finalFour.add(kiTuMoi);
        }
        String kQuaNop = String.join(" ", finalFour);
        out.write(kQuaNop);
        out.newLine();

        out.flush();
        in.close();
        out.close();
        socket.close();
    }
}
//ZlJgbPQD jsRqXr kaxGH HLNnhFCz OZdFZaONE RapruHhZz HrqFFCV
//ZlJgbPQD
//DQPbgJlZ
//DQPbgJlZ
//
//jsRqXr
//rXqRsj
//
//kaxGH
//HGxak
//
//HLNnhFCz
//zCFhnNLH
//
//OZdFZaONE
//ENOaZFdZO
//
//RapruHhZz
//zZhHurpaR
//
//HrqFFCV
//VCFFqrH
//
//DQPbgJlZ
//rXqRsj
//HGxak
//zCFhnNLH
//ENOaZFdZO
//zZhHurpaR
//2
//VCF2FqrH
