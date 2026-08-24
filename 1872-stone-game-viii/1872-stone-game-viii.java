class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        for (int i = 1; i < n; i++) {
            stones[i] += stones[i - 1]; // Calculating the prefix sum
        }
        int best = stones[n - 1]; // case when remove all stones from the array
        for (int i = n - 2; i >= 1; i--) {
            best = Math.max(best, stones[i] - best);// currentprefix sum - opponent best advantage
        }
        return best;
    }
}