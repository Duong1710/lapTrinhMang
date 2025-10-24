/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package WS_character;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import character.CharacterService_Service;
import character.CharacterService;

/**
 *
 * @author AD
 */
public class WS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CharacterService_Service characterService_Service = new CharacterService_Service();
        CharacterService instance = characterService_Service.getCharacterServicePort();
        
        String studentCode = "B22DCCN613";
        String qCode = "BzAVX4bn";
        
        List<String> requestStringArray = instance.requestStringArray(studentCode, qCode);
        
        Map<Integer, List<String>> groups = new HashMap<>();

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
        
        instance.submitCharacterStringArray(studentCode, qCode, result);
        
    }
    
    // Đếm số nguyên âm trong từ
    private static int countVowels(String word) {
        int count = 0;
        for (char c : word.toLowerCase().toCharArray()) {
            if ("aeiou".indexOf(c) != -1) {
                count++;
            }
        }
        return count;
    }
}
