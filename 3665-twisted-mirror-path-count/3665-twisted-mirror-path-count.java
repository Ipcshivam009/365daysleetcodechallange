
class Solution {
    public int uniquePaths(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        int MOD = 1_000_000_007;

        int[][][] dp = new int[m][n][2];
        // Starting cell
        dp[0][0][0] = 1;
        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                int rightWays = dp[i][j][0];
                int downWays = dp[i][j][1];

                if (grid[i][j] == 0) {
                    if (j + 1 < n) {
                        dp[i][j + 1][0] = (
                            dp[i][j + 1][0]
                            + rightWays + downWays
                        ) % MOD;
                    }
                    if (i + 1 < m) {
                        dp[i + 1][j][1] = (
                            dp[i + 1][j][1]
                            + rightWays + downWays
                        ) % MOD;
                    }

                } else {
                    // Reflect down
                    if (i + 1 < m) {
                        dp[i + 1][j][1] = (
                            dp[i + 1][j][1] + rightWays
                        ) % MOD;
                    }
                    // Reflect right
                    if (j + 1 < n) {
                        dp[i][j + 1][0] = (
                            dp[i][j + 1][0] + downWays
                        ) % MOD;
                    }
                }
            }
        }
        return 
            (dp[m - 1][n - 1][0]
            + dp[m - 1][n - 1][1]
        ) % MOD;
    }
}
