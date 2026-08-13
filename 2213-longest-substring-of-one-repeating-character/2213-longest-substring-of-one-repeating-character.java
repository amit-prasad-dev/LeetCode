class Solution {
    int[] prefix;
    int[] suffix;
    int[] max;
    int[] length;
    char[] leftChar;
    char[] rightChar;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {

        int n = s.length();

        prefix = new int[4 * n];
        suffix = new int[4 * n];
        max = new int[4 * n];
        length = new int[4 * n];

        leftChar = new char[4 * n];
        rightChar = new char[4 * n];

        char[] arr = s.toCharArray();

        // Build Segment Tree
        build(1, 0, n - 1, arr);

        int k = queryIndices.length;
        int[] answer = new int[k];

        for (int i = 0; i < k; i++) {

            int index = queryIndices[i];
            char ch = queryCharacters.charAt(i);

            // Update the character
            update(1, 0, n - 1, index, ch);

            // Root contains the answer
            answer[i] = max[1];
        }

        return answer;
    }


    // Build Segment Tree
    void build(int node, int l, int r, char[] arr) {

        // Leaf node
        if (l == r) {

            leftChar[node] = arr[l];
            rightChar[node] = arr[l];

            prefix[node] = 1;
            suffix[node] = 1;
            max[node] = 1;
            length[node] = 1;

            return;
        }

        int mid = (l + r) / 2;

        // Build left child
        build(node * 2, l, mid, arr);

        // Build right child
        build(node * 2 + 1, mid + 1, r, arr);

        // Combine children
        merge(node);
    }


    // Update one character
    void update(int node, int l, int r, int index, char ch) {

        // We reached the required position
        if (l == r) {

            leftChar[node] = ch;
            rightChar[node] = ch;

            prefix[node] = 1;
            suffix[node] = 1;
            max[node] = 1;

            return;
        }

        int mid = (l + r) / 2;

        if (index <= mid) {

            // Go to left child
            update(node * 2, l, mid, index, ch);

        } else {

            // Go to right child
            update(node * 2 + 1, mid + 1, r, index, ch);
        }

        // Recalculate current node
        merge(node);
    }


    // Merge left child and right child
    void merge(int node) {

        int left = node * 2;
        int right = node * 2 + 1;

        // Total length
        length[node] = length[left] + length[right];

        // First character
        leftChar[node] = leftChar[left];

        // Last character
        rightChar[node] = rightChar[right];

        // Start with the best answer from either side
        max[node] = Math.max(max[left], max[right]);

        // Prefix initially comes from left child
        prefix[node] = prefix[left];

        // Suffix initially comes from right child
        suffix[node] = suffix[right];


        // If boundary characters are equal,
        // the two parts can be connected.
        if (rightChar[left] == leftChar[right]) {

            // Longest substring crossing the boundary
            max[node] = Math.max(
                max[node],
                suffix[left] + prefix[right]
            );

            // If the ENTIRE left segment has the same character,
            // prefix can extend into the right segment.
            if (prefix[left] == length[left]) {

                prefix[node] =
                    length[left] + prefix[right];
            }

            // If the ENTIRE right segment has the same character,
            // suffix can extend into the left segment.
            if (suffix[right] == length[right]) {

                suffix[node] =
                    suffix[left] + length[right];
            }
        }
    }
}    
    

