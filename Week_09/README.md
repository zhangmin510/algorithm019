# 学习笔记

## 动态规划代码模板

对于动态规划问题， 一般的代码模板如下。 对于不同的DP问题，难度在于状态数组的定义，以及递推公式的复杂度。
高级的动态规划可能是二维，甚至三维的数组。而且有时需要考虑状态压缩。

```java

public int dpProblem() {
    // 状态定义
    int[][] dp = new int[M][N];

    // 初始化
    for (int i = 0; i < M; i++) {
        d[i][0] = i;
    }

    // 递推
    for (int i = 0; i < M; i++) {
        for (int j = 0;j < N; j++) {
            dp[i][j] = fun(dp[i-1][j-1]); // 递推方程
        }
    }

    // 结果
    return dp[M - 1][N - 1];
}

```

## 动态规划递推公式总结


一维：
1. 斐波那契数列。 `dp[i] = dp[i - 1] + dp[i - 1]`

二维：
1. 路径问题。 `dp[i][j] = dp[i - 1][j] + f[i][j - 1]`
2. 最小路径问题.`dp[i][j] = min(dp[i - 1][j], dp[i][j - 1]) + A[i][j]`
3. 打家劫舍问题。`dp[i] = max(dp[i - 2] + nums[i], dp[i - 1])`或者`dp[i][0] = max(dp[i - 1][0], dp[i - 1][1]); dp[i][1] = dp[i - 1][0] + nums[i]`;
4. 最长子序列。`dp[i][j] = dp[i - 1][j - 1] + 1; dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])`
5. 最长子串.`dp[i][j] = dp[i - 1][j - 1] + 1; d[i][j] = 0`
6. 编辑距离。

三维：
1. 股票问题。`dp[i][k][s] = max(buy, sell, rest)`