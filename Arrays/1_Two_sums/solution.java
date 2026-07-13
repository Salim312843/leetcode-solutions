  import java.util.HashMap;

public static int[] twoSum(int[] nums, int target) {
    HashMap<Integer, Integer> seen = new HashMap<>();

    for (int i = 0; i < nums.length; i++) {
        int complement = target - nums[i];

        if (seen.containsKey(complement)) {
            return new int[]{seen.get(complement), i};
        }

        seen.put(nums[i], i);               //If complement is not found, we store the current element.
    }

    throw new IllegalArgumentException("No two sum solution exists");                  //This runs only if no pair is found.
}




//Method Breakdown   :   
                   int[] - return type  ,  two sum - method name , (int[] nums, int target) - inputs 
                   in short : A public method named twoSum that takes an integer array and a target value as input and returns an array of two indices
                             whose elements add up to the target.
