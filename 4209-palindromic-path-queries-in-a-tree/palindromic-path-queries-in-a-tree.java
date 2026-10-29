import java.util.*;

class Solution {
    private int LOG;
    private int[] tin, tout, depth, parent;
    private int[][] up;
    private int[] bit;
    private int timer;

    public List<Boolean> palindromePath(
            int n, int[][] edges, String s, String[] queries) {

        List<Integer>[] adj = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            adj[u].add(v);
            adj[v].add(u);
        }

        LOG = 1;
        while ((1 << LOG) <= n) {
            LOG++;
        }

        tin = new int[n];
        tout = new int[n];
        depth = new int[n];
        parent = new int[n];
        up = new int[LOG][n];
        timer = 0;

        // Iterative DFS avoids stack overflow on a deep tree.
        int[] stack = new int[2 * n + 5];
        int top = 0;
        stack[top++] = 0;

        parent[0] = 0;

        while (top > 0) {
            int x = stack[--top];

            if (x < 0) {
                int u = ~x;
                tout[u] = timer;
                continue;
            }

            int u = x;
            tin[u] = ++timer;

            for (int k = 0; k < LOG; k++) {
                if (k == 0) {
                    up[k][u] = parent[u];
                } else {
                    up[k][u] = up[k - 1][up[k - 1][u]];
                }
            }

            stack[top++] = ~u;

            for (int v : adj[u]) {
                if (v == parent[u]) {
                    continue;
                }

                parent[v] = u;
                depth[v] = depth[u] + 1;
                stack[top++] = v;
            }
        }

        bit = new int[n + 2];

        char[] chars = s.toCharArray();

        // Initialize root-to-node character parity masks.
        for (int u = 0; u < n; u++) {
            int mask = 1 << (chars[u] - 'a');
            rangeXor(tin[u], tout[u], mask);
        }

        List<Boolean> answer = new ArrayList<>();

        for (String query : queries) {
            String[] parts = query.split(" ");

            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);

                if (chars[u] == c) {
                    continue;
                }

                int oldMask = 1 << (chars[u] - 'a');
                int newMask = 1 << (c - 'a');

                // Toggle the changed character throughout this subtree.
                rangeXor(tin[u], tout[u], oldMask ^ newMask);

                chars[u] = c;

            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int lca = getLca(u, v);

                int maskU = prefixXor(tin[u]);
                int maskV = prefixXor(tin[v]);
                int lcaMask = 1 << (chars[lca] - 'a');

                int pathMask = maskU ^ maskV ^ lcaMask;

                // A palindrome permutation allows at most one odd frequency.
                answer.add(Integer.bitCount(pathMask) <= 1);
            }
        }

        return answer;
    }

    private int getLca(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for (int k = 0; k < LOG; k++) {
            if (((diff >> k) & 1) != 0) {
                u = up[k][u];
            }
        }

        if (u == v) {
            return u;
        }

        for (int k = LOG - 1; k >= 0; k--) {
            if (up[k][u] != up[k][v]) {
                u = up[k][u];
                v = up[k][v];
            }
        }

        return parent[u];
    }

    private void rangeXor(int left, int right, int mask) {
        add(left, mask);
        add(right + 1, mask);
    }

    private void add(int index, int mask) {
        while (index < bit.length) {
            bit[index] ^= mask;
            index += index & -index;
        }
    }

    private int prefixXor(int index) {
        int result = 0;

        while (index > 0) {
            result ^= bit[index];
            index -= index & -index;
        }

        return result;
    }
}