/*
[Mã câu hỏi (qCode): 7GKn3OQY].  Một chương trình server cho phép kết nối qua TCP tại cổng 2207 
(hỗ trợ thời gian liên lạc tối đa cho mỗi yêu cầu là 5 giây). 
Yêu cầu xây dựng chương trình client thực hiện giao tiếp với server 
sử dụng luồng data (DataInputStream/DataOutputStream) để trao đổi thông tin theo thứ tự:
a. Gửi mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode".
Ví dụ: "B10DCCN001;A1B2C3D4"
b. Nhận một số nguyên hệ thập phân từ server. Ví dụ: 255
c. Chuyển đổi số nguyên nhận được sang hai hệ cơ số 8 và 16. Gửi lần lượt chuỗi 
kết quả lên server. Ví dụ: Với số 255 hệ thập phân, kết quả gửi lên sẽ là hai chuỗi "377" và "FF".
d. Đóng kết nối và kết thúc chương trình.
 */
package TCP;

import java.util.*;
import java.io.*;
import java.net.*;

public class TCP_07_Data {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("203.162.10.109", 2207);
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        String ma = "B22DCCN169;7GKn3OQY";
        out.writeUTF(ma);
        out.flush();
//b. Nhận một số nguyên hệ thập phân từ server. Ví dụ: 255 
//=> Dùng readInt()
        int duLieu = in.readInt();
        System.out.println(duLieu);
        String he8 = Integer.toString(duLieu,8);
        String he16 = Integer.toString(duLieu, 16).toUpperCase(); 
        System.out.println(he8+" " + he16);
        String ketQuaNop = he8 + ";" + he16;
        out.writeUTF(ketQuaNop);
        out.flush();
        
        
        in.close();
        out.close();
        socket.close();
    }
}
