class Solution {
    public int maxProduct(int n) {
        List<Integer> ans = new ArrayList<>();
        while (n != 0) {
            ans.add(n % 10);
            n /= 10;
        }
        Collections.sort(ans);

        return ans.get(ans.size() - 2) * ans.get(ans.size() - 1);
    }
}