/*
[Mã câu hỏi (qCode): DjGeAPWm].  Một chương trình server cho phép kết nối qua TCP tại cổng 2207 (
hỗ trợ thời gian liên lạc tối đa cho mỗi yêu cầu là 5 giây). Yêu cầu xây dựng chương trình client thực hiện 
giao tiếp với server sử dụng luồng data (DataInputStream/DataOutputStream) để trao đổi thông tin theo thứ tự:
a. Gửi mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode".
Ví dụ: "B10DCCN003;C6D7E8F9"
b. Nhận lần lượt:
•	Một số nguyên k là độ dài đoạn.
•	Chuỗi chứa mảng số nguyên, các phần tử được phân tách bởi dấu phẩy ",".
Ví dụ: Nhận k = 3 và "1,2,3,4,5,6,7,8".
c. Thực hiện chia mảng thành các đoạn có độ dài k và đảo ngược mỗi đoạn, sau đó gửi mảng đã xử lý lên server. 
Ví dụ: Với k = 3 và mảng "1,2,3,4,5,6,7,8", kết quả là "3,2,1,6,5,4,8,7". Gửi chuỗi kết quả "3,2,1,6,5,4,8,7" lên server.
d. Đóng kết nối và kết thúc chương trình
 */
package TCP;

import java.util.*;
import java.io.*;
import java.net.*;
public class onTapDangData {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("203.162.10.109", 2207);
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        String ma = "B22DCCN169;DjGeAPWm";
        out.writeUTF(ma);
        out.flush();
        
        int k = in.readInt();
        String duLieu = in.readUTF();
        System.out.println(k);
        System.out.println(duLieu);
        String[] mang = duLieu.trim().split(",");
        ArrayList<String> b = new ArrayList<>();
        int n = mang.length;
        for(int i = 0; i < n; i += k){
            ArrayList<String> tmp = new ArrayList<>();
            for(int j = 0; j < k; j++){
                if(i + j < n) tmp.add(mang[i+j]);
                else break;
            }
            b.addAll(tmp.reversed());
        }
        String kQua = "";
        for(int i = 0; i < b.size(); i++){
            if(i == b.size() - 1){
                kQua += b.get(i) ;
            }
            else{
                kQua += b.get(i) + ",";
            }
        }
        System.out.println(kQua);
        out.writeUTF(kQua);
        out.flush();
        out.close();
        in.close();
        socket.close();
    }
}
