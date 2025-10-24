package javaapplication3;

import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) throws Exception {
        String host = "203.162.10.109"; int port = 2206;
        String submit = "B22DCCN169;ZxemzfrW\n";          // Đổi MSV nếu cần

        Socket s = new Socket();
        s.connect(new InetSocketAddress(host, port), 5000);
        s.setSoTimeout(5000);

        InputStream in = s.getInputStream();
        OutputStream out = s.getOutputStream();

        // a) gửi "studentCode;qCode"
        out.write(submit.getBytes(StandardCharsets.UTF_8));
        out.flush();

        // b) nhận "a|b|c|..."
        String line = readLine(in);

        // c) tính tổng và gửi lên
        long sum = 0;
        if (line != null && !line.isEmpty()) {
            for (String p : line.split("\\|"))
                if (!p.isBlank()) sum += Long.parseLong(p.trim());
        }
        out.write((sum + "\n").getBytes(StandardCharsets.UTF_8));
        out.flush();

        // d) đóng kết nối (quan trọng để AC)
        in.close(); out.close(); s.close();
    }

    // đọc đến '\n' (bỏ '\r')
    private static String readLine(InputStream in) throws Exception {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        for (int b; (b = in.read()) != -1;) {
            if (b == '\n') break;
            if (b != '\r') buf.write(b);
        }
        return buf.toString(StandardCharsets.UTF_8);
    }
}
