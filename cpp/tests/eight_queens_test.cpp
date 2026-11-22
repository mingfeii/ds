#include "eight_queens.hpp"

#include <cstdlib>
#include <iostream>
#include <set>

int main() {
    const auto solutions = algorithms::solve_eight_queens();
    if (solutions.size() != 92) {
        std::cerr << "Expected 92 solutions\n";
        return 1;
    }

    const std::set<algorithms::QueenSolution> unique(solutions.begin(), solutions.end());
    if (unique.size() != solutions.size()) {
        std::cerr << "Found duplicate solutions\n";
        return 1;
    }

    for (const auto& columns : solutions) {
        for (int row = 0; row < 8; ++row) {
            if (columns[row] < 0 || columns[row] >= 8) {
                std::cerr << "Queen outside the board\n";
                return 1;
            }
            for (int previous_row = 0; previous_row < row; ++previous_row) {
                if (columns[row] == columns[previous_row] ||
                    std::abs(row - previous_row) ==
                        std::abs(columns[row] - columns[previous_row])) {
                    std::cerr << "Queens attack each other\n";
                    return 1;
                }
            }
        }
    }
}
