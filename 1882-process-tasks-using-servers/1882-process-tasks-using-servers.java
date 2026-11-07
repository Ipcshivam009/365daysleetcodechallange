class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {

        int n = servers.length;
        int m = tasks.length;

        int[] ans = new int[m];
        // Free servers weight, index
        PriorityQueue<int[]> free = new PriorityQueue<>(
            (a, b) -> {
                if (a[0] != b[0])
                    return a[0] - b[0];
                return a[1] - b[1];
            }
        );
        // Busy servers endTime, weight, index
        PriorityQueue<long[]> busy = new PriorityQueue<>(
            (a, b) -> Long.compare(a[0], b[0])
        );
        // Add all servers
        for (int i = 0; i < n; i++) {
            free.offer(new int[]{servers[i], i});
        }
        long time = 0;
        for (int i = 0; i < m; i++) {
            time = Math.max(time, i);

            // Release completed servers
            while (!busy.isEmpty() && busy.peek()[0] <= time) {
                long[] s = busy.poll();

                free.offer(new int[]{
                    (int) s[1],
                    (int) s[2]
                });
            }
            // If no server is free then wait
            if (free.isEmpty()) {

                time = busy.peek()[0];

                while (!busy.isEmpty() && busy.peek()[0] <= time) {
                    long[] s = busy.poll();
                    free.offer(new int[]{
                        (int) s[1],
                        (int) s[2]
                    });
                }
            }
            // Select available server
            int[] s = free.poll();

            ans[i] = s[1];

            // Assign task
            busy.offer(new long[]{
                time + tasks[i],
                s[0],
                s[1]
            });
        }
        return ans;
    }
}
