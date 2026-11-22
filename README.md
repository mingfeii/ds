# 算法学习代码

本仓库用于跟随《算法》（Java 版）学习，并分别用 C++ 和 Scala 实现练习代码。

## 目录

- `cpp/`：C++23 + CMake 工程
  - `src/`：算法实现（二分查找、八皇后）
  - `examples/`：可直接运行的演示
  - `tests/`：测试；新增 `tests/*.cpp` 后，CMake 会自动创建并注册对应测试
- `scala/`：Scala 2.13.14 + sbt 工程
  - `src/main/scala/`：算法实现
  - `src/test/scala/`：测试（使用 MUnit）

## C++

在仓库根目录执行：

```bash
cmake -S cpp -B cpp/build
cmake --build cpp/build
ctest --test-dir cpp/build --output-on-failure
```

新增算法头文件放在 `cpp/src/`，新增测试放在 `cpp/tests/`；之后重新执行构建命令即可。

## Scala

在仓库根目录执行：

```bash
cd scala
sbt test
```

首次运行 sbt 会下载 Scala 编译器和 MUnit 测试依赖，因此需要能访问 Maven Central 等远程仓库。网络恢复或连接 VPN 后再运行上述命令即可；依赖下载完成后，后续构建通常会使用本地缓存。

新增实现放在 `scala/src/main/scala/`，测试放在 `scala/src/test/scala/`。

## 八皇后：学习 DFS 和回溯

目标是在 8 × 8 的棋盘上放 8 个皇后，让任意两个皇后都不同行、不同列、不同对角线。

- C++ 实现：[`cpp/src/eight_queens.hpp`](cpp/src/eight_queens.hpp)
- Scala 2 实现：[`scala/src/main/scala/algorithms/EightQueens.scala`](scala/src/main/scala/algorithms/EightQueens.scala)

两份实现使用相同的思路，方便对照阅读。`columns(row)`（C++ 中为 `columns[row]`）表示第 `row` 行的皇后放在哪一列。每层递归只负责一行，所以天然不会同行。

`dfs(row)` 的含义是：前 `row` 行已放好皇后，现在尝试第 `row` 行。从左到右尝试每一列，检查它是否与前面放好的皇后冲突。同列会冲突；行距离等于列距离时，说明在同一条对角线上，也会冲突。

每次找到合法位置，就重复三个步骤：

1. **做选择**：记录这一行皇后所在的列。
2. **深入搜索**：调用 `dfs(row + 1)`，尝试下一行。
3. **撤销选择**：递归返回后清空这一行，再尝试下一列。

沿着一个选择不断走到更深的行，就是 **DFS（深度优先搜索）**；返回后撤销选择、尝试其他分支，就是**回溯**；发现冲突便跳过该位置，就是**剪枝**。如果一行的所有列都不合法，循环结束，函数自然返回上一层，不需要额外处理。

当 `row == 8` 时，说明第 0～7 行都放好了，保存当前解的副本并返回。之后继续搜索其他分支，最终得到 **92 个解**（旋转、镜像后的不同棋盘也计数）。两份实现都扫描前面的皇后来检查冲突，便于理解；暂不使用位运算等优化。

运行 C++ 演示（在仓库根目录）：

```bash
cmake -S cpp -B cpp/build
cmake --build cpp/build
./cpp/build/eight_queens
```

运行 Scala 演示（在 `scala/` 目录）：

```bash
sbt "runMain algorithms.EightQueensDemo"
```

演示输出解的总数和第一个解的棋盘，`Q` 表示皇后，`.` 表示空格。建议在 `dfs` 入口和撤销选择处打断点，观察 `row` 和 `columns` 如何随递归变化。
