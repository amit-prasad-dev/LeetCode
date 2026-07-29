class Solution {

    // Maximum value we care about.
    // Since k <= 10^6, agar permutation  isse zyada ho jaye
    // to hume  extract value ki jarurat nahi hai
    private static final long LIMIT = 1_000_000L;

    public String smallestPalindrome(String s, int k) {
        
        // Step 1: Frequency count of every character 
        int[] freq = new int[26];

        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        // Middle character (for odd length palindrome)
        String mid = "";

        // Store frequency of the left half
        int[] left_half = new int[26];

        // Length of left half
        int len = 0;

        // Step 2:
        // Build half frquency and find middle character 
        for (int i = 0; i < 26; i++) {

            // Odd frequency charcter become middle
            if ((freq[i] & 1) == 1) {
                mid = String.valueOf((char) ('a' + i));
            }

            // Only half character are required
            left_half[i] = freq[i] / 2;

            len = len + left_half[i];
        }

        // Step 3:
        // Count total distinct permutation possible
        long total = countWays(left_half, len);

        // If total permutations are les than k,
        // answer doesn't exist
        if (total < k) return "";

        // Store left half of palindrome
        StringBuilder left = new StringBuilder();

        // step 4:
        // Build left half greedily
        while (len > 0) {

            // Try every charcter from a to z
            for (int c = 0; c < 26; c++){
                if (left_half[c] == 0) continue;

                // Assume this character is choosen
                left_half[c]--;

                // Count remaining permutation
                long way = countWays(left_half, len - 1);

                if (way >= k) {

                    // k-th palindrome lies in this branch
                    left.append((char) ('a' + c));

                    len--;

                    break;
                } else {

                    // Skip this branch
                    k -= way;

                    // Restore frequency
                    left_half[c]++;
                }
            }
        }

        // Step 5:
        // Construct final palindrome

        StringBuilder ans = new StringBuilder();

        ans.append(left);

        ans.append(mid);

        ans.append(new StringBuilder(left).reverse());

        return ans.toString();

    }

    // Count the number of distinct Permutation
    // Using multinomial formula 
    private long countWays (int[] left_half, int total) {

        long res = 1;

        int remaining = total;

        for (int i =0; i < 26; i++) {

            int cnt = left_half[i];

            if (cnt == 0) continue;

            /*
                Formula:

                remaining!
                ----------
                cnt! * ...

                Instead of calculating factorial directly, 
                multiply by 
                C(remaining , cnt)
                repeatedly.
            */

            res *= nCrLimited(remaining, cnt);

            // No need to count beyond LIMIT
            if (res > LIMIT) return LIMIT;

            remaining -= cnt;
        }
        return Math.min(res, LIMIT);
    }

    // Claculate nCr
    // Stop if answer exceeds LIMIT
    private long nCrLimited(int n, int r) {

        if (r > n) return 0;

        r = Math.min(r, n-r);

        long ans  = 1;

        for (int i = 1; i <= r; i++) {

            ans = ans * (n-r + i) / i;

            if (ans > LIMIT) return LIMIT;
        }

        return ans;
    }

}