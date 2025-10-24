/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP;

import java.util.*;
import java.io.*;
import java.net.*;

public class onTapDangIO {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("203.162.10.109", 2206);
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        String ma = "B22DCCN169;ZxemzfrW";
        out.write(ma.getBytes());
        out.flush();

        byte[] buff = new byte[4096];
        int len = in.read(buff);
        String duLieu = new String(buff, 0, len);
        System.out.println(duLieu);
        String[] mang = duLieu.trim().split("\\|");
        for (String x : mang) {
            System.out.println(x + " ");
        }
        long tong = 0l;
        for (String x : mang) {
            tong += Long.parseLong(x);
        }
        System.out.println(tong);
        String kQuaNop = Long.toString(tong);
        out.write(kQuaNop.getBytes());
        out.flush();

        in.close();
        out.close();
        socket.close();
    }
}
