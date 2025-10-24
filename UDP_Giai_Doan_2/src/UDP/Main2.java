/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP;
import java.util.*;
import java.io.*;
import java.net.*;
public class Main2 {
    public static String thayDoiTen(String s){
        String[] mang = s.trim().split("\\s+");
        String kQua = "";
        kQua += mang[mang.length-1] + " ";
        for(int i = 1; i < mang.length - 1; i++){
            kQua += mang[i] + " ";
        }
        kQua += mang[0];
        return kQua;
    }
    public static int thayDoiSL(int s){
        String a = s +"";
        StringBuilder tmp = new StringBuilder(a);
        tmp.reverse();
        int kQua = Integer.parseInt(tmp.toString());
        return kQua;
    }
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("203.162.10.109");
        int sP = 2209;
        String ma = ";B22DCCN169;xyTpt1ML";
        DatagramPacket dpGui = new DatagramPacket(ma.getBytes(), ma.length(), sA, sP);
        socket.send(dpGui);
        byte[] buf = new byte[1096];
        DatagramPacket dpNhan = new DatagramPacket(buf, buf.length);
        socket.receive(dpNhan);
        // Nhận reqId
        String reqId = new String(dpNhan.getData(),0,8);
        System.out.println(reqId);
        // Nhận đối tượng
        ByteArrayInputStream inByte = new ByteArrayInputStream(dpNhan.getData(),8, dpNhan.getLength()-8);
        ObjectInputStream inObject = new ObjectInputStream(inByte);
        Product sp = (Product)inObject.readObject();
        System.out.println(sp);
        sp.setName(thayDoiTen(sp.getName()));
        sp.setQuantity(thayDoiSL(sp.getQuantity()));
        System.out.println(sp);
        ByteArrayOutputStream outByte = new ByteArrayOutputStream();
        ObjectOutputStream outObject = new ObjectOutputStream(outByte);
        outObject.writeObject(sp); // Viết lên outByte
        outObject.flush();
        byte[] kQua = new byte[1096];
        System.arraycopy(reqId.getBytes(), 0, kQua, 0, 8);
        System.arraycopy(outByte.toByteArray(), 0, kQua, 8, outByte.size());
        DatagramPacket dpGui2 = new DatagramPacket(kQua, kQua.length, sA, sP);
        socket.send(dpGui2);
        socket.close();
    }
}
