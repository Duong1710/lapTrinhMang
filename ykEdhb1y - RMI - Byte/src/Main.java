import RMI.ByteService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry("203.162.10.109", 1099);
        ByteService byteService = (ByteService) registry.lookup("RMIByteService");
        String studentCode = "B22DCCN613";
        String qCode = "ykEdhb1y";
        byte[] bytes = byteService.requestData(studentCode, qCode);
        int len = bytes.length;
        byte[] result = new byte[len];
        for (int i = 0; i < len; i++) {
            result[i] = (byte) (bytes[i] + len);
        }
        byteService.submitData(studentCode, qCode, result);
    }
}