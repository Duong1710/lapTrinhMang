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

public class Main {
    public static String tenKH(String s){
        String[] mang = s.trim().split("\\s+");
        List<String> chuoi = new ArrayList<>();
        String kQua = "";
        kQua += mang[mang.length-1].toUpperCase() + ",";
        for(int i = 0; i < mang.length -1; i++){
            String a = Character.toUpperCase(mang[i].charAt(0)) + mang[i].substring(1, mang[i].length()).toLowerCase();
            kQua += " " + a;
        }
        return kQua;
    }
    public static String thayDoiNS(String s){
        String[] mang = s.trim().split("-");
        String ngay = mang[1];
        String thang = mang[0];
        String nam = mang[2];
        String kQua = ngay + "/" + thang + "/" + nam;
        return kQua;
    }
    public static String thayDoiUSN(String s){
        String[] mang = s.trim().split("\\s+");
        String kQua = "";
        for(int i = 0; i < mang.length-1; i++){
            kQua += Character.toLowerCase(mang[i].charAt(0));
        }
        kQua += mang[mang.length-1].toLowerCase();
        return kQua;
    }
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("203.162.10.109", 2209);
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

        String ma = "B22DCCN169;NWBm2ECz";
        out.writeObject(ma);
        out.flush();

        Customer kh = (Customer) in.readObject();
        System.out.println(kh);
        String re = kh.getName();
        kh.setName(tenKH(kh.getName()));
        kh.setDayOfBirth(thayDoiNS(kh.getDayOfBirth()));
        kh.setUserName(thayDoiUSN(re));
        System.out.println(kh);
        out.writeObject(kh);
        out.flush();
        out.close();
        in.close();
        socket.close();
    }
}
