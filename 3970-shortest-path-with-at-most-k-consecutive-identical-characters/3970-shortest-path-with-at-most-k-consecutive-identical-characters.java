class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        List<int[]>[] graph = new ArrayList[n];

        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for(int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            graph[u].add(new int[] {v, w});
        }
        long[][] dist = new long[n][k+1];
        for(long[]row : dist) {
            Arrays.fill(row, Long.MAX_VALUE);
        }
        PriorityQueue<long[]> pq = new PriorityQueue<>(
            (a, b) -> Long.compare(a[0], b[0])
        );
        dist[0][1] = 0;
        pq.offer(new long[] {0, 0, 1});
        while(! pq.isEmpty()) {
            long[] curr = pq.poll();
            long cost = curr[0];
            int node = (int)curr[1];
            int count = (int)curr[2];
            if(cost != dist[node][count]) {
                continue;
            }
            if(node == n-1) {
                return(int)cost;
            }
            for(int[] next : graph[node]) {
                int v = next[0];
                int weight = next[1];
                int newCount;
                if(labels.charAt(node) == labels.charAt(v)) {
                    newCount = count + 1;
                } else {
                    newCount = 1;
                }
                if(newCount>k) {
                    continue;
                }
                long newCost = cost + weight;

                if (newCost < dist[v][newCount]) {

                    dist[v][newCount] = newCost;
                    pq.offer(new long[] {newCost, v, newCount});
                }
            }
        }
        return-1;
    }
}

