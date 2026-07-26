class Solution {
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int first_max = nums[0] * nums[1] * nums[nums.length - 1];
        int second_max = nums[nums.length - 1] * nums[nums.length - 2] * nums[nums.length - 3];
        if (first_max > second_max) {
            return first_max;
        }
        return second_max;
    }
} 