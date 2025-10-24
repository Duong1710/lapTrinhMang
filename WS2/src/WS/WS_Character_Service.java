/*
[Mã câu hỏi (qCode): PZ9nDU1m].  Một dịch vụ web được định nghĩa và mô tả trong tệp CharacterService.wsdl,
được triển khai trên server tại URL http://<Exam_IP>:8080/JNPWS/CharacterService?wsdl để xử lý các bài toán về chuỗi và ký tự.
Yêu cầu: Viết chương trình tại máy trạm (WS client) để giao tiếp với CharacterService thực hiện các công việc sau:
a. Triệu gọi phương thức requestStringArray với tham số đầu vào là mã sinh viên (studentCode) và mã câu hỏi 
(qCode) để nhận về một danh sách chuỗi (List<String>) từ server.
b. Phân loại các từ trong mảng chuỗi thành các nhóm có cùng số lượng nguyên âm. Tạo một chuỗi cho mỗi nhóm, trong đó liệt kê các từ cách nhau bằng dấu phẩy, và sắp xếp các từ theo thứ tự từ điển trong mỗi nhóm.
c. Triệu gọi phương thức submitCharacterStringArray(String studentCode, String qCode, List<String> data) để gửi danh sách chuỗi kết quả trở lại server, trong đó mỗi phần tử là một nhóm từ với cùng số lượng nguyên âm.
Ví dụ: Nếu danh sách chuỗi nhận được từ phương thức requestCharacter là ["apple", "banana", "pear", "grape", "kiwi"], các nhóm có thể là:
•	Nhóm 2 nguyên âm: "apple, banana"
•	Nhóm 1 nguyên âm: "grape, kiwi, pear"
Danh sách kết quả sẽ là ["apple, banana", "grape, kiwi, pear"], và danh sách này sẽ được gửi lại server qua phương thức submitCharacter.
d. Kết thúc chương trình client.
 */
package WS;

import vn.medianews.*;
import java.util.*;

public class WS_Character_Service {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CharacterService_Service service = new CharacterService_Service();
        CharacterService port = service.getCharacterServicePort();

        String studentCode = "B22DCCN169";
        String qCode = "PZ9nDU1m";

        List<String> requestStringArray = port.requestStringArray(studentCode, qCode);

        Map<Integer, List<String>> groups = new HashMap<>();
        // Tạo các nhóm
        for (String word : requestStringArray) {
            int vowelCount = countVowels(word);
            groups.computeIfAbsent(vowelCount, k -> new ArrayList<>()).add(word);
        }

        List<String> result = new ArrayList<>();
        for (List<String> groupWords : groups.values()) {
            Collections.sort(groupWords);
            String joined = String.join(", ", groupWords);
            result.add(joined);
        }

        port.submitCharacterStringArray(studentCode, qCode, result);

    }

    // Đếm số nguyên âm trong từ
    private static int countVowels(String word) {
        int count = 0;
        String ngAm = "ueoaiUEOAI";
        for (char c : word.toCharArray()) {
            if (ngAm.indexOf(c) != -1) {
                count++;
            }
        }
        return count;
    }
}
