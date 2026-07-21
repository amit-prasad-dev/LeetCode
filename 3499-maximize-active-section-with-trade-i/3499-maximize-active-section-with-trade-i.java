class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        
        // Count total 1's in the original string
        int active = 0;
        for(char c : s.toCharArray()) {
            if (c == '1') active++;
        }

        // add 1 at both end so checking surrounded blocks becomes easy
        String t = "1" + s + "1";

        // Store block type (0 or 1)
        ArrayList<Character> type = new ArrayList<>();

        // Store length of each block
        ArrayList<Integer> len = new ArrayList<>();

        int i = 0;
        
        // Create block of consecutive same character
        while (i < t.length()) {

            char ch = t.charAt(i);
            int j = i;

            // Find the end of the current block
            while (j < t.length() && t.charAt(j) == ch) {
                j++;
            }

            // Save current block
            type.add(ch);
            len.add(j - i);

            i = j;
        }

        int max = 0;

        // Check every 1-block
        for(int k = 1; k < type.size()-1; k++) {

            // Valid only if this 1-block is between two 0-block
            if(type.get(k) == '1'
                    && type.get(k-1) == '0'
                    && type.get(k+1) == '0') {

                // These two 0-block will merge after removing this 1-block
                int gain = len.get(k-1) + len.get(k+1);

                // keep the maximum gain
                max = Math.max(max, gain);
            }
        }

        // Original active sections + best possible gain
        return active + max;

    }
}