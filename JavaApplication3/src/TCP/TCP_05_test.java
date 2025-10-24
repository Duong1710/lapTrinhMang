/*
[Mã câu hỏi (qCode): 0KglQf6W].  Một chương trình server hỗ trợ kết nối qua giao thức TCP tại cổng 2206 
(hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). 
Yêu cầu xây dựng chương trình client thực hiện kết nối tới server sử dụng luồng byte dữ liệu (InputStream/OutputStream) để trao đổi thông tin theo thứ tự:
a. Gửi mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode".
Ví dụ: "B16DCCN999;D45EFA12"
b. Nhận dữ liệu từ server là một chuỗi các số nguyên được phân tách bởi ký tự ",".
Ví dụ: "10,5,15,20,25,30,35"
c. Xác định hai số trong dãy có tổng gần nhất với gấp đôi giá trị trung bình của toàn bộ dãy. Gửi thông điệp lên server theo định dạng "num1,num2" (với num1 < num2)
Ví dụ: Với dãy "10,5,15,20,25,30,35", gấp đôi giá trị trung bình là 40, hai số có tổng gần nhất là 15 và 25. Gửi lên server chuỗi "15,25".
d. Đóng kết nối và kết thúc chương trình.

 */
package TCP;

import java.util.*;
import java.io.*;
import java.net.*;

public class TCP_05_test {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("203.162.10.109", 2206);
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();

        String ma = "B22DCCN169;0KglQf6W";
        out.write(ma.getBytes());
        out.flush();

        byte[] buff = new byte[2014];
        int len = in.read(buff);
        String duLieu = new String(buff, 0, len);
//        System.out.println(duLieu);
        String[] daySo = duLieu.trim().split(",");
        double tongTb = 0d;
        ArrayList<Long> dsSo = new ArrayList<>();
        for (String x : daySo) {
            Long res = Long.valueOf(x);
            dsSo.add(res);
            tongTb += res * 1.0;
        }
        tongTb = tongTb / daySo.length;
        tongTb *= 2;
        Long soThuNhat = -1L;
        Long soThuHai = -1L;
        double kCach = 1e8;
//        Collections.sort(dsSo);
        for(Long x : dsSo){
            System.out.println(x);
        }
        for(int i = 0; i < dsSo.size(); i++){
            for(int j = i + 1; j < dsSo.size(); j++){
                double tongHaiSo = dsSo.get(i) * 1.0 + dsSo.get(j) * 1.0;
                double chenhLech = Math.abs(tongTb - tongHaiSo);
                if(kCach > chenhLech){
                    kCach = chenhLech;
                    soThuNhat = dsSo.get(i);
                    soThuHai = dsSo.get(j);
                }
            }
        }
        String ketQuaNop = soThuNhat + "," + soThuHai;
        System.out.println(tongTb);
        System.out.println(ketQuaNop);
        out.write(ketQuaNop.getBytes());
        out.flush();
        
        in.close();
        out.close();
        socket.close();
    }
}
