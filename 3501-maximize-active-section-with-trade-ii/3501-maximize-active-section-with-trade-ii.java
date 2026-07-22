import java.util.*;

class Solution {

    class Group {
        int start, length;

        Group(int s, int l) {
            start = s;
            length = l;
        }
    }

    class Pair {
        List<Group> groups;
        int[] idx;

        Pair(List<Group> g, int[] i) {
            groups = g;
            idx = i;
        }
    }

    class SparseTable {

        int[][] st;
        int[] log;

        SparseTable(int[] nums) {

            int n = nums.length;

            // agar array empty hai
            if (n == 0) {
                st = new int[1][1];
                log = new int[1];
                return;
            }

            // log values store kar rahe hain
            log = new int[n + 1];
            for (int i = 2; i <= n; i++)
                log[i] = log[i / 2] + 1;

            int K = log[n] + 1;
            st = new int[K][n];

            // first row me original values
            for (int i = 0; i < n; i++)
                st[0][i] = nums[i];

            // sparse table build kar rahe hain
            for (int k = 1; k < K; k++) {
                for (int i = 0; i + (1 << k) <= n; i++) {
                    st[k][i] = Math.max(
                            st[k - 1][i],
                            st[k - 1][i + (1 << (k - 1))]
                    );
                }
            }
        }

        // range maximum nikalne ke liye
        int query(int l, int r) {

            if (l > r)
                return 0;

            int j = log[r - l + 1];

            return Math.max(
                    st[j][l],
                    st[j][r - (1 << j) + 1]
            );
        }
    }

    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {

        int ones = 0;

        // total kitne 1 hain
        for (char c : s.toCharArray())
            if (c == '1')
                ones++;

        Pair info = getZeroGroups(s);

        List<Group> zeroGroups = info.groups;
        int[] zeroGroupIndex = info.idx;

        List<Integer> ans = new ArrayList<>();

        // agar 0 hi nahi hain to answer same rahega
        if (zeroGroups.isEmpty()) {

            for (int i = 0; i < queries.length; i++)
                ans.add(ones);

            return ans;
        }

        SparseTable st = new SparseTable(getMerge(zeroGroups));

        for (int[] q : queries) {

            int l = q[0];
            int r = q[1];

            int left = -1;

            // left side wali partial zero block
            if (zeroGroupIndex[l] != -1) {

                Group g = zeroGroups.get(zeroGroupIndex[l]);

                left = g.length - (l - g.start);
            }

            int right = -1;

            // right side wali partial zero block
            if (zeroGroupIndex[r] != -1) {

                Group g = zeroGroups.get(zeroGroupIndex[r]);

                right = r - g.start + 1;
            }

            int startAdj = zeroGroupIndex[l] + 1;

            int endAdj = (s.charAt(r) == '1')
                    ? zeroGroupIndex[r]
                    : zeroGroupIndex[r] - 1;

            int best = ones;

            // dono partial groups adjacent hain
            if (s.charAt(l) == '0'
                    && s.charAt(r) == '0'
                    && zeroGroupIndex[l] + 1 == zeroGroupIndex[r]) {

                best = Math.max(best, ones + left + right);
            }

            // beech ke complete zero groups
            else if (startAdj <= endAdj - 1) {

                best = Math.max(
                        best,
                        ones + st.query(startAdj, endAdj - 1)
                );
            }

            // left partial + next complete group
            if (s.charAt(l) == '0'
                    && zeroGroupIndex[l] + 1 <= endAdj) {

                best = Math.max(
                        best,
                        ones + left +
                                zeroGroups.get(zeroGroupIndex[l] + 1).length
                );
            }

            // previous complete group + right partial
            if (s.charAt(r) == '0'
                    && zeroGroupIndex[l] < zeroGroupIndex[r] - 1) {

                best = Math.max(best,
                        ones + right +
                        zeroGroups.get(zeroGroupIndex[r] - 1).length
                );
            }

            ans.add(best);
        }

        return ans;
    }

    private Pair getZeroGroups(String s) {

        List<Group> groups = new ArrayList<>();
        int[] idx = new int[s.length()];

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '0') {

                // same zero group continue ho rahi hai
                if (i > 0 && s.charAt(i - 1) == '0')
                    groups.get(groups.size() - 1).length++;

                // nayi zero group start hui
                else
                    groups.add(new Group(i, 1));
            }

            // ye index kis zero group me hai
            idx[i] = groups.size() - 1;
        }

        return new Pair(groups, idx);
    }

    private int[] getMerge(List<Group> groups) {

        if (groups.size() <= 1)
            return new int[0];

        int[] arr = new int[groups.size() - 1];

        for (int i = 0; i < groups.size() - 1; i++) {

            // adjacent groups ka total length
            arr[i] =groups.get(i).length + groups.get(i + 1).length;
        }

        return arr;
    }
}