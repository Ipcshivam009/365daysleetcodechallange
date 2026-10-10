import java.util.Arrays;
class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long[] diff = new long[n];
        long totalDiff = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs((long) nums1[i] - nums2[i]);
            totalDiff += diff[i];
        }
        long k = (long) k1 + k2;
        if (totalDiff <= k) {
            return 0;
        }
        Arrays.sort(diff);
        int low = 0, high = (int) diff[n - 1];
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (canReduce(diff, k, mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        int limit = low;
        long used = cost(diff, limit);
        long remaining = k - used;
        long ans = 0;
        int countAtLimit = 0;
        for (long d : diff) {
            if (d > limit) {
                ans += (long) limit * limit;
                countAtLimit++;
            } else if (d == limit) {
                ans += (long) limit * limit;
                countAtLimit++;
            } else {
                ans += d * d;
            }
        }
        ans -= (long) countAtLimit * limit * limit;
        int extra = (int) remaining;
        ans += (long) (countAtLimit - extra) * limit * limit;
        ans += (long) extra * (long) (limit - 1) * (limit - 1);
        return ans;
    }
    private boolean canReduce(long[] diff, long k, int limit) {
        return cost(diff, limit) <= k;
    }
    private long cost(long[] diff, int limit) {
        long needed = 0;
        for (long d : diff) {
            if (d > limit) {
                needed += d - limit;
            }
        }
        return needed;
    }
}