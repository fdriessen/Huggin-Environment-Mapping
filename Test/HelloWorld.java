class HelloWorld 
{
  public native void print();
  static 
  {
    System.loadLibrary("HelloWorld"); 
  }
  public native void test(int a, int b);

}