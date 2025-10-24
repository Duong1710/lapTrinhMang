/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP;
import java.util.*;
import java.io.*;
import java.net.*;
public class UDP_02_Review {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("203.162.10.109");
        int sP = 2208;
        String ma = ";B22DCCN169;CGApn89b";
        DatagramPacket dpGui = new DatagramPacket(ma.getBytes(), ma.length(), sA, sP);
        socket.send(dpGui);
        // Nhận
        byte[] buff = new byte[1096];
        DatagramPacket dpNhan = new DatagramPacket(buff, buff.length);
        socket.receive(dpNhan);
        String duLieu = new String(dpNhan.getData());
        System.out.println(duLieu);
        String[] mang = duLieu.trim().split(";");
        String reqId = mang[0];
        System.out.println(reqId);
        String data = mang[1];
        System.out.println(data);
        String[] mang2 = data.trim().split("\\s+");
        String data2 = "";
        for(String x : mang2){
            data2 += Character.toUpperCase(x.charAt(0)) + x.substring(1).toLowerCase() + " ";
        }
        data2 = data2.substring(0, data2.length()-1);
        String kQua = reqId +";" + data2;
        DatagramPacket dpGui2 = new DatagramPacket(kQua.getBytes(), kQua.length(), sA, sP);
        socket.send(dpGui2);
        socket.close();
    }
}
