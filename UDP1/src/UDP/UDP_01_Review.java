
package UDP;
import java.util.*;
import java.io.*;
import java.net.*;
public class UDP_01_Review {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("203.162.10.109");
        int sP = 2207;
        String ma = ";B22DCCN169;pNlIhlT7";
        // Gửi
        DatagramPacket dpGui = new DatagramPacket(ma.getBytes(), ma.length(), sA, sP);
        socket.send(dpGui);
        // Nhận
        byte[] buff = new byte[1096];
        DatagramPacket dpNhan = new DatagramPacket(buff, buff.length);
        socket.receive(dpNhan);
        String duLieu = new String(dpNhan.getData());
        System.out.println(duLieu);
        String[] mang= duLieu.trim().split(";");
        String data = mang[1];
        String id = mang[0];
        System.out.println(id);
        System.out.println(data);
        String[] day = data.trim().split(",");
        Set<Integer> se = new TreeSet<>();
        for(String x : day){
            se.add(Integer.parseInt(x));
        }
        ArrayList<Integer> chuoi = new ArrayList<>(se);
        int soLonNhat = chuoi.getLast();
        int soBeNhat = chuoi.getFirst();
        System.out.println(soLonNhat + "-" + soBeNhat);
        String kQua = id + ";" + soLonNhat + "," + soBeNhat;
        DatagramPacket dpGui2 = new DatagramPacket(kQua.getBytes(), kQua.length(), sA, sP);
        socket.send(dpGui2);
        socket.close();
    }
}
