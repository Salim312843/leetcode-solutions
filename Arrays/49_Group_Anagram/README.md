# H1 Q) Given an array of strings strs, group the anagrams together. You can return the answer in any order. 

-What Is Given? = *An array of strings*
So input type is:
**String[]**

-*Group the anagrams together*
It will be:
**List<List<String>>**
                    That means:
                     A list
                     Inside it, multiple lists
                     Each inner list is one group

-We are asked to:
*Organize words into groups*
                We are not asked to check if two strings are anagrams/ return true or false...

-*You can return the answer in any order*
     **That means sorting output is not required.**

# H2 Understanding Constraints
- 1 <= strs.length <= 104   :  **Avoid O(n²)**
- 0 <= strs[i].length <= 100  : **Sorting is fine** [Sorting one word costs:O(k log k). where k ≤ 100. Since 100 is small]
- strs[i] consists of lowercase English letters.  : **Frequency array of size 26 works** [Only 26 possible characters]


# H2 HashMap + Sorting Approach
In this approach, the main idea is to use sorting to identify anagrams and HashMap to group them.

Two strings are anagrams if they contain the same characters with the same frequency. When the characters of an anagram strings are sorted alphabetically, they produce the same sorted string.
For example:
eat → aet
tea → aet
ate → aet
Since the sorted result is identical, these words belong to the same group

**Working Principle**
1)Traverse each string in the given array.
2)Convert the string into a character array.
3)Sort the character array.
4)Convert the sorted array back into a string.
5)Use this sorted string as a key in a HashMap.
6)Store the original string in the list corresponding to that key.
7)After processing all strings, return all values of the HashMap


# H2 [Optimized Approach]: HashMap + Character Frequency Count
This approach is based on the observation that anagrams contain the same characters with identical frequencies. Therefore, if two strings have the same frequency distribution of characters, they must belong to the same anagram group.

**Working Principle**
1)Anagrams have the same characters with the same frequency.
2)Create a frequency array of size 26 for each string.
3)Count occurrences of each character (a–z) in the string.
4)Convert the frequency array into a unique key string.
5)Use a HashMap where key = frequency pattern.
6)Store all strings with the same key in the same list.
7)Finally, return all the lists from the HashMap as grouped anagrams.



# LeetCode 49. Group Anagrams (Java)

---

## Approach 1: Brute Force

```java
import java.util.*;

class Solution {

    public List<List<String>> groupAnagrams(String[] strs) {

        List<List<String>> result = new ArrayList<>();
        boolean[] visited = new boolean[strs.length];

        for (int i = 0; i < strs.length; i++) {

            if (visited[i]) {
                continue;
            }

            List<String> group = new ArrayList<>();
            group.add(strs[i]);
            visited[i] = true;

            for (int j = i + 1; j < strs.length; j++) {

                if (!visited[j] && isAnagram(strs[i], strs[j])) {
                    group.add(strs[j]);
                    visited[j] = true;
                }
            }

            result.add(group);
        }

        return result;
    }

    private boolean isAnagram(String s, String t) {

        if (s.length() != t.length()) {
            return false;
        }

        int[] count = new int[26];

        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }

        for (int value : count) {
            if (value != 0) {
                return false;
            }
        }

        return true;
    }
}
```

**Time Complexity:** `O(n² × k)` *(Each string may be compared with every other string, and each comparison takes O(k).)*  
**Space Complexity:** `O(n)` *(Visited array and result list require additional space.)*

---

## Approach 2: Sorting + HashMap

```java
import java.util.*;

class Solution {

    public List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {

            char[] chars = str.toCharArray();
            Arrays.sort(chars);

            String key = new String(chars);

            map.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }

        return new ArrayList<>(map.values());
    }
}
```

**Time Complexity:** `O(n × k log k)` *(Each string is sorted individually before inserting into the HashMap.)*  
**Space Complexity:** `O(n × k)` *(The HashMap stores all strings and their sorted keys.)*

---

## Approach 3: Frequency Count (Canonical Signature) + HashMap ⭐ Optimal

```java
import java.util.*;

class Solution {

    public List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {

            int[] count = new int[26];

            for (char c : str.toCharArray()) {
                count[c - 'a']++;
            }

            StringBuilder key = new StringBuilder();

            for (int freq : count) {
                key.append('#').append(freq);
            }

            map.computeIfAbsent(key.toString(), k -> new ArrayList<>()).add(str);
        }

        return new ArrayList<>(map.values());
    }
}
```

**Time Complexity:** `O(n × k)` *(Each string is scanned once to build its frequency signature.)*  
**Space Complexity:** `O(n × k)` *(The HashMap stores all strings along with their generated signatures.)*
