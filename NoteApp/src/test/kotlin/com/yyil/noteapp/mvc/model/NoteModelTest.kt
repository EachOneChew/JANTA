package com.yyil.noteapp.mvc.model

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class NoteModelTest {

    private val testNoteModel: NoteModel = NoteModel()

    @Test
    fun insertAnnotationNoTags() {
        val entryString = "Hello this is a possible string, with like stuff in it &nbsp; but no tags just html"
        val exitString = "<span title=\"yo\">Hello this is a possible string, with like stuff in it &nbsp; but no tags just html</span>"
        assertEquals(testNoteModel.insertAnnotation("yo", entryString), exitString)
    }

    @Test
    fun insertAnnotationOnlyOuterTags() {
        val entryString = "<p>hello world</p>"
        val exitString = "<p><span title=\"yo\">hello world</span></p>"
        assertEquals(testNoteModel.insertAnnotation("yo", entryString), exitString)
    }

    @Test
    fun insertAnnotationLotsOfTags() {
        val entryString = "<p><em>Y</em><strong><em>o</em>u</strong>!</p>"
        val exitString = "<p><em><span title=\"yo\">Y</span></em><strong><em><span title=\"yo\">o</span></em><span title=\"yo\">u</span></strong><span title=\"yo\">!</span></p>"
        assertEquals(testNoteModel.insertAnnotation("yo", entryString), exitString)
    }

    @Test
    fun removeAnnotation() {
    }
}