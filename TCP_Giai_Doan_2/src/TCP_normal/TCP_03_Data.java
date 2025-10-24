/*
[Mã câu hỏi (qCode): XPdQ94Bh].  Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2207 
(hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5 giây). Yêu cầu sinh viên xây dựng chương trình client 
để tương tác với server, sử dụng các luồng data (DataInputStream và DataOutputStream) để trao đổi thông tin theo thứ tự sau:
a. Gửi mã sinh viên và mã câu hỏi: Chuỗi gồm mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". 
Ví dụ: "B15DCCN999;D68C93F7".
b. Nhận một số nguyên hệ thập phân từ server. Ví dụ: 45
c. Chuyển đổi số nguyên nhận được sang hệ nhị phân và gửi chuỗi kết quả này lại cho server. 
Ví dụ: 45 sẽ thành chuỗi "101101"
d. Đóng kết nối và kết thúc chương trình.
 */
package TCP_normal;
import java.util.*;
import java.io.*;
import java.net.*;
public class TCP_03_Data {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("203.162.10.109", 2207);
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        String ma = "B22DCCN169;XPdQ94Bh";
        out.writeUTF(ma);
        out.flush();
        int n = in.readInt();
        System.out.println(n);
        String kQua = Integer.toString(n,2);
        System.out.println(kQua);
        out.writeUTF(kQua);
        out.flush();
        out.close();
        in.close();
        socket.close();
    }
}
