/*
 [Mã câu hỏi (qCode): 4Pt2s6Zo].  Một chương trình (tạm gọi là RMI Server) cung cấp giao diện cho phép triệu gọi từ xa để xử lý chuỗi.
Giao diện từ xa:
public interface CharacterService extends Remote {
public String requestCharacter(String studentCode, String qCode) throws RemoteException;
public void submitCharacter(String studentCode, String qCode, String strSubmit) throws RemoteException;
}
Trong đó:
•	Interface CharacterService được viết trong package RMI.
•	Đối tượng cài đặt giao diện từ xa CharacterService được đăng ký với RegistryServer với tên là: RMICharacterService.
Yêu cầu: Viết chương trình tại máy trạm (RMI client) để thực hiện các công việc sau với chuỗi được nhận từ RMI Server:
a. Triệu gọi phương thức requestCharacter để nhận chuỗi ngẫu nhiên từ server với định dạng: "Chuỗi đầu vào".
b. Thực hiện đếm tần số xuất hiện của mỗi ký tự trong chuỗi đầu vào và tạo ra chuỗi kết quả theo 
định dạng <Ký tự><Số lần xuất hiện>, sắp xếp theo thứ tự xuất hiện của các ký tự trong chuỗi.
Ví dụ: Chuỗi đầu vào "AAABBC" -> Kết quả: "A3B2C1".
c. Triệu gọi phương thức submitCharacter để gửi chuỗi kết quả trở lại server.
d. Kết thúc chương trình client.
 */
package RMI;

import java.rmi.*;
import java.util.*;
import java.rmi.registry.*;

public class RMI_Char_Service {

    public static void main(String[] args) throws Exception {
        // Lấy số đăng ký từ server
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        // Tìm kiếm số đã đăng ký
        CharacterService sv = (CharacterService) rg.lookup("RMICharacterService");
        // Gửi mã
        String ma = "B22DCCN169";
        String qCode = "4Pt2s6Zo";
        // Gửi mã lên số đã đăng ký để lấy dữ liệu
        String s = sv.requestCharacter(ma, qCode);
        System.out.println(s);
        // Xử lí dữ liệu
//        String[] mang = s.trim().split("\\s+");
//        String data = "";
//        for(String x : mang){
//            data += x;
//        }
//        System.out.println(data);
        String data = s;
        int[] idx = new int[256];
        for (char x : data.toCharArray()) {
            idx[x]++;
        }
        String kQua = "";
        for (char x : data.toCharArray()) {
            if (idx[x] >= 1) {
                kQua += x;
                kQua += idx[x];
                idx[x] = 0;
            }
        }
        System.out.println(kQua);
        sv.submitCharacter(ma, qCode,  kQua );
    }
}
