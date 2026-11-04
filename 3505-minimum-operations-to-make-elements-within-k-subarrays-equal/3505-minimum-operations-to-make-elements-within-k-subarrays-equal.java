class Solution {
    class MedianWindow {
        TreeMap<Long, Integer> left = new TreeMap<>();
        TreeMap<Long, Integer> right = new TreeMap<>();

        int leftSize = 0;
        int rightSize = 0;

        long leftSum = 0;
        long rightSum = 0;

        void add(TreeMap<Long, Integer> map, long val) {
            map.put(val, map.getOrDefault(val, 0) + 1);
        }
        void remove(TreeMap<Long, Integer> map, long val) {
            int count = map.get(val);

            if (count == 1) {
                map.remove(val);
            } else {
                map.put(val, count - 1);
            }
        }
        void insert(long val) {

            if (leftSize == 0 || val <= left.lastKey()) {
                add(left, val);
                leftSize++;
                leftSum += val;
            } else {
                add(right, val);
                rightSize++;
                rightSum += val;
            }
            balance();
        }
        void erase(long val) {

            if (left.containsKey(val)) {
                remove(left, val);
                leftSize--;
                leftSum -= val;
            } else {
                remove(right, val);
                rightSize--;
                rightSum -= val;
            }
            balance();
        }
        void balance() {

            while (leftSize > rightSize + 1) {
                long val = left.lastKey();

                remove(left, val);
                leftSize--;
                leftSum -= val;

                add(right, val);
                rightSize++;
                rightSum += val;
            }
            while (leftSize < rightSize) {
                long val = right.firstKey();

                remove(right, val);
                rightSize--;
                rightSum -= val;

                add(left, val);
                leftSize++;
                leftSum += val;
            }
        }
        long getCost() {

            long median = left.lastKey();
            long costLeft =
                median * leftSize - leftSum;
            long costRight =
                rightSum - median * rightSize;
            return costLeft + costRight;
        }
    }
    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;

         // Calculate cost of every window
        MedianWindow window = new MedianWindow();
        for (int i = 0; i < x; i++) {
            window.insert(nums[i]);
        }
        int totalWindows = n - x + 1;
        long[] cost = new long[totalWindows];

        cost[0] = window.getCost();
        for (int i = x; i < n; i++) {
            window.erase(nums[i - x]);
            window.insert(nums[i]);

            cost[i - x + 1] = window.getCost();
        }
        //DP
        long INF = Long.MAX_VALUE / 4;
        long[][] dp = new long[k + 1][n + 1];

        for (int t = 0; t <= k; t++) {
            Arrays.fill(dp[t], INF);
        }
        // Selecting zero windows costs zero
        for (int i = 0; i <= n; i++) {
            dp[0][i] = 0;
        }
        //DP transitions
        for (int t = 1; t <= k; t++) {
            for (int i = 1; i <= n; i++) {

                // Skip current element
                dp[t][i] = dp[t][i - 1];

                // Select window size x
                if (i >= x && dp[t - 1][i - x] != INF) {

                    long take = dp[t - 1][i - x] + cost[i - x];
                    dp[t][i] = Math.min(
                        dp[t][i],take);
                }
            }
        }
        return dp[k][n];
    }
}