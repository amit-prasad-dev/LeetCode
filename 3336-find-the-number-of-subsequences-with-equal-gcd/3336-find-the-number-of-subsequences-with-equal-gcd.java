class Solution {
    int MOD =  1_000_000_007;
    public int subsequencePairCount(int[] nums) {
        int MAX = 200;
        long[][] dp = new long[MAX+1][MAX+1];
        dp[0][0] = 1;

        for(int x : nums){
            long[][] next = new long[MAX+1][MAX+1];
            for(int g1=0; g1 <= MAX; g1++){
                for(int g2=0; g2 <= MAX; g2++){
                    long ways = dp[g1][g2];

                    if (ways == 0)
                        continue;

                    next[g1][g2] = (next[g1][g2] + ways) % MOD;

                    int newGcd1 = (g1 == 0) ? x : gcd(g1, x);
                    next[newGcd1][g2] = (next[newGcd1][g2] + ways) % MOD;

                    int newGcd2 = (g2 == 0) ? x : gcd(g2, x);
                    next[g1][newGcd2] = (next[g1][newGcd2] + ways) % MOD;

                }
            }
            dp = next;
        }

        long ans = 0;
        for(int g = 1; g <= MAX; g++){
            ans = (ans + dp[g][g]) % MOD;
        }
        return (int) ans;
    }

    int gcd(int a, int b){
        while(b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}