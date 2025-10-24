/*
[Mã câu hỏi (qCode): AXQrZmaV].  Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2209 
(hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5 giây). Yêu cầu là xây dựng một chương trình client
tương tác với server sử dụng các luồng đối tượng (ObjectOutputStream/ObjectInputStream) để gửi/nhận 
và chuẩn hóa thông tin địa chỉ của khách hàng.
Biết rằng lớp TCP.Address có các thuộc tính (id int, code String, addressLine String, city String, postalCode String) 
và trường dữ liệu private static final long serialVersionUID = 20180801L.
a. Gửi đối tượng là một chuỗi gồm mã sinh viên và mã câu hỏi với định dạng "studentCode;qCode". Ví dụ: "B15DCCN999;A1B2C3D4"
b. Nhận một đối tượng là thể hiện của lớp TCP.Address từ server. Thực hiện chuẩn hóa thông tin addressLine bằng cách:
•	Chuẩn hóa addressLine: Viết hoa chữ cái đầu mỗi từ, in thường các chữ còn lại, loại bỏ ký tự đặc biệt và 
khoảng trắng thừa (ví dụ: "123 nguyen!!! van cu" → "123 Nguyen Van Cu") 
•	Chuẩn hóa postalCode: Chỉ giữ lại số và ký tự "-" ví dụ: "123-456"
c. Gửi đối tượng đã được chuẩn hóa thông tin địa chỉ lên server.
d. Đóng kết nối và kết thúc chương trình.
 */
package TCP;

import java.io.*;
import java.util.*;
import java.net.*;
public class Address implements Serializable{
    private static final long serialVersionUID = 20180801L;
    private int id;
    private String code, addressLine, city, postalCode;

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

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "Address{" + "id=" + id + ", code=" + code + ", addressLine=" + addressLine + ", city=" + city + ", postalCode=" + postalCode + '}';
    }
    
}
