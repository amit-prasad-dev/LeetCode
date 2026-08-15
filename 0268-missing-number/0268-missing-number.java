class Solution {
    public int missingNumber(int[] nums) {
        int expected_sum = 0;
        int sum = 0;
        for(int i = 0; i <= nums.length; i++) {
            expected_sum += i;
        }
        for (int num : nums) {
            sum += num;
        }
        return expected_sum - sum;
    }
}