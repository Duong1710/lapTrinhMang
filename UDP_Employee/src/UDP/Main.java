/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP;

import java.util.*;
import java.io.*;
import java.net.*;
import java.math.*;
public class Main {
    public static String chuanHoaTen(String s){
        String[] mang = s.trim().split("\\s+");
        String res = "";
        for(String x : mang){
            res += Character.toUpperCase(x.charAt(0)) + x.substring(1).toLowerCase();
            res += " ";
        }
        res = res.substring(0, res.length()-1);
        return res;
    }
    public static double newSalary(double x, String tmp){
        int soPhanTram = 0;
        int y = Integer.parseInt(tmp);
        while(y != 0){
            soPhanTram += y % 10;
            y /= 10;
        }
        double newLuong = x * (100+soPhanTram) /100;
        newLuong = Math.round(newLuong * 100) / 100.00;
        return newLuong;
    }
    public static String chuanHoanHireDate(String x){
        String[] mang = x.trim().split("-");
        String res = "";
        res += mang[2] + "/" + mang[1] + "/" + mang[0];
        return res;
    }
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("203.162.10.109");
        int sP = 2209;
        String ma = ";B22DCCN169;CWKb2Ltd";
        //Gửi ma
        DatagramPacket dpGui = new DatagramPacket(ma.getBytes(), ma.length(), sA, sP);
        socket.send(dpGui);
        //Nhận duLieu
        byte[] buff = new byte[1096];
        DatagramPacket dpNhan = new DatagramPacket(buff, buff.length);
        socket.receive(dpNhan);
        //Nhận id
        String reqId = new String(dpNhan.getData(),0,8);
        System.out.println(reqId);
        //Nhận data
        ByteArrayInputStream inByte = new ByteArrayInputStream(dpNhan.getData(), 8, dpNhan.getLength()-8);
        ObjectInputStream inObject = new ObjectInputStream(inByte);
        Employee nv = (Employee)inObject.readObject();
        System.out.println(nv);
        nv.setName(chuanHoaTen(nv.name));
        System.out.println(nv);
        String[] mang2 = nv.hireDate.trim().split("-");
        String nam = mang2[0];
        nv.setSalary(newSalary(nv.salary, nam));
        System.out.println(nv);
        nv.setHireDate(chuanHoanHireDate(nv.hireDate));
        System.out.println(nv);
        // Gửi kqua
        // Gửi object đã dc xử lí
        ByteArrayOutputStream outByte = new ByteArrayOutputStream();
        ObjectOutputStream outObject = new ObjectOutputStream(outByte);
        outObject.writeObject(nv); // outByte chứa dữ liệu
        // Lưu đáp án vào mảng byte
        byte[] kQua = new byte[1096];
        System.arraycopy(reqId.getBytes(), 0, kQua, 0, 8);
        System.arraycopy(outByte.toByteArray(), 0, kQua, 8, outByte.size());
        // Tạo packet gửi dữ liệu
        DatagramPacket dpGui2 = new DatagramPacket(kQua, kQua.length, sA, sP);
        socket.send(dpGui2);
        socket.close();
    }
}
