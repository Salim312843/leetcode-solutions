import java.util.HashMap;

class Solution {
    public int[] twoSum(int[] nums, int target) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            int complement = target - nums[i];

            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }

            map.put(nums[i], i);                //If complement is not found, we store the current element.
        }

        return new int[]{};                     //This runs only if no pair is found.
    }
}





//Method Breakdown   :   
                   int[] - return type  ,  two sum - method name , (int[] nums, int target) - inputs 
                   in short : A public method named twoSum that takes an integer array and a target value as input and returns an array of two indices
                             whose elements add up to the target.
