

# 271. Encode and Decode Strings [ level : Medium ]

## Problem Statement

Design an algorithm to encode a list of strings into a single string and decode it back to the original list of strings.

Implement the `encode` and `decode` methods:

- `encode(List<String> strs)` → Encodes a list of strings into a single string.
- `decode(String s)` → Decodes the encoded string back to the original list.

### Note:
- The encoded string should be reversible.
- The solution should handle **any characters**, including special characters.

---

# LeetCode 271. Encode and Decode Strings (Java)

---

## Approach 1: Delimiter-Based Encoding

### Logic
- Join all strings using a special delimiter.
- Split the encoded string using the same delimiter during decoding.
- This works only if the delimiter never appears in the original strings.

```java
import java.util.*;

public class Codec {

    private static final String DELIMITER = "#";

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {

        if (strs.isEmpty())
            return "";

        StringBuilder sb = new StringBuilder();

        for (String str : strs) {
            sb.append(str).append(DELIMITER);
        }

        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {

        if (s.isEmpty())
            return new ArrayList<>();

        return Arrays.asList(s.split(DELIMITER, -1));
    }
}
```

**Time Complexity:** `O(n)` *(Each character is processed once during encoding and decoding.)*  
**Space Complexity:** `O(n)` *(The encoded string and decoded list require linear extra space.)*

> **Note:** This approach is **not reliable** if the delimiter (`#`) appears inside any string.

---

## Approach 2: Escape Character + Delimiter

### Logic
- Escape every occurrence of the delimiter inside each string.
- Join the strings using the delimiter.
- During decoding, distinguish escaped delimiters from actual separators.

```java
import java.util.*;

public class Codec {

    public String encode(List<String> strs) {

        StringBuilder sb = new StringBuilder();

        for (String str : strs) {

            str = str.replace("/", "//");
            str = str.replace("#", "/#");

            sb.append(str).append("#");
        }

        return sb.toString();
    }

    public List<String> decode(String s) {

        List<String> result = new ArrayList<>();
        StringBuilder current = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            if (ch == '/') {
                current.append(s.charAt(++i));
            }
            else if (ch == '#') {
                result.add(current.toString());
                current.setLength(0);
            }
            else {
                current.append(ch);
            }
        }

        return result;
    }
}
```

**Time Complexity:** `O(n)` *(Each character is visited once while encoding and decoding.)*  
**Space Complexity:** `O(n)` *(The encoded string and decoded list require linear extra space.)*

---

## Approach 3: Length Prefix ⭐ Optimal

### Logic
- Prefix every string with its length followed by a separator (`#`).
- During decoding, read the length first.
- Extract exactly that many characters as the original string.
- This works for any character, including `#`.

```java
import java.util.*;

public class Codec {

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {

        StringBuilder sb = new StringBuilder();

        for (String str : strs) {
            sb.append(str.length()).append("#").append(str);
        }

        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {

        List<String> result = new ArrayList<>();

        int i = 0;

        while (i < s.length()) {

            int j = i;

            while (s.charAt(j) != '#') {
                j++;
            }

            int length = Integer.parseInt(s.substring(i, j));

            j++;

            result.add(s.substring(j, j + length));

            i = j + length;
        }

        return result;
    }
}
```

**Time Complexity:** `O(n)` *(Each character is processed exactly once during encoding and decoding.)*  
**Space Complexity:** `O(n)` *(The encoded string and decoded list require linear extra space.)*
