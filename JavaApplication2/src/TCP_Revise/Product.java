/*
[Mã câu hỏi (qCode): QWSZ93D4].  Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2209 
(hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5 giây). Yêu cầu là xây dựng một 
chương trình client tương tác với server sử dụng các luồng đối tượng (ObjectOutputStream/ObjectInputStream) theo kịch bản dưới đây:
Biết lớp TCP.Product gồm các thuộc tính (id int, name String, price double, int discount)
và private static final long serialVersionUID = 20231107;
a. Gửi đối tượng là một chuỗi gồm mã sinh viên và mã câu hỏi với định dạng "studentCode;qCode". Ví dụ: "B15DCCN999;1E08CA31"
b. Nhận một đối tượng là thể hiện của lớp TCP.Product từ server.
c. Tính toán giá trị giảm giá theo price theo nguyên tắc: Giá trị giảm giá (discount) bằng tổng 
các chữ số trong phần nguyên của giá sản phẩm (price). Thực hiện gán giá trị cho thuộc tính discount 
và gửi lên đối tượng nhận được lên server.
d. Đóng kết nối và kết thúc chương trình.
 */
package TCP_Revise;

import java.io.*;
public class Product implements Serializable{
    private int id;
    private String name;
    private double price;
    private int discount;
    private static final long serialVersionUID = 20231107;

    public Product(int id, String name, double price, int discount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discount = discount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", price=" + price + ", discount=" + discount + '}';
    }
    
}
