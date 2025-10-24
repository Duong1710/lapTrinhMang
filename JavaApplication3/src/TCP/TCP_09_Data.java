/*
 [Mã câu hỏi (qCode): r9MwsfZt].  Một chương trình server cho phép kết nối qua TCP tại cổng 2207 
(hỗ trợ thời gian liên lạc tối đa cho mỗi yêu cầu là 5 giây). Yêu cầu xây dựng chương trình client thực
hiện giao tiếp với server sử dụng luồng data (DataInputStream/DataOutputStream) để trao đổi thông tin theo thứ tự:
a. Gửi mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode".
Ví dụ: "B10DCCN002;B4C5D6E7"
b. Nhận chuỗi chứa mảng số nguyên từ server, các phần tử được phân tách bởi dấu phẩy ",". Ví dụ: "1,3,2,5,4,7,6"
c. Tính số lần đổi chiều và tổng độ biến thiên trong dãy số.
- Đổi chiều: Khi dãy chuyển từ tăng sang giảm hoặc từ giảm sang tăng 
-   Độ biến thiên: Tổng giá trị tuyệt đối của các hiệu số liên tiếp
Gửi lần lượt lên server: số nguyên đại diện cho số lần đổi chiều, sau đó là số nguyên đại diện cho
tổng độ biến thiên. Ví dụ: Với mảng "1,3,2,5,4,7,6", số lần đổi chiều: 5 lần, Tổng độ biến thiên 11 
-> Gửi lần lượt số nguyên 5 và 11 lên server.
d. Đóng kết nối và kết thúc chương trình.
 */
package TCP;

import java.util.*;
import java.io.*;
import java.net.*;
public class TCP_09_Data {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("203.162.10.109", 2207);
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        String ma = "B22DCCN169;r9MwsfZt";
        out.writeUTF(ma);
        out.flush();
        
        String duLieu = in.readUTF();
        System.out.println(duLieu);
        String[] daySo = duLieu.trim().split(",");
        int tong = 0;
        int soLanDoiChieu = 0;
        ArrayList<Integer> res = new ArrayList<>();
        for (String daySo1 : daySo) {
            res.add(Integer.valueOf(daySo1));
        }
        for(int i = 1; i < res.size(); i++){
            int kqua = Math.abs(res.get(i) - res.get(i-1)) ;
            tong += kqua;
        }
        for(int i = 1; i < res.size() - 1 ; i++){
            if( (res.get(i) > res.get(i-1)) && (res.get(i) > res.get(i+1))){
                soLanDoiChieu += 1;
            }
            else if ( (res.get(i) < res.get(i-1)) && (res.get(i)< res.get(i+1))){
                soLanDoiChieu += 1;
            }
        }
        System.out.println(soLanDoiChieu +" " + tong);
        out.writeInt(soLanDoiChieu);
        out.flush();
        out.writeInt(tong);
        out.flush();
        
        in.close();
        out.close();
        socket.close();
    }
}
