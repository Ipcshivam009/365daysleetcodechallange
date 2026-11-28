class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;

        int[] freq = new int[n + 1];
        int minPrice = Integer.MAX_VALUE;

        for (int[] item : items) {
            freq[item[0]]++;
            minPrice = Math.min(minPrice, item[1]);
        }
        int[] count = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j += i) {
                count[i] += freq[j];
            }
        }
        List<int[]> list = new ArrayList<>();

        for (int[] item : items) {
            int free = count[item[0]] - 1;

            if (free > 0 && item[1] <= 2L * minPrice) {
                list.add(new int[]{item[1], free});
            }
        }
        list.sort((a, b) -> Integer.compare(a[0], b[0]));

        long ans = 0;
        long remaining = budget;

        for (int[] item : list) {
            long price = item[0];
            long free = item[1];

            long buy = Math.min(free, remaining / price);

            ans += buy * 2;
            remaining -= buy * price;
        }
        ans += remaining / minPrice;

        return (int) ans;
    }
}