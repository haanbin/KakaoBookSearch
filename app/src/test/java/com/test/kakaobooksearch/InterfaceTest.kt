package com.test.kakaobooksearch

import org.junit.jupiter.api.Test

class InterfaceTest {

    @Test
    fun javaTest() {
        val javaInterface = JavaInterfaceTest.JavaInterface {
            println("JavaInterface : $it")
        }
        val javaInterfaceTest = JavaInterfaceTest()
        javaInterfaceTest.setJavaInterface { println("setJavaInterface : $it") }

        javaInterfaceTest.callJava("callJava")
        javaInterface.onJava("onJava")
    }

    @Test
    fun kotlinTest() {
        val kotlinInterfaceFun = KotlinInterfaceTest.KotlinInterfaceFun {
            println("kotlinInterfaceFun : $it")
        }

        val kotlinInterfaceTest = KotlinInterfaceTest()
        kotlinInterfaceTest.setKotlinInterfaceFun {
            println("setKotlinInterfaceFun : $it")
        }
        kotlinInterfaceTest.setKotlinInterface(object : KotlinInterfaceTest.KotlinInterface {
            override fun onKotlin(msg: String) {
                println("setKotlinInterface : $msg")
            }
        })

        kotlinInterfaceFun.onKotlinFun("onKotlinFun")
        kotlinInterfaceTest.callInterface("callInterface")
    }
}

class KotlinInterfaceTest {

    private var kotlinInterfaceFun: KotlinInterfaceFun? = null
    private var kotlinInterface: KotlinInterface? = null

    fun setKotlinInterfaceFun(kotlinInterfaceFun: KotlinInterfaceFun) {
        this.kotlinInterfaceFun = kotlinInterfaceFun
    }

    fun setKotlinInterface(kotlinInterface: KotlinInterface) {
        this.kotlinInterface = kotlinInterface
    }

    fun callInterface(msg: String) {
        kotlinInterfaceFun?.onKotlinFun(msg)
        kotlinInterface?.onKotlin(msg)
    }

    fun interface KotlinInterfaceFun {
        fun onKotlinFun(msg: String)
    }

    interface KotlinInterface {
        fun onKotlin(msg: String)
    }
}


