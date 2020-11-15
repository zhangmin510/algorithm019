/*
 * @lc app=leetcode id=429 lang=java
 *
 * [429] N-ary Tree Level Order Traversal
 */

// @lc code=start
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    public List<Integer> preorder(Node root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Stack<Node> stack = new Stack<>();
        stack.add(root);
        
        while(!stack.isEmpty()) {
            Node tmp = stack.pop();
            result.add(tmp.val);
            for (int i = tmp.children.size() - 1; i >= 0; i--) {
                stack.push(tmp.children.get(i));
            }
        }
        return result;
    }

    public List<Integer> preorder0(Node root) {
        List<Integer> result = new ArrayList<>();
        preorder(root, result);
        return result;
    }

    private void preorder(Node root, List<Integer> result) {
        if (root == null) {
            return;
        }
        result.add(root.val);
        for (Node node : root.children) {
            preorder(node, result);
        }
    }
}
// @lc code=end

