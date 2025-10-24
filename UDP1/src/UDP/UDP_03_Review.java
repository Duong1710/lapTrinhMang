/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP;

import java.io.*;
import java.net.*;
import java.util.*;

public class UDP_03_Review {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket= new DatagramSocket();
        InetAddress sA = InetAddress.getByName("203.162.10.109");
        int sP = 2207;
        String ma = ";B22DCCN169;Z2lSf8ed";
        DatagramPacket dpGui = new DatagramPacket(ma.getBytes(), ma.length(), sA, sP);
        socket.send(dpGui);
        // Nhận
        byte[] buff = new byte[1096];
        DatagramPacket dpNhan = new DatagramPacket(buff, buff.length);
        socket.receive(dpNhan);
        String duLieu = new String(dpNhan.getData());
        System.out.println(duLieu);
        String[] mang = duLieu.trim().split(";");
        String id = mang[0];
        String data = mang[1];
        System.out.println(id);
        System.out.println(data);
        String[] mang2 = data.trim().split(",");
        Set<Integer> se = new TreeSet<>();
        for(String x : mang2){
            se.add(Integer.parseInt(x));
        }
        ArrayList<Integer> chuoi = new ArrayList<>(se);
        int soLonThu2 = chuoi.get(chuoi.size()-2);
        int soBeThu2 = chuoi.get(1);
        String kQua = id+";" + soLonThu2+"," + soBeThu2;
        // Gửi
        DatagramPacket dpGui2 = new DatagramPacket(kQua.getBytes(), kQua.length(), sA, sP);
        socket.send(dpGui2);
        socket.close();
    }
}
