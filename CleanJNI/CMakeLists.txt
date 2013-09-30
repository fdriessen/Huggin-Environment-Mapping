cmake_minimum_required(VERSION 2.8 FATAL_ERROR)
project(PIONTCLOUDSTICHER)

find_package(PCL 1.2 REQUIRED)
find_package(Java REQUIRED)
find_package(JNI REQUIRED)

include_directories(${PCL_INCLUDE_DIRS})
include_directories(${JNI_INCLUDE_DIRS})

link_directories(${PCL_LIBRARY_DIRS})
add_definitions(${PCL_DEFINITIONS})

add_executable(../libPointCloudStitcher.jnilib udpReader_PointCloudStitcher.cpp)


target_link_libraries(../libPointCloudStitcher.jnilib ${PCL_LIBRARIES})