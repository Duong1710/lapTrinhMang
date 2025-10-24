/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP_normal;

import java.io.*;
import java.net.*;
import java.util.*;

public class TCP_02_Review {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("203.162.10.109", 2208);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        String ma = "B22DCCN169;gtHzIH0f";
        out.write(ma);
        out.newLine();
        out.flush();
        String duLieu = in.readLine();
        System.out.println(duLieu);
        String[] mang = duLieu.trim().split("\\s+");
        String duLieu2 = "";
        String kQua = "";
        for (String x : mang) {
            duLieu2 += x;
        }
        int[] idx = new int[256];
        for (char x : duLieu2.toCharArray()) {
            idx[x]++;
        }
        for (char x : duLieu2.toCharArray()) {
            if (idx[x] > 1) {
                kQua += x + ":" + idx[x] + ",";
                idx[x] = 0;
            }
        }
        System.out.println(kQua);
        out.write(kQua);
        out.flush();
        in.close();
        out.close();
        socket.close();

    }
}
