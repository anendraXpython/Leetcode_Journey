class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        char[] arr = s.toCharArray();
        int[][] cost = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                cost[i][j] = palin(arr, i, j);
            }
        }

        int[] dp = new int[n + 1];
        Arrays.fill(dp, 1000000);
        dp[0] = 0;

        for (int j = 1; j <= k; j++) {
            for (int i = n; i >= 2 * j; i--) {
                int best = 1000000;
                for (int m = 2 * (j - 1); m <= i - 2; m++) {
                    best = Math.min(best, dp[m] + cost[m][i - 1]);
                }
                dp[i] = best;
            }
        }

        return dp[n];
    }

    public int palin(char[] arr, int start, int end) {
        int n = end - start + 1;
        int minc = Integer.MAX_VALUE;
        for (int d = 1; d <= n / 2; d++) {
            if (n % d != 0) continue;
            int c = 0;
            int g = n / d;
            for (int i = 0; i < d && c < minc; i++) {
                int left = i + start;
                int right = start + i + (g - 1) * d;
                while (left < right) {
                    if (arr[left] != arr[right]) c++;
                    left += d;
                    right -= d;
                }
            }
            minc = Math.min(minc, c);
        }
        return minc;
    }
}