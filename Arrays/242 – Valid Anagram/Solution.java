class Solution {
    public boolean isAnagram(String s, String t) {

        // If lengths differ, they cannot be anagrams
        if (s.length() != t.length()) {
            return false;
        }

        int[] count = new int[26];  // O(1) space (fixed size for 26 letters)

        // Single pass:
        // +1 for s characters
        // -1 for t characters
        // If anagram, all frequencies cancel to 0
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }

        // Check for mismatch
        for (int num : count) {
            if (num != 0) return false;
        }

        return true;
    }
}

/*
Time Complexity: O(n)
- Single traversal of strings.

Space Complexity: O(1)
- Fixed array of size 26 (constant space).

Why this is optimal:
- Sorting approach takes O(n log n) time.
- Nested loops take O(n²) time (too slow for n up to 50,000).
- Frequency array gives linear time and constant space.
*/
