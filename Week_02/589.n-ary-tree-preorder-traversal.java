/*
 * @lc app=leetcode id=589 lang=java
 *
 * [589] N-ary Tree Preorder Traversal
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
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
 
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
 
        while (!queue.isEmpty()) {
            List<Integer> levelResult = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                levelResult.add(node.val);
                queue.addAll(node.children);
            }
            result.add(levelResult);
        }
        return result; 
    }
}
// @lc code=end

