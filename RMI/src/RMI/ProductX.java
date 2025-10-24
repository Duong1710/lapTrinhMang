/*
[Mã câu hỏi (qCode): 3BIRpvK1].  Một chương trình (tạm gọi là RMI Server) cung cấp các mã khuyến mãi 
sản phẩm ngẫu nhiên cho sinh viên, được mô tả như sau:
•	Giao diện từ xa
    public interface ObjectService extends Remote {
        public Serializable requestObject(String studentCode, String qAlias) throws RemoteException;

        public void submitObject(String studentCode, String qAlias, Serializable object) throws RemoteException;
    }
•	Lớp ProductX gồm các thuộc tính id String, code String, discountCode String, discount int.
o	Một hàm khởi dựng với đầy đủ các thuộc tính liệt kê ở trên
o	Trường dữ liệu: private static final long serialVersionUID = 20171107; 
•	Đối tượng triệu gọi từ xa được đăng ký RegistryServer với tên: RMIObjectService
•	Tất cả các lớp được viết trong package RMI

Yêu cầu là xây dựng một chương trình client thực hiện các tương tác với hệ thống phần mềm ở trên theo kịch bản dưới đây:
1.	  Triệu gọi phương thức từ xa requestObject từ RMI Server với tham số đầu vào là mã sinh viên,
mã câu để nhận về đối tượng ProductX
2.    Nhận về đối tượng ProductX từ RMI Server với giá trị ban đầu đã được thiết lập. Tính tổng các chữ số nằm 
trong chuỗi mã giảm giá (discountCode) để ra giá trị được khuyến mãi của sản phẩm và cập nhật giá trị của khuyến mãi (discount)
3.	Triệu gọi phương thức từ xa submitObject với tham số đầu vào là đối tượng Product đã được cập nhật đầy đủ 
thông tin giá trị khuyến mãi
4.	Kết thúc chương trình
 */
package RMI;

import java.io.Serializable;
import java.rmi.*;
import java.util.*;
public class ProductX implements Serializable{
     String id, code, discountCode;
     int discount;
    private static final long serialVersionUID = 20171107;

    public ProductX(String id, String code, String discountCode, int discount) {
        this.id = id;
        this.code = code;
        this.discountCode = discountCode;
        this.discount = discount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "ProductX{" + "id=" + id + ", code=" + code + ", discountCode=" + discountCode + ", discount=" + discount + '}';
    }
    
}
