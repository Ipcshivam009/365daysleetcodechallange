import java.util.*;
class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        int[][] a = new int[n][2];
        for (int i = 0; i < n; i++)
            a[i] = new int[]{nums[i], sum(nums[i])};
        Arrays.sort(a, (x, y) -> {
            if (x[1] != y[1])
                return Integer.compare(x[1], y[1]);
            return Integer.compare(x[0], y[0]);
        });
        Map<Integer, Integer> pos = new HashMap<>();
        for (int i = 0; i < n; i++)
            pos.put(a[i][0], i);
        boolean[] visited = new boolean[n];
        int swaps = 0;
        for (int i = 0; i < n; i++) {
            if (visited[i] || pos.get(nums[i]) == i)
                continue;
            int j = i, cycle = 0;
            while (!visited[j]) {
                visited[j] = true;
                j = pos.get(nums[j]);
                cycle++;
            }
            swaps += cycle - 1;
        }
        return swaps;
    }
    private int sum(int n) {
        int s = 0;
        while (n > 0) {
            s += n % 10;
            n /= 10;
        }
        return s;
    }
}
