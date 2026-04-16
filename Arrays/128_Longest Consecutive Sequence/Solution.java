// APPROACH : HASHSET
// TIME COMPLEXITY : O(n)
// SPACE COMPLEXITY : O(n)


import java.util.*;

class Solution {
    public int longestConsecutive(int[] nums) {

        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {          // Insert all numbers into set   // Removes duplicates automatically
            set.add(num);
        }

        int longest = 0;

        for (int num : set) {        // Iterate through unique elements

            if (!set.contains(num - 1)) {   // start of sequence

                int current = num;
                int count = 1;

                while (set.contains(current + 1)) {
                    current++;
                    count++;
                }

                longest = Math.max(longest, count);
            }
        }

        return longest;
    }
}
