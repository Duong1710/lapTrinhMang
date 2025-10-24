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

public class Main {

    public static String xuLiLine(String diaChi2) {
        String diaChi = diaChi2.replaceAll("[^\\p{L}\\p{N}\\s]", " ");
        System.out.println(diaChi);
        String[] mang = diaChi.trim().split("\\s+");
        ArrayList<String> maMoi = new ArrayList<>();
        for(String x : mang){
            StringBuilder a = new StringBuilder();
            for(char e : x.toCharArray()){
                if(Character.isAlphabetic(e) || Character.isDigit(e)){
                    a.append(Character.toLowerCase(e));
                }
            }
            if(a.length() == 0) continue;
            else{
                a.setCharAt(0, Character.toUpperCase(a.charAt(0)));
                maMoi.add(a.toString());
            }
        }
        String kQua = String.join(" ", maMoi);
        return kQua;
    }
    public static String xuLiPostal(String s){
        StringBuilder newOne = new StringBuilder();
        for(char x : s.toCharArray()){
            if(x == '-' || Character.isDigit(x)){
                newOne.append(x);
            }
        }
        return newOne.toString();
    }
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("203.162.10.109", 2209);
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        String ma = "B22DCCN169;AXQrZmaV";

        out.writeObject(ma);
        out.flush();

        Address diaChi = (Address) in.readObject();
        System.out.println(diaChi);
        String addressLineNew = xuLiLine(diaChi.getAddressLine());
        System.out.println(addressLineNew);
        diaChi.setAddressLine(addressLineNew);
        String posTalNew = xuLiPostal(diaChi.getPostalCode());
        diaChi.setPostalCode(posTalNew);
        
        out.writeObject(diaChi);
        out.flush();
        out.close();
        in.close();
        socket.close();
    }
}
