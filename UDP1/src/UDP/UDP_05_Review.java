/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP;

import java.util.*;
import java.io.*;
import java.math.BigInteger;
import java.net.*;
public class UDP_05_Review {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("203.162.10.109");
        int sP = 2208;
        String ma = ";B22DCCN169;hVs8U8Gi";
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
        Arrays.sort(mang2,new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.toLowerCase().compareTo(o1.toLowerCase());
            }
        });
        String kQua = reqId + ";";
        for(String x : mang2){
            kQua += x + ",";
        }
        kQua = kQua.substring(0, kQua.length()-1);
//        String soA= mang[1];
//        String soB = mang[2];
//        System.out.println(reqId);
//        System.out.println(soA);
//        System.out.println(soB);
//        BigInteger a = new BigInteger(soA);
//        BigInteger b = new BigInteger(soB);
//        System.out.println(a);
//        System.out.println(b);
//        BigInteger tong = a.add(b);
//        BigInteger hieu = a.subtract(b);
//        String kQua = reqId + ";" + tong.toString() + "," + hieu.toString();
        DatagramPacket dpGui2 = new DatagramPacket(kQua.getBytes(), kQua.length(), sA, sP);
        socket.send(dpGui2);
        socket.close();
    }
}
