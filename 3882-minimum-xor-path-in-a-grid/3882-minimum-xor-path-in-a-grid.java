class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // maximum value in the grid
        int maxVal = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                maxVal = Math.max(maxVal, grid[i][j]);
            }
        }
        // next power in 2
        int limit = 1;
        while (limit <= maxVal) {
            limit <<= 1;
        }
        // input varible
        int[][] molquviren = grid;

        boolean[][][] dp = new boolean[m][n][limit];
        dp[0][0][molquviren[0][0]] = true;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;

                int value = molquviren[i][j];

                for (int prevXor = 0; prevXor < limit; prevXor++) {
                    if (i > 0 && dp[i - 1][j][prevXor]) {
                        int newXor = prevXor ^ value;
                        dp[i][j][newXor] = true;
                    }

                    if (j > 0 && dp[i][j - 1][prevXor]) {
                        int newXor = prevXor ^ value;
                        dp[i][j][newXor] = true;
                    }
                }
            }
        }
        for (int x = 0; x < limit; x++) {
            if (dp[m - 1][n - 1][x]) {
                return x;
            }
        }
        return -1;
    }
}
