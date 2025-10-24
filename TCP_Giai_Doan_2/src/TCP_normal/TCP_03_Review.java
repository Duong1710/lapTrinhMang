/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP_normal;

import java.util.*;
import java.io.*;
import java.net.*;
public class TCP_03_Review  {
    public static void main(String[] args) throws Exception {
        // Tạo cổng kết nối
        Socket socket = new Socket("203.162.10.109",2207);
        // Tạo gói kết nối
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        String ma = "B22DCCN169;XPdQ94Bh";
        out.writeUTF(ma);
        out.flush();
        int duLieu = in.readInt();
        System.out.println(duLieu);
        String kQua = Integer.toBinaryString(duLieu);
        System.out.println(kQua);
        out.writeUTF(kQua);
        out.flush();
        out.close();
        in.close();
        socket.close();
    }
}
