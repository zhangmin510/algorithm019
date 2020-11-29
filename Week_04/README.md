# 学习笔记

## DFS vs BFS

### DFS递归代码模板
递归实现。helper函数，传入当前深度。

```java
    public List<List<Integer>> levelOrder(TreeNode root) {
        // 1. 结果申明
        List<List<Integer>> allResults = new ArrayList<>();
        // 2. 参数校验
        if(root==null){
            return allResults;
        }
        // 3. 调用helper函数实现DFS，记录层数
        travel(root,0,allResults);
        return allResults;
    }

    private void travel(TreeNode root,int level,List<List<Integer>> results){
        // 4. 初始化每一层结果
        if(results.size()==level){
            results.add(new ArrayList<>());
        }
        // 5. 记录每一层的结果
        results.get(level).add(root.val);
        // 6. 继续向下递归遍历，层数加一
        if(root.left!=null){
            travel(root.left,level+1,results);
        }
        if(root.right!=null){
            travel(root.right,level+1,results);
        }
    }
```


### DFS非递归代码模板

使用栈来模拟递归。

```java
   // 前序遍历非递归实现
   public List<Integer> preOrder(Node root) {
        // 1. 结果申明
        List<Integer> result = new ArrayList<>();

        // 2. 参数校验
        if (root == null) {
            return result;
        }

        // 3. 申明栈，放入初始节点
        Stack<Node> stack = new Stack<>();
        stack.add(root);
        
        // 4. 遍历栈，直到非空
        while(!stack.isEmpty()) {
            // 5. 遍历栈顶元素
            Node tmp = stack.pop();
            result.add(tmp.val);
            // 6. 将子节点放入栈中
            for (int i = tmp.children.size() - 1; i >= 0; i--) {
                stack.push(tmp.children.get(i));
            }
        }
        return result;
    }

```

### BFS代码模板

核心思想使用队列，每一层节点放入到队列中，可以通过一个变量记录每一层的节点数目，从而获取每层的遍历结果。

```java
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public List<List<Integer>> levelOrder(TreeNode root) {
    // 1. 结果申明
    List<List<Integer>> allResults = new ArrayList<>();

    // 2. 参数判空
    if (root == null) {
        return allResults;
    }

    // 3. 队列申明
    Queue<TreeNode> nodes = new LinkedList<>();

    // 4. 队列中放入起始节点
    nodes.add(root);

    // 5. 开始遍历队列，直到队列为空
    while (!nodes.isEmpty()) {
        // 6. 临时变量，记录每层的结果及每层的节点数目
        int size = nodes.size();
        List<Integer> results = new ArrayList<>();
        // 7. 遍历当前层中每个节点，将其子节点放入到队列中
        for (int i = 0; i < size; i++) {
            TreeNode node = nodes.poll();
            results.add(node.val);
            if (node.left != null) {
                nodes.add(node.left);
            }
            if (node.right != null) {
                nodes.add(node.right);
            }
        }
        // 8. 更新最终结果
        allResults.add(results);
    }
    return allResults;
}

```

## 二分查找

能够进行二分查找的对象必须满足：
1. 单调性。 必须是有序的。
2. 必须能够可以通过下标来访问。
3. 有上下界。 才能够确定中间元素是哪一个。
   

代码模板

```java
public int binarySearch(int[] array, int target) {
    // 1. 申明左中右的变量
    int left = 0, right = array.length - 1, mid;

    // 2. 循环遍历，终止条件：左小于等于右（递增情况下）
    while (left <= right) {
        // 3. 确定中间下标
        mid = (right - left) / 2 + left;

        // 4. 比较，更新左或者右的下标
        if (array[mid] == target) {
            return mid;
        } else if (array[mid] > target) {
            right = mid - 1;
        } else {
            left = mid + 1;
        }
    }

    return -1;
}

```

### 二分查找变形问题

使用二分查找，寻找一个半有序数组 [4, 5, 6, 7, 0, 1, 2] 中间无序的地方

主要思路
1. 找到旋转点。通过一层循环找到旋转点
2. 然后确定目标值在哪一半有序的数组中
3. 进行二分查找


```java
class Solution {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length < 1) {
            return -1;
        }
        int i = 0, len = nums.length;
        int lo = 0, hi = len - 1;
        // 确定旋转点
        while (i < len - 1 && nums[i] < nums[i + 1]) {
            i++;
        }
        
        // 确定在哪一半有序数组中进行二分查找
        if (nums[0] <= target && nums[i] >= target) {
            lo = 0;
            hi = i;
        } else if (nums[len - 1] >= target) {
            lo = i + 1;
            hi = len - 1;
        } else {
            return -1;
        }

        // 二分查找
        while (lo <= hi) {
            int mid = lo + ((hi - lo) >> 1);
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
               hi = mid - 1;
            } else {
               lo = mid + 1; 
            }
        }
        return -1;
    }
}
```

## 贪心算法

贪心算法的基本原理是在算法的每一步都选择当前状态最优的结果，并进行下一步。核心需要证明
使用局部最优解能够得到最终的全局最优解。这一步往往需要数学证明。

贪心算法不记录当前状态，不进行回退，一条道走到黑。所以一定要在理论上证明这条道路是正确的。