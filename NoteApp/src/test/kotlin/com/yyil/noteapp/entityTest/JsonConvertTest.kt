package com.yyil.noteapp.entityTest

import com.yyil.noteapp.entity.LinkEntity
import com.yyil.noteapp.entity.TextFormat
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString

import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test

internal class NoteApplicationTest {
    @Test
    fun testJson() {
        // Serialization (Kotlin object to JSON string)
        val textFormat = TextFormat(font = "Arial", fontSize = 11.0f, colour="#951595")
        val link = "this is a link to the week 7 content"
        val url = "https://student.cs.uwaterloo.ca/~cs398/01-syllabus/2-weekly-schedule/#week-7-sprint-1-demo"
        val test1 = LinkEntity(textFormat = textFormat, link = link, url = url)
        val s = Json.encodeToString(test1)
//        File("output.out").writeText(s)
            assert(s == "{\"textFormat\":{\"font\":\"Arial\",\"fontSize\":11.0,\"colour\":\"#951595\"}" +
                ",\"link\":\"this is a link to the week 7 content\"," +
                "\"url\":\"https://student.cs.uwaterloo.ca/~cs398/01-syllabus/2-weekly-schedule" +
                "/#week-7-sprint-1-demo\"}")

        // Deserialization (JSON string to Kotlin object)

        val obj = Json.decodeFromString<LinkEntity>(string=s)
        assert(obj == LinkEntity(contentList= emptyList(), templateEntityId="0",
            textFormat= TextFormat(bold=false, italic=false, underline=false, superscript=false,
                subscript=false, font="Arial", complexFont="Helvetica", fontSize=11.0f, colour="#951595",
                backgroundColour=null, underlineColour=null),
            link="this is a link to the week 7 content",
            url="https://student.cs.uwaterloo.ca/~cs398/01-syllabus/2-weekly-schedule/#week-7-sprint-1-demo")
        )
    }
}
