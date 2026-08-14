class Solution {
    public int maximumLengthSubstring(String s) {
        HashMap<Character, Integer> freq = new HashMap<>();

        int l = 0;
        int maxLength = 0;

        for (int r = 0; r < s.length(); r++) {
            char ch = s.charAt(r);

            // add current element in freq
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);

            while(freq.get(ch) > 2) {

                char leftChar = s.charAt(l);

                // If any character occurs more than 2 times
                freq.put(leftChar, freq.get(leftChar) - 1);

                l++;
            }

            maxLength = Math.max(maxLength, r - l + 1);
        }
        return maxLength;
    }
}