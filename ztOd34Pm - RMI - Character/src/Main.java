import RMI.CharacterService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry("203.162.10.109", 1099);
        CharacterService characterService = (CharacterService) registry.lookup("RMICharacterService");
        String studentCode = "B22DCCN613";
        String qCode = "ztOd34Pm";
        String words = characterService.requestCharacter(studentCode, qCode);
        Map<Character, Integer> map = new HashMap<>();
        List<Character> strings = new ArrayList<>();
        for (int i = 0; i < words.length(); i++) {
            if (map.containsKey(words.charAt(i))) {
                map.put(words.charAt(i), map.get(words.charAt(i)) + 1);
            } else {
                map.put(words.charAt(i), 1);
                strings.add(words.charAt(i));
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        for (int i = 0; i < strings.size(); i++) {
            char key = strings.get(i);
            int value = map.get(key);

            stringBuilder.append("\"").append(key).append("\": ").append(value);

            if (i < strings.size() - 1) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("}");
        characterService.submitCharacter(studentCode, qCode, stringBuilder.toString());
    }
}