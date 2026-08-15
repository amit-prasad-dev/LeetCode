class Solution {
    public int missingNumber(int[] nums) {
        int expected_sum = 0;
        int sum = 0;
        int n = nums.length;
        // for(int i = 0; i <= nums.length; i++) {
        //     expected_sum += i;
        // }
        expected_sum = (n * (n + 1)) / 2;
        for (int num : nums) {
            sum += num;
        }
        return expected_sum - sum;
    }
}