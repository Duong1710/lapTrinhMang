/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP;

import java.util.*;
import java.io.*;
import java.net.*;

public class Main {

    public static String chuanHoaTitle(String s) {
        String[] mang = s.trim().split("\\s+");
        String res = "";
        for (String x : mang) {
            res += Character.toUpperCase(x.charAt(0)) + x.substring(1).toLowerCase();
            res += " ";
        }
        res = res.substring(0, res.length() - 1);
        return res;
    }

    public static String chuanHoaAuthor(String s) {
        String[] mang = s.trim().split("\\s+");
        String x = mang[0];
        String res = "";
//        res += Character.toUpperCase(x.charAt(0)) + x.substring(1).toLowerCase();
        res += x.toUpperCase();
        res += ", ";
        for (int i = 1; i < mang.length; i++) {
            res += Character.toUpperCase(mang[i].charAt(0)) + mang[i].substring(1).toLowerCase();
            res += " ";
        }
        res = res.substring(0, res.length() - 1);
        return res;
    }

    public static String chuanHoaISBN(String s) {
        String res = "";
        res += s.substring(0, 3);
        res += "-";
        res += s.substring(3, 4);
        res += "-";
        res += s.substring(4, 6);
        res += "-";
        res += s.substring(6, 12);
        res += "-";
        res += s.substring(12);
        return res;
    }

    public static String chuanHoaPubDate(String s) {
        String[] mang = s.trim().split("-");
        String res = "";
        res += mang[1];
        res += "/" + mang[0];
        return res;
    }

    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("203.162.10.109");
        int sP = 2209;
        String ma = ";B22DCCN169;tg86qlMI";
        DatagramPacket dpGui = new DatagramPacket(ma.getBytes(), ma.length(), sA, sP);
        socket.send(dpGui);
        // Nhận dữ liệu
        byte[] buff = new byte[1096]; // Tạo ra 1 mảng byte để nhận dữ liệu
        DatagramPacket dpNhan = new DatagramPacket(buff, buff.length);
        socket.receive(dpNhan);
        String reqId = new String(dpNhan.getData(), 0, 8);
        System.out.println(reqId);
        // Nhận đối tượng
        ByteArrayInputStream inByte = new ByteArrayInputStream(buff, 8, buff.length - 8);
        ObjectInputStream inObject = new ObjectInputStream(inByte);
        Book sach = (Book) inObject.readObject();
        System.out.println(sach);
        sach.setTitle(chuanHoaTitle(sach.title));
        System.out.println(sach);
        sach.setAuthor(chuanHoaAuthor(sach.author));
        System.out.println(sach);
        sach.setIsbn(chuanHoaISBN(sach.isbn));
        System.out.println(sach);
        sach.setPublishDate(chuanHoaPubDate(sach.publishDate));
        System.out.println(sach);
        // Gửi dữ liệu
        ByteArrayOutputStream outByte = new ByteArrayOutputStream();
        ObjectOutputStream outObject = new ObjectOutputStream(outByte);
        outObject.writeObject(sach);
        outObject.flush();
        byte[] gui = new byte[1096];
        System.arraycopy(reqId.getBytes(), 0, gui, 0, 8);
        System.arraycopy(outByte.toByteArray(), 0, gui, 8, outByte.size());
        DatagramPacket dpGui2 = new DatagramPacket(gui, gui.length, sA, sP);
        socket.send(dpGui2);
        socket.close();
    }
}
