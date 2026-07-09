class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
    
        int[] comp = new int[n];
        comp[0] = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] - nums[i - 1] <= maxDiff) {
                comp[i] = comp[i - 1];
            } else {
                comp[i] = comp[i - 1] + 1;
            }
        }

        int q = queries.length;
        boolean[] ans = new boolean[q];
        for (int i = 0; i < q; i++) {
            int u = queries[i][0], v = queries[i][1];
            ans[i] = comp[u] == comp[v];
        }
        return ans;
    }
}