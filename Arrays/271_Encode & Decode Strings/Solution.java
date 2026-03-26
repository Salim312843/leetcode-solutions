import java.util.*;

public class Codec {

    // Encode list of strings to a single string
    public String encode(List<String> strs) {
        StringBuilder encoded = new StringBuilder();

        for (String str : strs) {
            encoded.append(str.length()).append('#').append(str);
        }

        return encoded.toString();
    }

    // Decode single string back to list of strings
    public List<String> decode(String s) {
        List<String> result = new ArrayList<>();
        int i = 0;

        while (i < s.length()) {

            int j = i;
            while (s.charAt(j) != '#') {
                j++;
            }

            int length = Integer.parseInt(s.substring(i, j));

            String str = s.substring(j + 1, j + 1 + length);
            result.add(str);

            i = j + 1 + length;
        }

        return result;
    }
}
