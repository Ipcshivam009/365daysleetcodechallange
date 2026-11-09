class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {

        TreeSet<Integer> available = new TreeSet<>();

        for (int i = 0; i < k; i++) {
            available.add(i);
        }
        // finish time, server number
        PriorityQueue<int[]> busy = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[0], b[0]));
        
        int[] count = new int[k];
        for (int i = 0; i < arrival.length; i++) {
            int time = arrival[i];

            // Release servers have finished
            while (!busy.isEmpty() && busy.peek()[0] <= time) {
                int[] curr = busy.poll();
                available.add(curr[1]);
            }
            if (available.isEmpty()) {
                continue;
            }
            // Find preferred server
            int server = i % k;

            Integer s = available.ceiling(server);

            if (s == null) {
                s = available.first();
            }
            // request
            available.remove(s);
            count[s]++;
            busy.offer(new int[]{time + load[i], s});
        }
        int max = 0;

        for (int c : count) {
            max = Math.max(max, c);
        }
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            if (count[i] == max) {
                ans.add(i);
            }
        }
        return ans;
    }
}