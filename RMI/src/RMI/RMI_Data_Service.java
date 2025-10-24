/*
[Mã câu hỏi (qCode): idbl0SqX].  Một chương trình (tạm gọi là RMI Server) cung cấp giao diện cho phép triệu gọi từ xa để xử lý dữ liệu.
Giao diện từ xa:
public interface DataService extends Remote {
public Object requestData(String studentCode, String qCode) throws RemoteException;
public void submitData(String studentCode, String qCode, Object data) throws RemoteException;
}
Trong đó:
•	Interface DataService được viết trong package RMI.
•	Đối tượng cài đặt giao diện từ xa DataService được đăng ký với RegistryServer với tên là: RMIDataService.
Yêu cầu: Viết chương trình tại máy trạm (RMI client) để thực hiện các công việc sau với dữ liệu nhận được từ RMI Server:
a. Triệu gọi phương thức requestData để nhận một chuỗi các số nguyên.
b. Sử dụng thuật toán sinh tổ hợp kế tiếp để tìm tổ hợp kế tiếp của chuỗi số này theo thứ tự từ điển. Nếu chuỗi 
đã là tổ hợp lớn nhất, trả về tổ hợp nhỏ nhất (sắp xếp lại từ đầu theo thứ tự từ điển).
Ví dụ: Với chuỗi 1, 2, 3 tổ hợp kế tiếp là 1, 3, 2. Nếu chuỗi là 3, 2, 1 (tổ hợp lớn nhất), kết quả sẽ là 1, 2, 3 (tổ hợp nhỏ nhất).
c. Triệu gọi phương thức submitData để gửi chuỗi chứa tổ hợp kế tiếp đã tìm được trở lại server.
d. Kết thúc chương trình client.
 */
package RMI;

import java.util.*;
import java.io.*;
import java.net.*;
import java.math.*;
import java.rmi.*;
import java.rmi.registry.*;

public class RMI_Data_Service {

    public static void main(String[] args) throws Exception {
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        DataService ds = (DataService) rg.lookup("RMIDataService");
        // Lấy dữ liệu
        String ma = "B22DCCN169";
        String qCode = "idbl0SqX";
        String res = (String) ds.requestData(ma, qCode);
        System.out.println(res);
        String[] mang = res.trim().split(", ");
        List<Integer> chuoi = new ArrayList<>();
        for (String x : mang) {
            int a = Integer.parseInt(x);
            System.out.println(a);
            chuoi.add(a);
        }
        int n = chuoi.size();
        String kQua = xuLy(n, chuoi);
        System.out.println(kQua);
        ds.submitData(ma, qCode, kQua);
    }

    public static String xuLy(int n, List<Integer> chuoi) {
        int i = n - 2;
        while (i >= 0 && chuoi.get(i) > chuoi.get(i + 1)) {
            i--;
        }
        String kQua = "";
        if (i < 0) {
            Collections.reverse(chuoi);
        } else {
            int k = n - 1;
            while (chuoi.get(i) >= chuoi.get(k)) {
                k--;
            }
            Collections.swap(chuoi, i, k);
            Collections.reverse(chuoi.subList(i + 1, n));
        }
        for (int x : chuoi) {
            kQua += x + ",";
        }
        kQua = kQua.substring(0,kQua.length()-1);
        return kQua;
    }
}
