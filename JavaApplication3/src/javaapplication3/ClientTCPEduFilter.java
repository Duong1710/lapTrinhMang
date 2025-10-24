package javaapplication3;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class ClientTCPEduFilter {
    // Thay đổi nếu cần
    private static final String DEFAULT_HOST = "203.162.10.109";
    private static final int DEFAULT_PORT = 2208;
    private static final int SOCKET_TIMEOUT_MS = 5000;

    public static void main(String[] args) {
        String host = (args.length >= 1) ? args[0] : DEFAULT_HOST;
        int port = (args.length >= 2) ? Integer.parseInt(args[1]) : DEFAULT_PORT;

        // TODO: thay studentCode nếu bạn muốn
        String studentCode = "B22DCCN169";
        String qCode = "cZUnU8vc";
        String hello = studentCode + ";" + qCode;

        System.out.println("[*] Connecting to " + host + ":" + port);

        try (Socket socket = new Socket()) {
            // timeout khi đang connect (5s)
            socket.connect(new InetSocketAddress(host, port), SOCKET_TIMEOUT_MS);
            // timeout khi đọc/ghi (5s)
            socket.setSoTimeout(SOCKET_TIMEOUT_MS);

            try (
                BufferedWriter bw = new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8))
            ) {
                // a) Gửi "studentCode;qCode"
                bw.write(hello);
                bw.newLine();
                bw.flush();
                System.out.println("[>] Sent: " + hello);

                // b) Nhận chuỗi danh sách domain (một dòng)
                String domainsLine = br.readLine();
                if (domainsLine == null) {
                    throw new IOException("Server closed the connection unexpectedly.");
                }
                System.out.println("[<] Received domains: " + domainsLine);

                // c) Lọc các domain .edu (không phân biệt hoa/thường), giữ nguyên nguyên bản ghi
                //    Tách theo dấu phẩy, loại bỏ khoảng trắng thừa
                String[] parts = Arrays.stream(domainsLine.split(","))
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .toArray(String[]::new);

                List<String> eduDomains = Arrays.stream(parts)
                        .filter(d -> d.toLowerCase(Locale.ROOT).endsWith(".edu"))
                        .collect(Collectors.toList());

                // Chuẩn bị chuỗi trả lời
                String reply;
                if (eduDomains.isEmpty()) {
                    // Tuỳ server: nếu không có .edu, ta gửi chuỗi rỗng hoặc một token đặc biệt.
                    // Ở đây gửi chuỗi rỗng (vẫn có newline).
                    reply = "";
                } else {
                    reply = String.join(", ", eduDomains);
                }

                // Gửi lên server
                bw.write(reply);
                bw.newLine();
                bw.flush();
                System.out.println("[>] Sent filtered .edu: " + (reply.isEmpty() ? "(empty)" : reply));

                // (nếu server phản hồi kết quả/flag, đọc thêm 1 dòng tuỳ bài)
                String finalResp = br.readLine();
                if (finalResp != null) {
                    System.out.println("[<] Server says: " + finalResp);
                }

                // d) Kết thúc: try-with-resources sẽ tự đóng stream & socket
                System.out.println("[*] Done. Closing.");
            }
        } catch (SocketTimeoutException e) {
            System.err.println("[!] Timeout (quá 5s) khi kết nối/đọc/ghi: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("[!] Lỗi I/O: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("[!] Lỗi khác: " + e.getMessage());
        }
    }
}
