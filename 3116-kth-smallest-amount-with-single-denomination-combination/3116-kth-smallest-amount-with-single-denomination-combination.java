class Solution {

    public long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    public long lcm(long a, long b, long limit) {
        long g = gcd(a, b);

        // Prevent overflow
        if (a > limit / (b / g)) {
            return limit + 1;
        }

        return a * (b / g);
    }

    public long count(long x, int[] coins) {
        int n = coins.length;
        long total = 0;

        int totalMasks = 1 << n;

        for (int mask = 1; mask < totalMasks; mask++) {

            long currentLCM = 1;
            int bits = 0;
            boolean valid = true;

            for (int i = 0; i < n; i++) {

                if ((mask & (1 << i)) != 0) {

                    bits++;

                    currentLCM = lcm(
                        currentLCM,
                        coins[i],
                        x
                    );

                    if (currentLCM > x) {
                        valid = false;
                        break;
                    }
                }
            }

            if (!valid) {
                continue;
            }

            long ways = x / currentLCM;

            if (bits % 2 == 1) {
                total += ways;
            } else {
                total -= ways;
            }
        }

        return total;
    }

    public long findKthSmallest(int[] coins, int k) {

        long low = 1;
        long high = (long) coins[0] * k;

        for (int coin : coins) {
            high = Math.min(high, (long) coin * k);
        }

        while (low < high) {

            long mid = low + (high - low) / 2;

            if (count(mid, coins) >= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }
}