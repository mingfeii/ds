#include "binary_search.hpp"

#include <cassert>
#include <vector>

int main() {
    const std::vector<int> values{1, 3, 5, 7, 9};

    assert(algorithms::binary_search(values, 1) == 0);
    assert(algorithms::binary_search(values, 5) == 2);
    assert(algorithms::binary_search(values, 9) == 4);
    assert(!algorithms::binary_search(values, 4));
}
