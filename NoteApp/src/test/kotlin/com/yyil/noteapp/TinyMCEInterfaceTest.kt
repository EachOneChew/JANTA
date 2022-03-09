package com.yyil.noteapp

import javafx.stage.Stage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.testfx.framework.junit5.ApplicationTest

internal class TinyMCEInterfaceTest : ApplicationTest() {
    var tinyMCE : TinyMCEInterface? = null

    override fun start(stage: Stage?) {
        tinyMCE = TinyMCEInterface("HUAK HUAK", fun(s : String) {})
    }

    /**
     * Delays execution of tests so editor can initialize properly
     */
    @BeforeEach
    fun delayForInit() {
        runBlocking {
            launch {
                delay(1000L)
            }
        }
    }

    @Test
    fun doSomething() {
        println(tinyMCE!!.content)
    }
}
