#include <jni.h>
#include <iostream>
#include "HelloWorld.h"
using namespace std;
 
JNIEXPORT void JNICALL Java_HelloWorld_print(JNIEnv *, jobject){
    printf("Hello World!\n");
    return;
}


JNIEXPORT void JNICALL Java_HelloWorld_test(JNIEnv *env, jobject thisObj, jint a, jint b){
    printf("sum:%d\n", (a+b));
    return;
}