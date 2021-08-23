package com.test.kakaobooksearch

import org.junit.jupiter.api.Test

class InterfaceTest {

    @Test
    fun test() {
        val kotlinInterfaceTest = KotlinInterfaceTest()
        /*kotlinInterfaceTest.setKotlinInterface {

        }*/
        kotlinInterfaceTest.setKotlinInterface(object : KotlinInterfaceTest.KotlinInterface {
            override fun onKotlin(msg: String) {
                println("KotlinInterfaceFun : $msg")
            }
        })
        kotlinInterfaceTest.setKotlinInterfaceFun {
            println("KotlinInterfaceFun : $it")
        }
        kotlinInterfaceTest.setJavaInterface {
            println("javaInterface : $it")
        }
        kotlinInterfaceTest.callInterface()
    }
}

class KotlinInterfaceTest {

    private var kotlinInterfaceFun: KotlinInterfaceFun? = null
    private var kotlinInterface: KotlinInterface? = null
    private var javaInterface: JavaInterfaceTest.JavaInterface? = null

    fun setKotlinInterfaceFun(kotlinInterfaceFun: KotlinInterfaceFun) {
        this.kotlinInterfaceFun = kotlinInterfaceFun
    }

    fun setKotlinInterface(kotlinInterface: KotlinInterface) {
        this.kotlinInterface = kotlinInterface
    }

    fun setJavaInterface(javaInterface: JavaInterfaceTest.JavaInterface) {
        this.javaInterface = javaInterface
    }

    fun callInterface() {
        kotlinInterfaceFun?.onKotlinFun("kotlinInterfaceFun")
        kotlinInterface?.onKotlin("kotlinInterface")
        javaInterface?.onJava("javaInterface")
    }

    fun interface KotlinInterfaceFun {
        fun onKotlinFun(msg: String)
    }

    interface KotlinInterface {
        fun onKotlin(msg: String)
    }
}


