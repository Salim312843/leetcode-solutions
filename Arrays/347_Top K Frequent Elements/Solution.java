import java.util.*;

class Solution {
    public int[] topKFrequent(int[] nums, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();

        // Step 1: Count frequency                 
        for (int num : nums) {                                         
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // Step 2: Create bucket array
        List<Integer>[] bucket = new ArrayList[nums.length + 1];

        for (int key : map.keySet()) {           // 1, 2, 3
            int freq = map.get(key);             // 1 → 3  2 → 2  3 → 1

            if (bucket[freq] == null) {
                bucket[freq] = new ArrayList<>();          // bucket[1] → [3]
                }                                            // bucket[2] → [2]
             bucket[freq].add(key);                                           //bucket[3] → [1]
            } 


        // Step 3: Collect top k elements
        int[] result = new int[k];
        int index = 0;

        for (int i = bucket.length - 1; i >= 0 && index < k; i--) {
            if (bucket[i] != null) {
                for (int num : bucket[i]) {
                    result[index++] = num;

                    if (index == k) {
                        break;
                    }
                }
            }
        }

        return result;
    }
}
