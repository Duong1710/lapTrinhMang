/*
[Mã câu hỏi (qCode): J6BxDJvr].  Một dịch vụ web (hỗ trợ SOAP version 1.1) được định nghĩa 
và mô tả trong tệp ObjectService.wsdl, được triển khai trên server tại 
URL http://<Exam_IP>:8080/JNPWS/ObjectService?wsdl để xử lý các bài toán với đối tượng.
Yêu cầu: Viết chương trình tại máy trạm (WS client) để giao tiếp với ObjectService thực hiện các công việc sau:
a. Triệu gọi phương thức requestListStudent với tham số đầu vào là mã sinh viên (studentCode) 
và mã câu hỏi (qCode) để nhận về một danh sách đối tượng Student từ server. Mỗi đối tượng Student có các thuộc tính:
•	name: kiểu String, đại diện cho tên của sinh viên.
•	score: kiểu float, đại diện cho điểm trung bình của sinh viên.
b. Thực hiện lọc và giữ lại các sinh viên có điểm thuộc nhóm A, D. Biết rằng điểm các mức sau: 
A: điểm từ 8.0 trở lên; B: điểm từ 6.5 đến dưới 8.0; C: điểm từ 5.0 đến dưới 6.5; D: điểm dưới 5.0
c. Triệu gọi phương thức submitListStudent(String studentCode, String qCode, List<Student> students)
để gửi danh sách sinh viên thuộc nhóm A và D. 
d. Kết thúc chương trình client.
 */
package WS;

import java.util.*;
import vn.medianews.*;
public class WS_Object {
   
    public static void main(String[] args) throws Exception {
        ObjectService_Service service = new ObjectService_Service();
        ObjectService port = service.getObjectServicePort();
        String ma = "B22DCCN169";
        String qCode = "J6BxDJvr";
        List<Student> a = port.requestListStudent(ma, qCode);
        List<Student> b = new ArrayList<>();
        for(Student x : a){
            if(x.getScore() >= 8.0 || x.getScore() <= 5.0){
                b.add(x);
            }
        }
        port.submitListStudent(ma, qCode, b);
    }
}
