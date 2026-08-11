# LeetCode 125. Valid Palindrome (Java)

## Problem Statement

A phrase is a **palindrome** if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward.

Alphanumeric characters include **letters (`a-z`, `A-Z`) and digits (`0-9`)**.

Given a string `s`, return `true` if it is a palindrome after converting all uppercase letters to lowercase and removing all non-alphanumeric characters. Otherwise, return `false`.

**Example 1:**

```text
Input: s = "A man, a plan, a canal: Panama"
Output: true

Explanation:
After removing non-alphanumeric characters and converting to lowercase,
the string becomes "amanaplanacanalpanama", which is a palindrome.
```

**Example 2:**

```text
Input: s = "race a car"
Output: false

Explanation:
After preprocessing, the string becomes "raceacar",
which is not a palindrome.
```

**Example 3:**

```text
Input: s = " "
Output: true

Explanation:
After removing all non-alphanumeric characters,
the string becomes an empty string "".
An empty string is considered a palindrome.
```

**Constraints:**

- `1 <= s.length <= 2 × 10⁵`
- `s` consists only of printable ASCII characters.

---

## Approach 1: Brute Force

### Logic
- Traverse the string and build a new string containing only lowercase alphanumeric characters.
- Reverse the cleaned string.
- Compare the cleaned string with its reversed version.

```java
class Solution {

    public boolean isPalindrome(String s) {

        StringBuilder cleaned = new StringBuilder();

        for (char ch : s.toCharArray()) {

            if (Character.isLetterOrDigit(ch)) {
                cleaned.append(Character.toLowerCase(ch));
            }
        }

        String original = cleaned.toString();
        String reversed = cleaned.reverse().toString();

        return original.equals(reversed);
    }
}
```

**Time Complexity:** `O(n)` *(The string is traversed to clean and reverse it.)*  
**Space Complexity:** `O(n)` *(A new cleaned string and its reverse are created.)*

---

## Approach 2: Two Pointers ⭐ Optimal

### Logic
- Place one pointer at the beginning and another at the end.
- Skip non-alphanumeric characters.
- Compare characters after converting them to lowercase.
- If all corresponding characters match, the string is a palindrome.

```java
public static boolean isPalindrome(String s) {
    int left = 0;
    int right = s.length() - 1;

    while (left < right) {
        char leftChar = s.charAt(left);
        char rightChar = s.charAt(right);

        if (!Character.isLetterOrDigit(leftChar)) {
            left++;
            continue;
        }

        if (!Character.isLetterOrDigit(rightChar)) {
            right--;
            continue;
        }

        if (Character.toLowerCase(leftChar) != Character.toLowerCase(rightChar)) {
            return false;
        }

        left++;
        right--;
    }

    return true;
}

```

**Time Complexity:** `O(n)` *(Each character is visited at most once using two pointers.)*  
**Space Complexity:** `O(1)` *(Only two pointers are used; no extra data structures are required.)*
