package com.yyil.noteapp.mvc.model

import com.yyil.noteapp.delayOneSecond
import javafx.stage.Stage
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.testfx.framework.junit5.ApplicationTest

internal class ModelTest : ApplicationTest() {

    private var testNoteModel: Model? = null

    override fun start(stage: Stage?) {
        testNoteModel = Model()
    }

    /**
     * Delays execution of tests so editor can initialize properly
     */
    @BeforeEach
    fun delayForInit() {
        if (!testNoteModel!!.tinyMCE.isActive) {
            delayOneSecond()
            assertEquals(testNoteModel!!.tinyMCE.isActive, true)
        }
    }

    @Test
    fun insertAnnotationNoTags() {
        val entryString = "Hello this is a possible string, with like stuff in it &nbsp; but no tags just html"
        val exitString =
            "<span title=\"yo\">Hello this is a possible string, with like stuff in it &nbsp; but no tags just html</span>"
        assertEquals(testNoteModel?.insertAnnotation("yo", entryString), exitString)
    }

    @Test
    fun insertAnnotationOnlyOuterTags() {
        val entryString = "<p>hello world</p>"
        val exitString = "<p><span title=\"yo\">hello world</span></p>"
        assertEquals(testNoteModel?.insertAnnotation("yo", entryString), exitString)
    }

    @Test
    fun insertAnnotationLotsOfTags() {
        val entryString = "<p><em>Y</em><strong><em>o</em>u</strong>!</p>"
        val exitString =
            "<p><em><span title=\"yo\">Y</span></em><strong><em><span title=\"yo\">o</span></em><span title=\"yo\">u</span></strong><span title=\"yo\">!</span></p>"
        assertEquals(testNoteModel?.insertAnnotation("yo", entryString), exitString)
    }

    @Test
    fun removeAnnotationNoTags() {
        val entryString = "Hello this is a possible string, with like stuff in it &nbsp; but no tags just html"
        val exitString =
            "<span title=\"yo\">Hello this is a possible string, with like stuff in it &nbsp; but no tags just html</span>"
        assertEquals(testNoteModel?.removeAnnotation(exitString), entryString)
    }

    @Test
    fun removeAnnotationOnlyOuterTags() {
        val entryString = "<p>hello world</p>"
        val exitString = "<p><span title=\"yo\">hello world</span></p>"
        assertEquals(testNoteModel?.removeAnnotation(exitString), entryString)
    }

    @Test
    fun removeAnnotationLotsOfTags() {
        val entryString = "<p><em>Y</em><strong><em>o</em>u</strong>!</p>"
        val exitString =
            "<p><em><span title=\"yo\">Y</span></em><strong><em><span title=\"yo\">o</span></em><span title=\"yo\">u</span></strong><span title=\"yo\">!</span></p>"
        assertEquals(testNoteModel?.removeAnnotation(exitString), entryString)
    }
}