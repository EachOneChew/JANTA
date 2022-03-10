package com.yyil.noteapp

import javafx.stage.Stage
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.testfx.framework.junit5.ApplicationTest

internal class TinyMCEInterfaceTest : ApplicationTest() {
    var tinyMCE: TinyMCEInterface? = null

    val testString: String = "<p>1234567890asdfghjkl</p>"
    val testSkin: String = "oxide"
    val testContentCSS: String = "default"

    var testCallModel: String = ""

    override fun start(stage: Stage?) {
        tinyMCE = TinyMCEInterface("", fun(_: String) {
            testCallModel = "CALLED"
        })
    }

    /**
     * Delays execution of tests so editor can initialize properly
     */
    @BeforeEach
    fun delayForInit() {
        if (!tinyMCE!!.isActive) {
            delayOneSecond()
            assertEquals(tinyMCE!!.isActive, true)
        }
    }

    /**
     * Editor should be destroyed when calling destroy
     */
    @Test
    fun initDestroy() {
        interact {
            tinyMCE!!.destroyEditor()
        }
        delayOneSecond()
        interact {
            assertEquals(false, tinyMCE!!.isActive)
        }
    }

    /**
     * Nothing should break when initializing twice
     */
    @Test
    fun initTwice() {
        interact {
            tinyMCE!!.initEditor("")
        }
        delayOneSecond()
        interact {
            assertEquals(true, tinyMCE!!.isActive)
        }
        interact {
            tinyMCE!!.initEditor("")
        }
        delayOneSecond()
        interact {
            assertEquals(true, tinyMCE!!.isActive)
        }
    }

    /**
     * Should initialize with text if passed in
     */
    @Test
    fun initText() {
        interact {
            tinyMCE!!.initEditor(testString)
        }
        delayOneSecond()
        interact {
            assertEquals(testString, tinyMCE!!.content)
        }
    }

    /**
     * Nothing should break when destroying twice
     */
    @Test
    fun destroyTwice() {
        interact {
            tinyMCE!!.destroyEditor()
        }
        delayOneSecond()
        interact {
            assertEquals(false, tinyMCE!!.isActive)
        }
        interact {
            tinyMCE!!.destroyEditor()
        }
        delayOneSecond()
        interact {
            assertEquals(false, tinyMCE!!.isActive)
        }
    }

    /**
     * Should be able to change editor content through interface
     */
    @Test
    fun setEditorContent() {
        interact {
            tinyMCE!!.content = testString
        }
        delayOneSecond()
        interact {
            assertEquals(testString, tinyMCE!!.content)
        }
    }

    /**
     * Should be able to change editor selection through interface
     */
    @Test
    fun setEditorSelection() {
        interact {
            tinyMCE!!.selection = testString
        }
        delayOneSecond()
        interact {
            assertEquals(testString, tinyMCE!!.content)
        }
    }

    /**
     * Should be able to change editor skin through interface
     */
    @Test
    fun setEditorSkin() {
        interact {
            tinyMCE!!.skin = testSkin
        }
        delayOneSecond()
        interact {
            assertEquals(testSkin, tinyMCE!!.skin)
        }
    }

    /**
     * Should be able to change editor content css through interface
     */
    @Test
    fun setEditorContentCSS() {
        interact {
            tinyMCE!!.contentCSS = testContentCSS
        }
        delayOneSecond()
        interact {
            assertEquals(testContentCSS, tinyMCE!!.contentCSS)
        }
    }
}
