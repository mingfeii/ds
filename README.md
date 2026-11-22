# 算法学习代码

本仓库用于跟随《算法》（Java 版）学习，并分别用 C++ 和 Scala 实现练习代码。

## 目录

- `cpp/`：C++23 + CMake 工程
  - `src/`：算法实现（目前包含二分查找示例）
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
