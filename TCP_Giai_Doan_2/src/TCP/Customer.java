/*
[Mã câu hỏi (qCode): NWBm2ECz].  Thông tin khách hàng cần thay đổi định dạng lại cho phù hợp với khu vực, cụ thể:
a.	Tên khách hàng cần được chuẩn hóa theo định dạng mới. Ví dụ: nguyen van hai duong -> DUONG, Nguyen Van Hai
b.	Ngày sinh của khách hàng hiện đang ở dạng mm-dd-yyyy, cần được chuyển thành định dạng dd/mm/yyyy. Ví dụ: 10-11-2012 -> 11/10/2012
c.	Tài khoản khách hàng là các chữ cái in thường được sinh tự động từ họ tên khách hàng. Ví dụ: nguyen van hai duong -> nvhduong

Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2209 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). 
Yêu cầu là xây dựng một chương trình client tương tác với server sử dụng các luồng đối tượng (ObjectInputStream / ObjectOutputStream) 
thực hiện gửi/nhận đối tượng khách hàng và chuẩn hóa. Cụ thể:
a.	Đối tượng trao đổi là thể hiện của lớp Customer được mô tả như sau
      •	Tên đầy đủ của lớp: TCP.Customer
      •	Các thuộc tính: id int, code String, name String, dayOfBirth String, userName String
      •	Hàm khởi tạo đầy đủ các thuộc tính được liệt kê ở trên
      •	Trường dữ liệu: private static final long serialVersionUID = 20170711L; 
b.	Tương tác với server theo kịch bản dưới đây:
	1) Gửi đối tượng là một chuỗi gồm mã sinh viên và mã câu hỏi ở định dạng "studentCode;qCode". Ví dụ: "B15DCCN999;F2DA54F3"
	2) Nhận một đối tượng là thể hiện của lớp Customer từ server với các thông tin đã được thiết lập
	3) Thay đổi định dạng theo các yêu cầu ở trên và gán vào các thuộc tính tương ứng.  Gửi đối tượng đã được sửa đổi lên server
	4) Đóng socket và kết thúc chương trình.
 */
package TCP;

import java.util.*;
import java.io.*;
import java.net.*;

public class Customer  implements Serializable {
    private static final long serialVersionUID = 20170711L;
    private int id;
    private String code;
    private String name;
    private String dayOfBirth;
    private String userName;

    public Customer(int id, String code, String name, String dayOfBirth, String userName) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.dayOfBirth = dayOfBirth;
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(String dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", code=" + code + ", name=" + name + ", dayOfBirth=" + dayOfBirth + ", userName=" + userName + '}';
    }

}
