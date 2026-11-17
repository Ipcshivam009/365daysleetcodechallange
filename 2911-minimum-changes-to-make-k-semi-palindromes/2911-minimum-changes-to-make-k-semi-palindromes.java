
class Solution {
    public int minimumChanges(String s, int k) {

        int n = s.length();
        int INF = 1000000000;

        // Step 1 Precompute minimum changes
        int[][] cost = new int[n][n];

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {

                int best = INF;
                int j = i + len - 1;

                // Check all proper divisors
                for (int d = 1; d < len; d++) {
                    if (len % d != 0) {
                        continue;
                    }
                    int changes = 0;
                    int count = len / d;

                    // Check each group
                    for (int r = 0; r < d; r++) {
                        for (int t = 0; t < count / 2; t++) {
                            int left = i + r + t * d;
                            int right = i + r
                                    + (count - 1 - t) * d;

                            if (s.charAt(left) != s.charAt(right)) {
                                changes++;
                            }
                        }
                    }
                    best = Math.min(best, changes);
                }
                cost[i][j] = best;
            }
        }

        // Step 2 DP 
        int[][] dp = new int[k + 1][n + 1];

        for (int p = 0; p <= k; p++) {
            for (int i = 0; i <= n; i++) {
                dp[p][i] = INF;
            }
        }
        dp[0][0] = 0;

        // Step 3 DP transition
        for (int p = 1; p <= k; p++) {
            for (int i = 2 * p; i <= n; i++) {
                for (int j = 2 * (p - 1); j <= i - 2; j++) {
                    if (dp[p - 1][j] == INF) {
                        continue;
                    }
                    dp[p][i] = Math.min(
                        dp[p][i],
                        dp[p - 1][j] + cost[j][i - 1]
                    );
                }
            }
        }
        return dp[k][n];
    }
}