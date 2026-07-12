class Solution {
    public int[] arrayRankTransform(int[] arr) {
        // T.C --> O(n log n)
        // S.C --> O(n)

        TreeSet<Integer> set = new TreeSet<>();
        for (int num : arr) {
            set.add(num);
        }
        HashMap<Integer, Integer> rankMap = new HashMap<>();
        int rank = 1;
        for (int num : set) {
            rankMap.put(num, rank++);
        }
        int[] ans = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ans[i] = rankMap.get(arr[i]);
        }
        return ans;
    }
}