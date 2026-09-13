class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;

        List<int[]> A = new ArrayList<>();
        List<int[]> B = new ArrayList<>();

        // Store positions of 1s
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (img1[i][j] == 1)
                    A.add(new int[]{i, j});

                if (img2[i][j] == 1)
                    B.add(new int[]{i, j});
            }
        }

        Map<String, Integer> count = new HashMap<>();
        int ans = 0;

        // Compare every 1 in A with every 1 in B
        for (int[] a : A) {
            for (int[] b : B) {
                int dx = b[0] - a[0];
                int dy = b[1] - a[1];

                String key = dx + "," + dy;

                count.put(key, count.getOrDefault(key, 0) + 1);
                ans = Math.max(ans, count.get(key));
            }
        }

        return ans;
    }
}