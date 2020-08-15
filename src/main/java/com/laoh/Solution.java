package com.laoh;


import java.util.ArrayList;
import java.util.Collections;

class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

}
public class Solution {

    ArrayList<ArrayList<Integer>> res = new ArrayList<>();
    ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        helper(0, pRoot);
        ArrayList<ArrayList<Integer>> ans = new ArrayList();
        for (int i = 0; i < res.size(); i++) {
            if (i % 2 == 1) {
                Collections.reverse(res.get(i));

            }
            ans.add(res.get(i));
        }
        return ans;
    }
    void helper(int level, TreeNode node) {
        if (node == null) {
            return;
        }
        if (level >= res.size()) {
            ArrayList<Integer> list = new ArrayList<>();
            res.add(list);
        }
        res.get(level).add(node.val);
        helper(level + 1, node.left);
        helper(level + 1, node.right);
    }


}