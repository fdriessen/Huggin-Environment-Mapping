# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 2.8

#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:

# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list

# Suppress display of executed commands.
$(VERBOSE).SILENT:

# A target that is always out of date.
cmake_force:
.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /opt/local/bin/cmake

# The command to remove a file.
RM = /opt/local/bin/cmake -E remove -f

# Escaping for special characters.
EQUALS = =

# The program to use to edit the cache.
CMAKE_EDIT_COMMAND = /opt/local/bin/ccmake

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /Users/tijlvanvliet/Documents/Quadcopter/CleanJNI

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /Users/tijlvanvliet/Documents/Quadcopter/CleanJNI/build

# Include any dependencies generated for this target.
include CMakeFiles/../libPointCloudStitcher.jnilib.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/../libPointCloudStitcher.jnilib.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/../libPointCloudStitcher.jnilib.dir/flags.make

CMakeFiles/../libPointCloudStitcher.jnilib.dir/udpReader_PointCloudStitcher.cpp.o: CMakeFiles/../libPointCloudStitcher.jnilib.dir/flags.make
CMakeFiles/../libPointCloudStitcher.jnilib.dir/udpReader_PointCloudStitcher.cpp.o: ../udpReader_PointCloudStitcher.cpp
	$(CMAKE_COMMAND) -E cmake_progress_report /Users/tijlvanvliet/Documents/Quadcopter/CleanJNI/build/CMakeFiles $(CMAKE_PROGRESS_1)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Building CXX object CMakeFiles/../libPointCloudStitcher.jnilib.dir/udpReader_PointCloudStitcher.cpp.o"
	/usr/bin/g++   $(CXX_DEFINES) $(CXX_FLAGS) -o CMakeFiles/../libPointCloudStitcher.jnilib.dir/udpReader_PointCloudStitcher.cpp.o -c /Users/tijlvanvliet/Documents/Quadcopter/CleanJNI/udpReader_PointCloudStitcher.cpp

CMakeFiles/../libPointCloudStitcher.jnilib.dir/udpReader_PointCloudStitcher.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/../libPointCloudStitcher.jnilib.dir/udpReader_PointCloudStitcher.cpp.i"
	/usr/bin/g++  $(CXX_DEFINES) $(CXX_FLAGS) -E /Users/tijlvanvliet/Documents/Quadcopter/CleanJNI/udpReader_PointCloudStitcher.cpp > CMakeFiles/../libPointCloudStitcher.jnilib.dir/udpReader_PointCloudStitcher.cpp.i

CMakeFiles/../libPointCloudStitcher.jnilib.dir/udpReader_PointCloudStitcher.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/../libPointCloudStitcher.jnilib.dir/udpReader_PointCloudStitcher.cpp.s"
	/usr/bin/g++  $(CXX_DEFINES) $(CXX_FLAGS) -S /Users/tijlvanvliet/Documents/Quadcopter/CleanJNI/udpReader_PointCloudStitcher.cpp -o CMakeFiles/../libPointCloudStitcher.jnilib.dir/udpReader_PointCloudStitcher.cpp.s

CMakeFiles/../libPointCloudStitcher.jnilib.dir/udpReader_PointCloudStitcher.cpp.o.requires:
.PHONY : CMakeFiles/../libPointCloudStitcher.jnilib.dir/udpReader_PointCloudStitcher.cpp.o.requires

CMakeFiles/../libPointCloudStitcher.jnilib.dir/udpReader_PointCloudStitcher.cpp.o.provides: CMakeFiles/../libPointCloudStitcher.jnilib.dir/udpReader_PointCloudStitcher.cpp.o.requires
	$(MAKE) -f CMakeFiles/../libPointCloudStitcher.jnilib.dir/build.make CMakeFiles/../libPointCloudStitcher.jnilib.dir/udpReader_PointCloudStitcher.cpp.o.provides.build
.PHONY : CMakeFiles/../libPointCloudStitcher.jnilib.dir/udpReader_PointCloudStitcher.cpp.o.provides

CMakeFiles/../libPointCloudStitcher.jnilib.dir/udpReader_PointCloudStitcher.cpp.o.provides.build: CMakeFiles/../libPointCloudStitcher.jnilib.dir/udpReader_PointCloudStitcher.cpp.o

# Object files for target ../libPointCloudStitcher.jnilib
__/libPointCloudStitcher_jnilib_OBJECTS = \
"CMakeFiles/../libPointCloudStitcher.jnilib.dir/udpReader_PointCloudStitcher.cpp.o"

# External object files for target ../libPointCloudStitcher.jnilib
__/libPointCloudStitcher_jnilib_EXTERNAL_OBJECTS =

