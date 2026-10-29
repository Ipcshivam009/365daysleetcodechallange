import java.util.*;
class Solution {
    int[][] up;
    int[] tin, tout, depth, bit;
    int LOG, timer;
    List<Integer>[] graph;

    void add(int i, int value) {
        while (i < bit.length) {
            bit[i] ^= value;
            i += i & -i;
        }
    }
    int sum(int i) {
        int result = 0;
        while (i > 0) {
            result ^= bit[i];
            i -= i & -i;
        }
        return result;
    }
    int lca(int a, int b) {
        if (depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }
        int diff = depth[a] - depth[b];

        for (int k = 0; k < LOG; k++) {
            if ((diff & (1 << k)) != 0) {
                a = up[k][a];
            }
        }
        if (a == b) return a;

        for (int k = LOG - 1; k >= 0; k--) {
            if (up[k][a] != up[k][b]) {
                a = up[k][a];
                b = up[k][b];
            }
        }
        return up[0][a];
    }
    public List<Boolean> palindromePath(
            int n, int[][] edges, String s, String[] queries) {

        LOG = 1;
        while ((1 << LOG) <= n) LOG++;

        graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        tin = new int[n];
        tout = new int[n];
        depth = new int[n];
        up = new int[LOG][n];

        // Iterative DFS
        int[] parent = new int[n];
        int[] iterator = new int[n];
        int[] stack = new int[n];

        Arrays.fill(parent, -1);
        int top = 0;
        stack[top++] = 0;
        parent[0] = 0;
        tin[0] = ++timer;

        while (top > 0) {
            int u = stack[top - 1];
            if (iterator[u] < graph[u].size()) {
                int v = graph[u].get(iterator[u]++);

                if (v == parent[u]) continue;
                parent[v] = u;
                depth[v] = depth[u] + 1;
                up[0][v] = u;

                for (int k = 1; k < LOG; k++) {
                    up[k][v] =
                        up[k - 1][up[k - 1][v]];
                }
                tin[v] = ++timer;
                stack[top++] = v;
            } else {
                tout[u] = timer;
                top--;
            }
        }
        bit = new int[n + 2];
        char[] chars = s.toCharArray();
        for (int u = 0; u < n; u++) {
            int mask = 1 << (chars[u] - 'a');
            add(tin[u], mask);
            add(tout[u] + 1, mask);
        }
        List<Boolean> answer = new ArrayList<>();
        for (String query : queries) {
            String[] parts = query.split(" ");
            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);
                int change =
                    (1 << (chars[u] - 'a'))
                    ^ (1 << (c - 'a'));
                add(tin[u], change);
                add(tout[u] + 1, change);
                chars[u] = c;
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);
                int w = lca(u, v);
                int mask =
                    sum(tin[u])
                    ^ sum(tin[v])
                    ^ (1 << (chars[w] - 'a'));
                answer.add(
                    (mask & (mask - 1)) == 0);
            }
        }
        return answer;
    }
}
