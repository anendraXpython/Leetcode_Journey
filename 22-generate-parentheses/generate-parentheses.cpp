class Solution {
public:
    void backtrack(int open, int close, int n, string current,
                   vector<string>& ans) {
        
        if (current.size() == 2 * n) {
            ans.push_back(current);
            return;
        }

        if (open < n) {
            backtrack(open + 1, close, n, current + "(", ans);
        }

        if (close < open) {
            backtrack(open, close + 1, n, current + ")", ans);
        }
    }

    vector<string> generateParenthesis(int n) {
        vector<string> ans;
        backtrack(0, 0, n, "", ans);
        return ans;
    }
};