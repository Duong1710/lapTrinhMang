/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP_normal;

import java.util.*;
import java.io.*;
import java.net.*;
public class UDP_Data_Revise {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("203.162.10.109");
        int sP = 2207;
        String ma = ";B22DCCN169;0AvIlNtH";
        DatagramPacket dpGui = new DatagramPacket(ma.getBytes(), ma.length(), sA, sP);
        socket.send(dpGui);
        byte[] buf = new byte[1096];
        DatagramPacket dpNhan = new DatagramPacket(buf, buf.length);
        socket.receive(dpNhan);
        String duLieu = new String(dpNhan.getData());
        System.out.println(duLieu);
        String[] mang = duLieu.trim().split(";");
        String reqId = mang[0];
        int n = Integer.parseInt(mang[1]);
        int k = Integer.parseInt(mang[2]);
        String chuoiSo = mang[3];
        System.out.println(reqId);
        reqId += ";";
        System.out.println(n);
        System.out.println(k);
        System.out.println(chuoiSo);
        String[] mang2 = chuoiSo.trim().split(",");
        List<Integer> res = new ArrayList<>();
        for(String x : mang2){
            res.add(Integer.parseInt(x));
        }
        for(int i = 0; i < n ; i++){
            if(i + k > n) break;
            List<Integer> tmp = new ArrayList<>();
            for(int j = 0; j < k ; j++){
                tmp.add(res.get(i+j));
            }
            int a = Collections.max(tmp);
            reqId += a + ",";
        }
        reqId = reqId.substring(0,reqId.length()-1);
        System.out.println(reqId);
        DatagramPacket dpGui2 = new DatagramPacket(reqId.getBytes(), reqId.length(), sA, sP);
        socket.send(dpGui2);
        socket.close();
    }
}
