class Solution {

    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        
        // Adjacency list to store the graph
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n ;i++) {
            graph[i] = new ArrayList<>();
        }

        // Building the graph
        for (int[] edge : invocations) {
            graph[edge[0]].add(edge[1]);
        }

        // To keep track of all suspicious methods
        boolean[] suspicious = new boolean[n];

        // Mark all methods reachable from k as suspicious
        dfs(k, graph, suspicious);

        // Check if any non-suspicious method calls a suspicious method
        // If yes, we cannot remove the suspicious group
        for (int[] edge : invocations) {
            int u = edge[0];
            int v = edge[1];

            if (!suspicious[u] && suspicious[v]) {

                // Return all methods since removal i not possible
                List<Integer> ans = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    ans.add(i);
                } 
                return ans;
            }
        }

        // Store all remaining (non-suspicious) methods
        List<Integer> ans = new ArrayList<>();

        for (int i=0; i<n; i++) {
            if (!suspicious[i]) {
                ans.add(i);
            }
        }
        return ans;
    }

    // DFS to mark all methods reachable from k
    private void dfs(int node, List<Integer>[] graph, boolean[] suspicious) {

        // Mark current method as suspicious
        suspicious[node] = true;

        // Visit all the methods called by the current method
        for (int next : graph[node]) {

            // Visit only if not already marked
            if (!suspicious[next]) {
                dfs(next, graph, suspicious);
            }
        }
    }
}