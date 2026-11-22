#pragma once

#include <array>
#include <cstdlib>
#include <functional>
#include <vector>

namespace algorithms {

// 下标表示行，值表示这一行皇后所在的列；行、列都从 0 开始。
// 例如 solution[2] = 5 表示第 3 行的皇后在第 6 列。
using QueenSolution = std::array<int, 8>;

inline std::vector<QueenSolution> solve_eight_queens() {
    std::vector<QueenSolution> solutions;
    QueenSolution columns;
    columns.fill(-1); // -1 表示这一行还没有放皇后。

    // 只检查前面的行：每行只放一个皇后，因此不会同行。
    const auto can_place = [&](int row, int col) {
        for (int previous_row = 0; previous_row < row; ++previous_row) {
            const int previous_col = columns[previous_row];
            if (previous_col == col) {
                return false; // 同列。
            }
            if (std::abs(row - previous_row) == std::abs(col - previous_col)) {
                return false; // 行距离等于列距离，说明在同一条对角线上。
            }
        }
        return true;
    };

    // dfs(row)：前 row 行已经放好，现在尝试放第 row 行。
    const std::function<void(int)> dfs = [&](int row) {
        if (row == 8) {
            solutions.push_back(columns); // 保存棋盘的副本，后续回溯不会改变它。
            return;
        }

        for (int col = 0; col < 8; ++col) {
            if (!can_place(row, col)) {
                continue; // 剪枝：这个位置冲突，不再向下搜索。
            }
            columns[row] = col;   // 1. 做选择：在当前位置放皇后。
            dfs(row + 1);        // 2. 深入：尝试下一行。
            columns[row] = -1;    // 3. 撤销选择：回到本行，尝试下一列。
        }
        // 所有列都试完了，返回上一层，让上一行的皇后换位置。
    };

    dfs(0);
    return solutions;
}

} // namespace algorithms
