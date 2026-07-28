class Solution {
    public String smallestPalindrome(String s) {
        // Frequency Array
        int[] freq = new int[26];

        // Count fequency of each character
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        StringBuilder left = new StringBuilder();
        char middle = '\0';

        // Build left half and find middle character
        for (int i = 0; i < 26; i++) {

            // Add half of the occurrence to the left half
            for (int j = 0; j < freq[i] / 2; j++) {
                left.append((char) ('a' + i));
            }

            // Store the odd frequency character (if any)
            if (freq[i]%2 == 1) {
                middle = (char) ('a' + i);
            }
        }

        // Right half is reverse of left half
        StringBuilder right = new StringBuilder(left).reverse();

        // Constructing final answer
        if (middle != '\0') {
            return left.toString() + middle + right.toString();
        }

        return left.toString() + right.toString();
    }
}