../libPointCloudStitcher.jnilib: CMakeFiles/../libPointCloudStitcher.jnilib.dir/udpReader_PointCloudStitcher.cpp.o
../libPointCloudStitcher.jnilib: CMakeFiles/../libPointCloudStitcher.jnilib.dir/build.make
../libPointCloudStitcher.jnilib: /opt/local/lib/libboost_system-mt.dylib
../libPointCloudStitcher.jnilib: /opt/local/lib/libboost_filesystem-mt.dylib
../libPointCloudStitcher.jnilib: /opt/local/lib/libboost_thread-mt.dylib
../libPointCloudStitcher.jnilib: /opt/local/lib/libboost_date_time-mt.dylib
../libPointCloudStitcher.jnilib: /opt/local/lib/libboost_iostreams-mt.dylib
../libPointCloudStitcher.jnilib: /usr/local/lib/libpcl_common.dylib
../libPointCloudStitcher.jnilib: /usr/local/lib/libpcl_octree.dylib
../libPointCloudStitcher.jnilib: /opt/local/lib/vtk-5.10/libvtkCommon.5.10.0.dylib
../libPointCloudStitcher.jnilib: /opt/local/lib/vtk-5.10/libvtkRendering.5.10.0.dylib
../libPointCloudStitcher.jnilib: /opt/local/lib/vtk-5.10/libvtkHybrid.5.10.0.dylib
../libPointCloudStitcher.jnilib: /usr/local/lib/libpcl_io.dylib
../libPointCloudStitcher.jnilib: /opt/local/lib/libflann_cpp_s.a
../libPointCloudStitcher.jnilib: /usr/local/lib/libpcl_kdtree.dylib
../libPointCloudStitcher.jnilib: /usr/local/lib/libpcl_search.dylib
../libPointCloudStitcher.jnilib: /usr/local/lib/libpcl_sample_consensus.dylib
../libPointCloudStitcher.jnilib: /usr/local/lib/libpcl_filters.dylib
../libPointCloudStitcher.jnilib: /usr/local/lib/libpcl_segmentation.dylib
../libPointCloudStitcher.jnilib: /usr/local/lib/libpcl_visualization.dylib
../libPointCloudStitcher.jnilib: /usr/local/lib/libpcl_features.dylib
../libPointCloudStitcher.jnilib: /usr/local/lib/libqhullstatic.a
../libPointCloudStitcher.jnilib: /usr/local/lib/libpcl_surface.dylib
../libPointCloudStitcher.jnilib: /usr/local/lib/libpcl_registration.dylib
../libPointCloudStitcher.jnilib: /usr/local/lib/libpcl_keypoints.dylib
../libPointCloudStitcher.jnilib: /usr/local/lib/libpcl_tracking.dylib
../libPointCloudStitcher.jnilib: /usr/local/lib/libpcl_apps.dylib
../libPointCloudStitcher.jnilib: /opt/local/lib/vtk-5.10/libvtkRendering.5.10.0.dylib
../libPointCloudStitcher.jnilib: /opt/local/lib/vtk-5.10/libvtkGraphics.5.10.0.dylib
../libPointCloudStitcher.jnilib: /opt/local/lib/vtk-5.10/libvtkImaging.5.10.0.dylib
../libPointCloudStitcher.jnilib: /opt/local/lib/libQtGui.dylib
../libPointCloudStitcher.jnilib: /opt/local/lib/libQtSql.dylib
../libPointCloudStitcher.jnilib: /opt/local/lib/libQtCore.dylib
../libPointCloudStitcher.jnilib: /opt/local/lib/vtk-5.10/libvtkIO.5.10.0.dylib
../libPointCloudStitcher.jnilib: /opt/local/lib/vtk-5.10/libvtkFiltering.5.10.0.dylib
../libPointCloudStitcher.jnilib: /opt/local/lib/vtk-5.10/libvtkCommon.5.10.0.dylib
../libPointCloudStitcher.jnilib: /opt/local/lib/vtk-5.10/libvtksys.5.10.0.dylib
../libPointCloudStitcher.jnilib: CMakeFiles/../libPointCloudStitcher.jnilib.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --red --bold "Linking CXX executable ../libPointCloudStitcher.jnilib"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/../libPointCloudStitcher.jnilib.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/../libPointCloudStitcher.jnilib.dir/build: ../libPointCloudStitcher.jnilib
.PHONY : CMakeFiles/../libPointCloudStitcher.jnilib.dir/build

CMakeFiles/../libPointCloudStitcher.jnilib.dir/requires: CMakeFiles/../libPointCloudStitcher.jnilib.dir/udpReader_PointCloudStitcher.cpp.o.requires
.PHONY : CMakeFiles/../libPointCloudStitcher.jnilib.dir/requires

CMakeFiles/../libPointCloudStitcher.jnilib.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/../libPointCloudStitcher.jnilib.dir/cmake_clean.cmake
.PHONY : CMakeFiles/../libPointCloudStitcher.jnilib.dir/clean

CMakeFiles/../libPointCloudStitcher.jnilib.dir/depend:
	cd /Users/tijlvanvliet/Documents/Quadcopter/CleanJNI/build && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /Users/tijlvanvliet/Documents/Quadcopter/CleanJNI /Users/tijlvanvliet/Documents/Quadcopter/CleanJNI /Users/tijlvanvliet/Documents/Quadcopter/CleanJNI/build /Users/tijlvanvliet/Documents/Quadcopter/CleanJNI/build /Users/tijlvanvliet/Documents/Quadcopter/CleanJNI/build/libPointCloudStitcher.jnilib.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/../libPointCloudStitcher.jnilib.dir/depend
