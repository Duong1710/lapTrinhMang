/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP;

import java.util.*;
import java.io.*;
import java.net.*;
public class Main {
    public static String chuanHoaTen(String s){
        String[] mang = s.trim().split("\\s+");
        String tenMoi = "";
        for(String x : mang){
            tenMoi += Character.toUpperCase(x.charAt(0)) + x.substring(1).toLowerCase();
            tenMoi += " ";
        }
        tenMoi = tenMoi.substring(0, tenMoi.length()-1);
        return tenMoi;
    }
    public static String chuanHoaEmail(String s){
        String email = "";
        String[] mang = s.trim().split("\\s+");
        email += mang[mang.length - 1].toLowerCase();
        for(int i = 0 ; i < mang.length-1; i++){
            email += Character.toLowerCase(mang[i].charAt(0));
        }
        email += "@ptit.edu.vn";
        return email;
    }
    public static void main(String[] args)throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("203.162.10.109");
        int sP = 2209;
        String ma = ";B22DCCN169;qFJkqFwA";
        DatagramPacket dpGui = new DatagramPacket(ma.getBytes(), ma.length(), sA, sP);
        socket.send(dpGui);
        
        // Nhận dữ liệu
        byte[] buff = new byte[2000];
        DatagramPacket dpNhan = new DatagramPacket(buff, buff.length);
        socket.receive(dpNhan);
        // RequestId
        String reqId = new String(dpNhan.getData(),0,8);
        System.out.println(reqId);
        // Xử lí chuỗi byte còn lại
        ByteArrayInputStream inByte = new ByteArrayInputStream(dpNhan.getData(), 8, dpNhan.getLength()-8);
        ObjectInputStream inObject = new ObjectInputStream(inByte); // ép kiểu mảng byte -> Object
        Student sv = (Student)inObject.readObject(); // Gán giá trị cho sv
        System.out.println(sv);
        System.out.println(sv.name + "-" + sv.email);
        sv.name = chuanHoaTen(sv.name);
        sv.email = chuanHoaEmail(sv.name);
        System.out.println(sv.name + "-" + sv.email);
        // Gửi dữ liệu
        ByteArrayOutputStream outByte = new ByteArrayOutputStream(); // Tạo mảng byte để gửi
        ObjectOutputStream outObject = new ObjectOutputStream(outByte);
        outObject.writeObject(sv); // Điền thông tin cho mảng byte
        outObject.flush();
        byte[] gui = new byte[8 + outByte.size()];
        System.arraycopy(reqId.getBytes(), 0, gui, 0, 8);
        System.arraycopy(outByte.toByteArray(), 0, gui, 8, outByte.size());
        DatagramPacket dpGui2 = new DatagramPacket(gui, gui.length, sA, sP);
        socket.send(dpGui2);
        socket.close();
    }
}
