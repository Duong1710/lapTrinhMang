/*
[Mã câu hỏi (qCode): W85v7mgh].  Một dịch vụ web được định nghĩa và mô tả trong tệp DataService?wsdl, 
được triển khai trên server tại URL http://<Exam_IP>:8080/JNPWS/DataService?wsdl để xử lý các bài toán với dữ liệu nguyên thủy.
Yêu cầu: Viết chương trình tại máy trạm (WS client) để giao tiếp với DataService thực hiện các công việc sau:
a. Triệu gọi phương thức getData với tham số đầu vào là mã sinh viên (studentCode) và mã câu hỏi (qCode)
để nhận về một danh sách số nguyên (List<Integer>) từ server.
b. Chuyển đổi số nguyên nhận được từ hệ thập phân sang hệ nhị phân và biểu diễn kết quả dưới dạng chuỗi nhị phân.
c. Triệu gọi phương thức submitDataStringArray(String studentCode, String qCode, List<String> data) để gửi 
chuỗi nhị phân đã chuyển đổi trở lại server.
Ví dụ: Nếu mỗi số nguyên nhận được từ phương thức getData, chương trình client sẽ chuyển đổi sang chuỗi
nhị phân là "1010", và gửi mảng chuỗi này trở lại server qua phương thức submitData.
d. Kết thúc chương trình client.
 */
package WS;
import java.util.*;
import vn.medianews.*;
public class WS_Data_Service {
    public static void main(String[] args)throws Exception {
        DataService_Service service = new DataService_Service();
        DataService port = service.getDataServicePort();
        String ma = "B22DCCN169";
        String qCode = "W85v7mgh";
        List<Integer> a = port.getData(ma, qCode);
        System.out.println(a);
        List<String> b = new ArrayList<>();
        for(int x : a){
            String s = Integer.toBinaryString(x);
            b.add(s);
        }
        port.submitDataStringArray(ma, qCode, b);
    }
}
