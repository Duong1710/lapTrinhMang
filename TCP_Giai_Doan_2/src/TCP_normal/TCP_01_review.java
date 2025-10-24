/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP_normal;
import java.io.*;
import java.net.*;
import java.util.*;
public class TCP_01_review {
    public static void main(String[] args)throws Exception {
        Socket socket= new Socket("203.162.10.109", 2206);
        InputStream in  = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        String ma = "B22DCCN169;swfb37us";
        out.write(ma.getBytes());
        out.flush();
        byte[] duLieu = new byte[1096];
        int len = in.read(duLieu);
        String s = new String(duLieu, 0, len);
        System.out.println(s);
       
        int idx = 1;
        int a = Integer.parseInt(s);
         s += " ";
        while(a != 1){
            if(a % 2 == 0){
                a /= 2;
                s += a +" ";
                idx++;
            }
            else{
                a = a * 3 + 1;
                s += a + " ";
                idx++;
            }
        }
        s = s.substring(0, s.length()-1);
        s +=  "; " + idx;
        System.out.println(s);
        out.write(s.getBytes());
        out.flush();
        out.close();
        in.close();
        socket.close();
    }
}
