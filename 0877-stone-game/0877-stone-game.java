class Solution {
    Integer[][] dp;
   
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        dp = new Integer[n][n];
        return solve(piles, 0, n - 1) > 0;
    }

    private int solve(int[] piles, int left, int right) {
        // Base case : only one pile is left
        if  (left == right) {
            return piles[left];
        }
        // Memoization
        if  (dp[left][right] != null) {
            return dp[left][right];
        }
        // Option 1: Picking left pile
        int takeLeft = piles[left] - solve(piles, left + 1, right);
        // Option 2: Picking the right pile 
        int takeRight = piles[right] - solve(piles, left, right - 1);

        // Choose the option that gives the aximum score diffrence
        return dp[left][right] = Math.max(takeLeft, takeRight);
    }
}