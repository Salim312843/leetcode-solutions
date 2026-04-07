import java.util.*;

class Solution {
    public int[] productExceptSelf(int[] nums) {

        int n = nums.length;
        int[] answer = new int[n];

        answer[0] = 1;  // no left elements
        for (int i = 1; i < n; i++) {
            answer[i] = answer[i - 1] * nums[i - 1];  // prefix product
        }

        int suffix = 1;  // no right elements
        for (int i = n - 1; i >= 0; i--) {
            answer[i] *= suffix;   // combine prefix & suffix
            suffix *= nums[i];     // update suffix
        }

        return answer;
    }
}
