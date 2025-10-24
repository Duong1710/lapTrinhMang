/*
[Mã câu hỏi (qCode): qA8TnBlP].  Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2208 (hỗ trợ thời gian giao tiếp tối đa 
cho mỗi yêu cầu là 5 giây). 
Yêu cầu là xây dựng một chương trình client tương tác với server sử dụng các luồng ký tự (BufferedReader/BufferedWriter) theo kịch bản sau:
a. Gửi một chuỗi chứa mã sinh viên và mã câu hỏi với định dạng "studentCode;qCode". Ví dụ: "B15DCCN999;C1234567"
b. Nhận từ server một chuỗi chứa nhiều từ, các từ được phân tách bởi khoảng trắng. Ví dụ: "hello world this is a test example"
c. Sắp xếp các từ trong chuỗi theo độ dài, thứ tự xuất hiện. Gửi danh sách các từ theo từng nhóm về 
server theo định dạng: "a, is, this, test, hello, world, example".
d. Đóng kết nối và kết thúc chương trình.
 */
package TCP;

import java.util.*;
import java.io.*;
import java.net.*;
public class onTapDangBuffer {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("203.162.10.109", 2208);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        String ma = "B22DCCN169;qA8TnBlP";
        out.write(ma);
        out.newLine();
        out.flush();
        
        String duLieu = in.readLine();
        System.out.println(duLieu);
        String[] mang = duLieu.trim().split("\\s+");
        Comparator <String> cmp = (o1, o2) -> {
            return Integer.compare(o1.length(), o2.length());
        };
        Arrays.sort(mang, cmp);
        String kQuaNop = String.join(", ", mang);
        System.out.println(kQuaNop);
        out.write(kQuaNop);
        out.newLine();
        out.flush();
        out.close();
        in.close();
        socket.close();
    }
}
