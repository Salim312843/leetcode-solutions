
## Problem Statement

Given an array of strings `strs`, group the **anagrams** together. You can return the answer in **any order**.

An **anagram** is a word or phrase formed by rearranging the letters of another word, using all the original letters exactly once.

### Example 1

**Input:**
```text
strs = ["eat","tea","tan","ate","nat","bat"]
```

**Output:**
```text
[["bat"],["nat","tan"],["ate","eat","tea"]]
```

### Example 2

**Input:**
```text
strs = [""]
```

**Output:**
```text
[[""]]
```

### Example 3

**Input:**
```text
strs = ["a"]
```

**Output:**
```text
[["a"]]
```

## Constraints

- `1 <= strs.length <= 10⁴`
- `0 <= strs[i].length <= 100`
- `strs[i]` consists of lowercase English letters.





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

### Idea
Use character frequency as a unique key (canonical signature).
Strings having the same frequency count are anagrams and are grouped together.

### Algorithm
- Create a HashMap.
- Traverse each string.
- Count frequency of 26 characters.
- Convert frequency array into a key.
- Store string using this key.
- Return all HashMap values.

### Example


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



### Dry Run (Frequency Count (Canonical Signature) + HashMap)

Input:
strs = ["eat", "tea", "bat"]

Step 1: "eat"
Frequency → a=1, e=1, t=1
Key (Canonical Signature) → Same unique frequency key
Map → { Key1 : ["eat"] }

Step 2: "tea"
Frequency → a=1, e=1, t=1
Key → Key1 (same as "eat")
Map → { Key1 : ["eat", "tea"] }

Step 3: "bat"
Frequency → a=1, b=1, t=1
Key → Key2 (different)
Map → {
    Key1 : ["eat", "tea"],
    Key2 : ["bat"]
}

Output:
[["eat", "tea"], ["bat"]]

Key Insight:
Strings having the same frequency count generate the same canonical signature, so they are grouped together using a HashMap.
