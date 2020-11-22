# 学习笔记

## 递归

递归是一种特殊的循环，是通过函数体自己调用自己来进行循环。对于递归一定要记住三点思维误区需要避免：
一是不要试图人肉递归，很容易把自己绕进去无法自拔。
二是要寻找重复子问题，以为递归本质就是循环，必须找到重复子问题才能使用递归来解决。难点就是如何
找到重复子问题。
三是使用数学归纳法，从最简单的`n=1`和`n=2`的情况开始，然后推导`n到n+1`的情况。

分治是基于递归实现， 难点在于如何把一个大问题划分为子问题，要满足子问题解决后能够合并为原始问题的解。

回溯也是基于递归实现，但递归只是手段， 目的是试错，通过不断递归深入到下一层试错，如果不正确，则返回到上一层，改变
条件，继续进入下一层。如此反复从而找到正确的解。


## 代码模板

### 递归

```java
public void recursion(int level, int param, ...) {
    // terminator 递归终结者：处理结果，一定要记着返回。
    if (level > MAX_LEVEL) {
        // process result 处理结果
        return; // 一定要记着返回
    }
    // process current logic 逻辑处理：分治和回溯处理不同。 递归的参数根据不同问题而定
    process(level, param, ...); 
    // drill down 下一层：标识层数的加一，改变状态后的参数传到下一层
    recursion( level: level + 1, newParam);
    // restore current status 恢复状态：视情况，因为下钻前修改了状态。如果是局部变量，一般无需进行这一步
}
```

### 分治

```python
def divide_conquer(problem, param1, param2, ...):
    # recursion terminator 递归终结者
    if problem is None:
    print_result
    return
    # prepare data  准备数据，分解问题
    data = prepare_data(problem)
    subproblems = split_problem(problem, data)
    # conquer subproblems 递归解决子问题
    subresult1 = self.divide_conquer(subproblems[0], p1, ...)
    subresult2 = self.divide_conquer(subproblems[1], p1, ...)
    subresult3 = self.divide_conquer(subproblems[2], p1, ...)
    ...
    # process and generate the final result 合并结果
    result = process_result(subresult1, subresult2, subresult3, …)
    # revert the current level states 是否需要重置状态
```

### 回溯

```java
public void recur(int level, int param) {
    // terminator 递归终结 判断是不是问题的解
    if (level > MAX_LEVEL) {
        // process result
        return;
    }
    // process current logic 处理逻辑，变换条件
    process(level, param);
    // drill down 下一层
    recur( level: level + 1, newParam);
    // restore current status 重置状态， 继续试错
}
```