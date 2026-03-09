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

