package com.yyil.noteapp.entityTest

import com.yyil.noteapp.entity.FileInfo
import kotlinx.serialization.encodeToString
import com.yyil.noteapp.entity.FileInfo.kt
import org.junit.jupiter.api.Test

internal class HelloApplicationTest {

    @Test
    fun testJson() {
        // Serialization (Kotlin object to JSON string)

        val test1 = FileInfo.kt("Test the first line")
        val string = Json.encodeToString(test1)
//        println(string) // {"content":"Test the first line","colour":#951595}

        // Deserialization (JSON string to Kotlin object)

//    val obj = Json.decodeFromString<User>(string)
//        println(obj)
    }
}
