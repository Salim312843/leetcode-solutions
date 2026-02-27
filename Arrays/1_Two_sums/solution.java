class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> numIndexMap = new HashMap<>();
        
        // Store all numbers and their indices for O(1) lookup
        for (int i = 0; i < nums.length; i++) {
            numIndexMap.put(nums[i], i);
        }
        
        // Find complement for each number
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (numIndexMap.containsKey(complement) && numIndexMap.get(complement) != i) {
                return new int[] { i, numIndexMap.get(complement) };
            }
        }
        
        return new int[] {};
    }
}

/*
COMPLEXITY ANALYSIS
==================
Time Complexity:  O(n)
  - First pass:   O(n) to populate the hash map
  - Second pass:  O(n) to find the complement
  - Total:        O(n)

Space Complexity: O(n) for the hash map

WHY THIS APPROACH IS OPTIMAL
=============================

HASH MAP (This Solution):       O(n) time, O(n) space
  ✓ Optimal - single pass through array, constant lookup
  ✓ Preferred for interviews and production

BRUTE FORCE (Nested loops):     O(n²) time, O(1) space
  ✗ Inefficient for large arrays

SORTING + TWO POINTERS:         O(n log n) time, O(1) space
  ✗ Slower than hash map, loses original indices

Conclusion: Hash map trading space for time is the best solution
for this problem as it achieves optimal linear time complexity.
*/
