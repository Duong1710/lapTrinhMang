/*
[Mã câu hỏi (qCode): pVd0i6Nt].  Một chương trình (tạm gọi là RMI Server) cung cấp giao diện cho phép triệu
gọi từ xa để xử lý dữ liệu nhị phân.
Giao diện từ xa:
public interface ByteService extends Remote {
public byte[] requestData(String studentCode, String qCode) throws RemoteException;
public void submitData(String studentCode, String qCode, byte[] data) throws RemoteException;
}
Trong đó:
•	Interface ByteService được viết trong package RMI.
•	Đối tượng cài đặt giao diện từ xa ByteService được đăng ký với RegistryServer với tên là: RMIByteService.
Yêu cầu: Viết chương trình tại máy trạm (RMI client) để thực hiện các công việc sau với dữ liệu nhị phân nhận được từ RMI Server:
a. Triệu gọi phương thức requestData để nhận một mảng dữ liệu nhị phân (byte[]) từ server.
b. Chuyển đổi mảng dữ liệu nhị phân nhận được thành một chuỗi biểu diễn hex. Mỗi byte trong mảng sẽ được chuyển đổi 
thành hai ký tự hex tương ứng.
Ví dụ: Nếu dữ liệu nhị phân nhận được là [72, 101, 108, 108, 111], chương trình sẽ chuyển đổi mảng này thành 
chuỗi hex "48656c6c6f", tương ứng với chuỗi "Hello" trong ASCII.
c. Triệu gọi phương thức submitData để gửi chuỗi biểu diễn hex đã chuyển đổi thành mảng byte trở lại server.
d. Kết thúc chương trình client.
 */
package RMI;
import java.util.*;
import java.rmi.*;
import java.rmi.registry.*;
public class RMI_Byte_Service {
    public static void main(String[] args) throws Exception {
        // Lấy số đăng kí từ web service
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        // Lấy Interface
        ByteService bs = (ByteService)rg.lookup("RMIByteService");
        // Lấy dữ liệu
        String ma = "B22DCCN169";
        String qCode = "pVd0i6Nt";
        byte[] buf = bs.requestData(ma, qCode);
        String duLieu = new String(buf);
        String res = solve(duLieu);
        bs.submitData(ma, qCode, res.getBytes());
        
    }
//    public static String solve(String s){
//        int len = s.length();
//        String res = "";
//        for(int i=0;i<len;i++){
//            int n = s.charAt(i);
//            String a = Integer.toHexString(n);
//            System.out.println(a);
//            res+= Integer.toHexString(n);
//        }
//        System.out.println(res);
//        return res;
//    }
        public static String solve(String s){
            String res = "";
            for(char x : s.toCharArray()){
                int n = x;
                res += Integer.toHexString(n);
            }
            return res;
        }
}
