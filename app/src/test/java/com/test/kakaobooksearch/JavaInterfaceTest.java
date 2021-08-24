package com.test.kakaobooksearch;

public class JavaInterfaceTest {

    private JavaInterface javaInterface;

    public void setJavaInterface(JavaInterface javaInterface) {
        this.javaInterface = javaInterface;
    }

    public void callJava(String msg){
        javaInterface.onJava(msg);
    }

    public interface JavaInterface {
        void onJava(String msg);
    }
}
