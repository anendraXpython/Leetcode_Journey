/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
public:
    vector<int> rightSideView(TreeNode* root) {
        queue<TreeNode*> qu;
        vector<int> ans;

        if(root == NULL){
            return ans;
        }

        qu.push(root);
        qu.push(NULL);

        while(!qu.empty()){
            TreeNode* cur = qu.front();
            qu.pop();

            if(qu.front() == NULL && cur != NULL){
                ans.push_back(cur->val);
            }

            if(cur == NULL){
                if(!qu.empty()){
                    qu.push(NULL);
                }
                continue; 
            }

            if(cur->left){
                qu.push(cur->left);
            }

            if(cur->right){
                qu.push(cur->right);
            }
        }

        return ans;
    }
};