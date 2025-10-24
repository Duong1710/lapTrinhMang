/*
[Mã câu hỏi (qCode): DjGeAPWm].  Một chương trình server cho phép kết nối qua TCP tại cổng 2207 
(hỗ trợ thời gian liên lạc tối đa cho mỗi yêu cầu là 5 giây). Yêu cầu xây dựng chương trình client 
thực hiện giao tiếp với server sử dụng luồng data (DataInputStream/DataOutputStream) để trao đổi thông tin theo thứ tự:
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
import java.net.*;
import java.io.*;
public class TCP_08_Revise {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("203.162.10.109", 2207);
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        String ma = "B22DCCN169;DjGeAPWm";
        out.writeUTF(ma);
        out.flush();
        int k = in.readInt();
        String chuoi = in.readUTF();
        System.out.println(k + " " + chuoi);
        String[] mang = chuoi.trim().split(",");
        List<Integer> res = new ArrayList<>();
        List<Integer> res2 = new ArrayList<>();
        for(String x : mang){
            res.add(Integer.parseInt(x));
        }
        int n = res.size();
        for(int i = 0; i < n; i+=k){
            List<Integer> tmp = new ArrayList<>();
            for(int j =0 ; j < k ;j++){
                if(i + j < n){
                    tmp.add(res.get(i+j));
                }
                else break;
            }
            Collections.reverse(tmp);
            res2.addAll(tmp);
        }
        String kQua = "";
        for(int x : res2){
            kQua += x + ",";
        }
        kQua = kQua.substring(0,kQua.length()-1);
        System.out.println(kQua);
        out.writeUTF(kQua);
        out.flush();
        out.close();
        in.close();
        socket.close();
        
    }
}
