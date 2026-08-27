class Solution {
    public String lexGreaterPermutation(String s, String target) {

        int n = s.length();
        int[] freq = new int[26];

        // Store frequency of characters in s
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        int pivot = -1;
        char biggerChar = 0;

        // Try to match target from left to right
        for (int i = 0; i < n; i++) {

            int current = target.charAt(i) - 'a';

            // Find the smallest character greater than target[i]
            for (int c = current + 1; c < 26; c++) {
                if (freq[c] > 0) {
                    pivot = i;
                    biggerChar = (char) ('a' + c);
                    break;
                }
            }

            // If target[i] is not available,
            // we cannot continue matching the prefix.
            if (freq[current] == 0) {
                break;
            }
            // Use target[i] for the equal prefix
            freq[current]--;
        }

        // No possible permutation
        if (pivot == -1) {
            return "";
        }

        // Rebuild frequencies from original string
        freq = new int[26];

        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        // Use target's prefix before pivot
        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < pivot; i++) {
            char ch = target.charAt(i);
            ans.append(ch);
            freq[ch - 'a']--;
        }

        // Put the bigger character at pivot
        ans.append(biggerChar);
        freq[biggerChar - 'a']--;

        // Put remaining characters in sorted order
        for (int c = 0; c < 26; c++) {
            while (freq[c] > 0) {
                ans.append((char) ('a' + c));
                freq[c]--;
            }
        }
        return ans.toString();
    }
}