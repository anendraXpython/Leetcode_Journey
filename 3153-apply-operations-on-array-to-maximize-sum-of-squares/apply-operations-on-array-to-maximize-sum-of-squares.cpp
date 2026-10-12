class Solution {
public:
    int maxSum(vector<int>& nums, int k) {
        const long long MOD = 1'000'000'007;
        vector<int> bitCount(31, 0);

        for (int num : nums) {
            for (int bit = 0; bit < 31; bit++) {
                if (num & (1LL << bit)) {
                    bitCount[bit]++;
                }
            }
        }

        long long ans = 0;

        for (int i = 0; i < k; i++) {
            long long x = 0;

            for (int bit = 30; bit >= 0; bit--) {
                if (bitCount[bit] > 0) {
                    x |= (1LL << bit);
                    bitCount[bit]--;
                }
            }
            ans = (ans + x * x % MOD) % MOD;
        }

        return static_cast<int>(ans);
    }
};
