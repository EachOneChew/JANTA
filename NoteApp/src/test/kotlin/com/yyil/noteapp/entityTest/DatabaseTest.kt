package com.yyil.noteapp.entityTest

import com.yyil.noteapp.entity.NoteContentEntity
import com.yyil.noteapp.entity.SettingEntity
import com.yyil.noteapp.mvc.model.Connect
import com.yyil.noteapp.mvc.model.Connect.findNoteById
import com.yyil.noteapp.mvc.model.Connect.findNoteTitleList
import com.yyil.noteapp.mvc.model.Connect.findSettingById
import com.yyil.noteapp.mvc.model.Connect.findSettingByName
import org.junit.jupiter.api.Test
import java.sql.ResultSet


internal class DatabaseTest {
    @Test
    fun testDatabase() {
        val conn = Connect.getConnection()
        var newId: Int?

        // where create, don't assign note_content_id field
        val temp = NoteContentEntity(
            creator = "IVAN",
            createTime = java.sql.Timestamp(System.currentTimeMillis()).toString(),
            updater = "BOB",
            updateTime = java.sql.Timestamp(System.currentTimeMillis()).toString(),
            repositoryPath = "/desktop/",
            noteContent = "<html><\\html>",
            category = "my cate",
            title = "my title"
        )
        newId = Connect.create(conn, temp)
        println("create newId: $newId")

        // where update, HAVE TO set note_content_id field
        val temp2 = NoteContentEntity(
            noteContentId = 4,
            creator = "IVAN",
            createTime = java.sql.Timestamp(System.currentTimeMillis()).toString(),
            updater = "BOB",
            updateTime = java.sql.Timestamp(System.currentTimeMillis()).toString(),
            repositoryPath = "/desktop/",
            noteContent = "update the db with entity",
            category = "my cate",
            title = "my title"
        )
        newId = Connect.update(conn, temp2)
        println("update check: $newId")

        // find
        // add id: will find at most 1 row of result
        // without id: will find all result with the EXACTLY SAME field
        // conditions on fields are possible, and could be done if required
//        val temp3 = NoteContentEntity(creator = "IVAN")
//
        var result: ResultSet?
//        println("All notes:")
//        while (result?.next() == true) {
//            val noteId = result.getInt("NOTE_CONTENT_ID")
//            val creator = result.getString("CREATOR")
//            val repositoryPath = result.getString("REPOSITORY_PATH")
//            val noteContent = result.getString("NOTE_CONTENT")
//            println(noteId.toString() + "\t" + creator + "\t" + repositoryPath + "\t" + noteContent)
//        }
//
        val temp4 = NoteContentEntity(category = "my cate")
        Connect.delete(conn, temp4)

        // NOTE: remember to change the name everytime, as name is restricted with "UNIQUE"
//        val temp5 = SettingEntity(
//            creator = "IVAN", createTime = java.sql.Timestamp(System.currentTimeMillis()).toString(), updater = "BOB",
//            updateTime = java.sql.Timestamp(System.currentTimeMillis()).toString(), name = "newfont", value = "Arial"
//        )
//        newId = Connect.create(conn, temp5)
        println("create check: $newId")
        result = Connect.find(conn, SettingEntity())
        println("All settings:")
        while (result?.next() == true) {
            val noteId = result.getInt("SETTING_ID")
            val name = result.getString("NAME")
            val value = result.getString("VALUE")
            println(noteId.toString() + "\t" + name + "\t" + value + "\t")
        }

        result = Connect.loadAll(conn, dbName = "\"MAIN\".\"NOTE_CONTENT\"")
        println("All settings:")
        while (result?.next() == true) {
            val noteId = result.getInt("NOTE_CONTENT_ID")
            val creator = result.getString("CREATOR")
            val repositoryPath = result.getString("REPOSITORY_PATH")
            val noteContent = result.getString("NOTE_CONTENT")
            println(noteId.toString() + "\t" + creator + "\t" + repositoryPath + "\t" + noteContent)
        }

        Connect.deleteNoteById(conn, id = 55)
        var foundNote: NoteContentEntity? = findNoteById(conn, id = 55)
        println("Did we found the note?: $foundNote")
        foundNote = findNoteById(conn, id = 60)
        println("Did we found the note?: $foundNote")
        foundNote?.print()
        var foundSetting: SettingEntity? = findSettingById(conn, id = 3)
        println("Did we found the note?: $foundSetting")
        foundSetting?.print()
        foundSetting = findSettingByName(conn, name = "font")
        println("Did we found the note?: $foundNote")
        foundSetting?.print()
        val lst: MutableList<NoteContentEntity>? = findNoteTitleList(conn, NoteContentEntity())
        if (lst != null) {
            for (item in lst) {
                println(item)
            }
        }
        Connect.reset(conn, NoteContentEntity())
        Connect.reset(conn, SettingEntity())
        Connect.close(conn)
    }
}
