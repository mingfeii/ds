#pragma once

#include <cstddef>
#include <optional>
#include <vector>

namespace algorithms {

template <typename T>
std::optional<std::size_t> binary_search(const std::vector<T>& values, const T& target) {
    std::size_t low = 0;
    std::size_t high = values.size();

    while (low < high) {
        const std::size_t middle = low + (high - low) / 2;
        if (values[middle] == target) {
            return middle;
        }
        if (values[middle] < target) {
            low = middle + 1;
        } else {
            high = middle;
        }
    }

    return std::nullopt;
}

} // namespace algorithms
