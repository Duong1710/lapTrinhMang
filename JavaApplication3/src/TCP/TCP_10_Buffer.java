/*
[Mã câu hỏi (qCode): qA8TnBlP].  Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2208 
(hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5 giây). 
Yêu cầu là xây dựng một chương trình client tương tác với server sử dụng các luồng ký tự (BufferedReader/BufferedWriter) theo kịch bản sau:
a. Gửi một chuỗi chứa mã sinh viên và mã câu hỏi với định dạng "studentCode;qCode". Ví dụ: "B15DCCN999;C1234567"
b. Nhận từ server một chuỗi chứa nhiều từ, các từ được phân tách bởi khoảng trắng. Ví dụ: "hello world this is a test example"
c. Sắp xếp các từ trong chuỗi theo độ dài, thứ tự xuất hiện. Gửi danh sách các từ 
theo từng nhóm về server theo định dạng: "a, is, this, test, hello, world, example".
d. Đóng kết nối và kết thúc chương trình.
 */
package TCP;

import java.util.*;
import java.io.*;
import java.net.*;

public class TCP_10_Buffer {

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
        String[] a = duLieu.trim().split("\\s+");
        ArrayList<String> b = new ArrayList<>();
        for (String x : a) {
            b.add(x);
            System.out.println(x);
        }
//        Comparator<String> cmp = (o1, o2) -> {
//            if(o1.length() < o2.length()) return 1;
//            else return 0;
        ////            return Integer.compare(o1.length(), o2.length());
//        };
        Comparator<String> cmp = (o1, o2) -> {
//            if(o1.length() < o2.length()) return -1; // -1 thì o1 đứng sau 02
//            else if(o1.length() > o2.length()) return 1;
//            else return 0;
            return Integer.compare(o1.length(), o2.length()); // sắp xếp tăng dần
        };
        b.sort(cmp);
//        b.sort(String.CASE_INSENSITIVE_ORDER);
        String kQuaNop = String.join(", ", b);
        System.out.println(kQuaNop);
        out.write(kQuaNop);
        out.newLine();
        out.flush();

        in.close();
        out.close();
        socket.close();
    }
}
