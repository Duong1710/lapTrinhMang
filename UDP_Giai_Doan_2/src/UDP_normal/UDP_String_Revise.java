/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP_normal;

import java.util.*;
import java.io.*;
import java.net.*;
public class UDP_String_Revise {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("203.162.10.109");
        int sP = 2208;
        String ma = ";B22DCCN169;OsrJIjN6";
        DatagramPacket dpGui = new DatagramPacket(ma.getBytes(), ma.length(), sA, sP);
        socket.send(dpGui);
        byte[] buf = new byte[1096];
        DatagramPacket dpNhan = new DatagramPacket(buf, buf.length);
        socket.receive(dpNhan);
        String duLieu = new String(dpNhan.getData());
        System.out.println(duLieu);
        String[] mang = duLieu.trim().split(";");
        String reqId = mang[0];
        String data = mang[1];
        System.out.println(reqId);
        reqId += ";";
        System.out.println(data);
        String[] res = data.trim().split("\\s+");
        for(String x : res){
            reqId += Character.toUpperCase(x.charAt(0)) + x.substring(1, x.length()).toLowerCase() + " ";
        }
        reqId = reqId.substring(0,reqId.length()-1);
        System.out.println(reqId);
        DatagramPacket dpGui2 = new DatagramPacket(reqId.getBytes(), reqId.length(), sA, sP);
        socket.send(dpGui2);
        socket.close();
    }
}
