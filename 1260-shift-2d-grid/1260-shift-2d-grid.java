class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        // number of rows
        int m = grid.length;

        // number of column
        int n = grid[0].length;

        // Total number of elemnt in the grid
        int total = m * n;

        // Reduce unnecessary ful rotation
        k = k % total;

        // New grid to store the shifted elements
        int[][] ans = new int[m][n];

        // Travverse every elements of the original grid
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){

                // Concert 2D position into 1D index
                int currentIndex = i * n + j;

                // Calculate the new position after shifting by k
                int newIndex = (currentIndex + k) % total;

                // Convert the new 1D index back to row number
                int newRow = newIndex / n;

                // Convert the new 1D index back to column number
                int newCol = newIndex % n;

                // Place the current element at its new position
                ans[newRow][newCol] = grid[i][j];
            }
        }

        // Final answer in the required List<List<Integer>> format
        List<List<Integer>> result = new ArrayList<>();

        // Convert the 2D array into List<List<Integer>>
        for(int i = 0; i <m; i++){
            
            // Create a list for the current row
            List<Integer> row = new ArrayList<>();

            // Add all elements of the current row
            for(int j = 0; j < n; j++){
                row.add(ans[i][j]);
            }

            // Add all elements of the result
            result.add(row);
        }

        return result;
    }
}