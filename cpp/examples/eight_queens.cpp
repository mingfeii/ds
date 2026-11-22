#include "eight_queens.hpp"

#include <iostream>

int main() {
    const auto solutions = algorithms::solve_eight_queens();
    std::cout << "Total solutions: " << solutions.size() << '\n';

    if (!solutions.empty()) {
        std::cout << "First solution (Q = queen, . = empty):\n";
        for (int row = 0; row < 8; ++row) {
            for (int col = 0; col < 8; ++col) {
                std::cout << (solutions.front()[row] == col ? "Q " : ". ");
            }
            std::cout << '\n';
        }
    }
}
