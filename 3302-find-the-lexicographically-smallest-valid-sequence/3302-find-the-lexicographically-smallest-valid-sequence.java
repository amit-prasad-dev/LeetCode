class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        // dp[i] = word1[i...] se word2 ke remaining
        // characters ko exactly match karne ki maximum length
        int[] dp = new int[n + 1];
        int j = m - 1;
        // Right to Left
        for (int i = n - 1; i >= 0; i--) {
            if (j >= 0 && word1.charAt(i) == word2.charAt(j)) {
                dp[i] = dp[i + 1] + 1;
                j--;
            } else {
                dp[i] = dp[i + 1];
            }
        }
        int[] ans = new int[m];
        int i = 0;
        j = 0;
        // Find lexicographically smallest sequence
        while (i < n && j < m) {
            // Case 1: Characters match
            if (word1.charAt(i) == word2.charAt(j)) {
                ans[j] = i;
                j++;
            } 
            // Case 2: Characters don't match
            else {
                // Use this as the one allowed mismatch
                // only if the remaining characters
                // can be matched exactly.
                if (dp[i + 1] >= m - 1 - j) {
                    ans[j] = i;
                    j++;
                    // One mismatch has been used
                    i++;
                    break;
                }
            }
            i++;
        }
        // If we couldn't complete the answer
        if (j < m && i == n) {
            return new int[0];
        }
        // Mismatch has already been used.
        // Now remaining characters must match exactly.
        while (j < m && i < n) {
            if (word1.charAt(i) == word2.charAt(j)) {
                ans[j] = i;
                j++;
            }
            i++;
        }
        // Still couldn't form word2
        if (j < m) {
            return new int[0];
        }
        return ans;
    }
}