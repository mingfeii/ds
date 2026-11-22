cmake_minimum_required(VERSION 3.28)
project(algorithms_cpp LANGUAGES CXX)

set(CMAKE_CXX_STANDARD 23)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
set(CMAKE_CXX_EXTENSIONS OFF)

include(CTest)

add_library(algorithms INTERFACE)
target_include_directories(algorithms INTERFACE ${CMAKE_CURRENT_SOURCE_DIR}/src)

add_executable(eight_queens examples/eight_queens.cpp)
target_link_libraries(eight_queens PRIVATE algorithms)

file(GLOB TEST_SOURCES CONFIGURE_DEPENDS tests/*.cpp)
foreach(test_source IN LISTS TEST_SOURCES)
    get_filename_component(test_name ${test_source} NAME_WE)
    add_executable(${test_name} ${test_source})
    target_link_libraries(${test_name} PRIVATE algorithms)
    add_test(NAME ${test_name} COMMAND ${test_name})
endforeach()
