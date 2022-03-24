package com.yyil.noteapp.entityTest

import com.yyil.noteapp.entity.NoteContentEntity
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class NoteContentEntityTest {
    @Test
    fun testcontentEntity() {
        var temp = NoteContentEntity(
            creator = "IVAN", createTime = java.sql.Timestamp(System.currentTimeMillis()).toString(), updater = "BOB",
            updateTime = java.sql.Timestamp(System.currentTimeMillis()).toString(), repositoryPath = "/desktop/", noteContent = "<html><\\html>",
            category = "my cate", title = "my title"
        )
        var str = temp.getInsertBraStr()
        var str2 = temp.getUpdateStr()
        var str3 = temp.getInsertStr()
        var str4 = temp.getConStr()
        
        println("getInsertBraStr:\n$str\ngetUpdateStr:\n$str2\ngetInsertStr:\n$str3\ngetConstr:$str4\n")
    }
}
