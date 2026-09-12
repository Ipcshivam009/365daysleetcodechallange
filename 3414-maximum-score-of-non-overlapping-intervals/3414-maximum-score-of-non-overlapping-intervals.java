class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();

        Integer[] id = new Integer[n];
        for (int i = 0; i < n; i++) id[i] = i;

        Arrays.sort(id, (a, b) -> {
            int x = intervals.get(a).get(0);
            int y = intervals.get(b).get(0);
            if (x != y) return Integer.compare(x, y);
            return Integer.compare(a, b);
        });

        // next[i] = first interval whose start > current end
        int[] next = new int[n];

        for (int i = 0; i < n; i++) {
            int cur = id[i];
            int end = intervals.get(cur).get(1);

            int lo = i + 1, hi = n;
            while (lo < hi) {
                int mid = (lo + hi) / 2;

                if (intervals.get(id[mid]).get(0) > end)
                    hi = mid;
                else
                    lo = mid + 1;
            }

            next[i] = lo;
        }

        long[][] dp = new long[n + 1][5];
        List<Integer>[][] ans = new ArrayList[n + 1][5];

        for (int i = 0; i <= n; i++) {
            for (int k = 0; k <= 4; k++) {
                ans[i][k] = new ArrayList<>();
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            int cur = id[i];

            for (int k = 1; k <= 4; k++) {

                // Skip
                dp[i][k] = dp[i + 1][k];
                ans[i][k] = new ArrayList<>(ans[i + 1][k]);

                // Take
                long value = intervals.get(cur).get(2)
                        + dp[next[i]][k - 1];

                List<Integer> candidate =
                        new ArrayList<>(ans[next[i]][k - 1]);

                candidate.add(cur);
                Collections.sort(candidate);

                if (value > dp[i][k] ||
                    (value == dp[i][k] &&
                     smaller(candidate, ans[i][k]))) {

                    dp[i][k] = value;
                    ans[i][k] = candidate;
                }
            }
        }

        return ans[0][4]
                .stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    private boolean smaller(List<Integer> a, List<Integer> b) {
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) < b.get(i)) return true;
            if (a.get(i) > b.get(i)) return false;
        }
        return false;
    }
}