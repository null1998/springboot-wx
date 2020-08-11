package com.laoh;


public class Solution {

    int index;
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }
    String Serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preOrder(root, sb);
        return sb.toString();
    }
    void preOrder(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("#");
        }
        sb.append(root.val+"!");
        preOrder(root.left, sb);
        preOrder(root.right, sb);
    }
    TreeNode Deserialize(String str) {
        index = 0;
        TreeNode node = deser(str);
        index = 0;
        return node;
    }
    TreeNode deser(String str) {
        if (str.charAt(index) == '#') {
            index++;
            return null;
        }
        String s = "";
        while (str.charAt(index) != '!') {
            s += str.charAt(index);
            index++;
        }
        index++;
        TreeNode node = new TreeNode(Integer.valueOf(s));
        node.left = deser(str);
        node.right = deser(str);
        return node;
    }
}