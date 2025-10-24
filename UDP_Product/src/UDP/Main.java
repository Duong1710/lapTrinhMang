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
        res += mang[mang.length-1] + " ";
        for(int i = 1; i < mang.length -1;i++){
            res += mang[i] + " ";
        }
        res += mang[0];
        return res;
    }
    public static Integer chuanHoaSoLuong(int x){
        String s = x + "";
        StringBuilder sb = new StringBuilder(s);
        return Integer.parseInt(sb.reverse().toString());
    }
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("203.162.10.109");
        int sP = 2209;
        String ma = ";B22DCCN169;rhlHeMFZ";
        // Gửi
        DatagramPacket dpGui = new DatagramPacket(ma.getBytes(), ma.length(), sA, sP);
        socket.send(dpGui);
        // Nhận
        byte[] buff = new byte[2056];
        DatagramPacket dpNhan = new DatagramPacket(buff, buff.length);
        socket.receive(dpNhan);
        // Lấy reqID
        String reqId = new String(dpNhan.getData(),0,8);
        System.out.println(reqId);
        // Lấy data
        ByteArrayInputStream inByte = new ByteArrayInputStream(dpNhan.getData(), 8, dpNhan.getLength() - 8);
        ObjectInputStream inObject = new ObjectInputStream(inByte);
        Product sp = (Product)inObject.readObject();
        System.out.println(sp);
        sp.setName(chuanHoaTen(sp.name));
        System.out.println(sp);
        sp.setQuantity(chuanHoaSoLuong(sp.quantity));
        System.out.println(sp);
        // Gửi
        ByteArrayOutputStream outByte = new ByteArrayOutputStream();
        ObjectOutputStream outObject = new ObjectOutputStream(outByte);
        outObject.writeObject(sp);
        outObject.flush();
        
        byte[] gui = new byte[1096];
        System.arraycopy(reqId.getBytes(), 0, gui, 0, 8);
        System.arraycopy(outByte.toByteArray(), 0, gui, 8, outByte.size());
        DatagramPacket dpGui2 = new DatagramPacket(gui, gui.length, sA, sP);
        socket.send(dpGui2);
        socket.close();
    }
}
