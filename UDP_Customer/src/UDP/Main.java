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
        String res = "";
        res += mang[mang.length-1].toUpperCase() + ", ";
        for(int i = 0; i < mang.length -1; i++){
            res += Character.toUpperCase(mang[i].charAt(0)) + mang[i].substring(1).toLowerCase() + " ";
        }
        res = res.substring(0, res.length()-1);
        return res;
    }
    public static String chuanHoaNgay(String s){
        String mang[] = s.trim().split("-");
        String res = mang[1] +"/" + mang[0] + "/" + mang[2];
        return res;
    }
    public static String chuanHoaTenNguoiDung(String s){
        String[] mang = s.trim().split("\\s+");
        String res = "";
        for(int i = 0; i < mang.length - 1;i++){
            res += Character.toLowerCase(mang[i].charAt(0));
        }
        res += mang[mang.length-1].toLowerCase();
        return res;
    }
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("203.162.10.109");
        int sP = 2209;
        String ma = ";B22DCCN169;LAwRrcVz";
        DatagramPacket dpGui = new DatagramPacket(ma.getBytes(), ma.length(), sA, sP);
        socket.send(dpGui);
        // Nhận
        byte[] buff = new byte[1096];
        DatagramPacket dpNhan = new DatagramPacket(buff, buff.length);
        socket.receive(dpNhan);
        String reqId = new String(dpNhan.getData(),0,8);
        System.out.println(reqId);
        // Nhận data
        ByteArrayInputStream inByte = new ByteArrayInputStream(dpNhan.getData(), 8, dpNhan.getLength()-8);
        ObjectInputStream inObject = new ObjectInputStream(inByte);
        Customer kh = (Customer)inObject.readObject();
        String namek = kh.getName();
        System.out.println(kh);
        kh.setName(chuanHoaTen(kh.name));
        System.out.println(kh);
        kh.setDayOfBirth(chuanHoaNgay(kh.dayOfBirth));
        System.out.println(kh);
        kh.setUserName(chuanHoaTenNguoiDung(namek));
        System.out.println(kh);
        //Gửi
        ByteArrayOutputStream outByte = new ByteArrayOutputStream();
        ObjectOutputStream outObject = new ObjectOutputStream(outByte);
        outObject.writeObject(kh);
        byte[] kQua = new byte[1096];
        System.arraycopy(reqId.getBytes(), 0, kQua, 0, 8);
        System.arraycopy(outByte.toByteArray(), 0, kQua, 8, outByte.size());
        DatagramPacket dpGui2 = new DatagramPacket(kQua, kQua.length, sA, sP);
        socket.send(dpGui2);
        socket.close();
    }
}
