
compile:
	javac main.java
	javac HelloWorld.java
	javah -jni HelloWorld
	#g++ -G -I/System/Library/Frameworks/JavaVM.framework/Headers HelloWorld.cpp -dynamiclib -o HelloWorld.o
	g++ -G -I/System/Library/Frameworks/JavaVM.framework/Headers HelloWorld.cpp -dynamiclib -o libhelloworld.jnilib