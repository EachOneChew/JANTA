package com.yyil.noteapp.entityTest

import com.yyil.noteapp.entity.ContentEntity
import com.yyil.noteapp.entity.NoteContentEntity
import com.yyil.noteapp.entity.SettingEntity
import com.yyil.noteapp.mvc.model.Connect
import org.junit.jupiter.api.Test
import java.sql.ResultSet
import java.time.LocalDateTime


internal class DatabaseTest {
	@Test
	fun testDatabase() {
		val conn = Connect.getConnection()
		var check: Boolean

        // where create, don't assign note_content_id field
        val temp = NoteContentEntity(
            creator = "IVAN", createTime = LocalDateTime.now(), updater = "BOB",
            updateTime = LocalDateTime.now(), repositoryPath = "/desktop/", noteContent = "<html><\\html>",
            category = "my cate", title = "my title"
        )
        check = Connect.create(conn, temp)
        println("create check: $check")

        // where update, HAVE TO set note_content_id field
        val temp2 = NoteContentEntity(
            noteContentId = 4, creator = "IVAN", createTime = LocalDateTime.now(), updater = "BOB",
            updateTime = LocalDateTime.now(), repositoryPath = "/desktop/", noteContent = "update the db with entity",
            category = "my cate", title = "my title"
        )
        check = Connect.update(conn, temp2)
        println("update check: $check")

        // find
        // add id: will find at most 1 row of result
        // without id: will find all result with the EXACTLY SAME field
        // conditions on fields are possible, and could be done if required
        val temp3 = NoteContentEntity(creator = "IVAN")
        
        var result: ResultSet? = Connect.find(conn, temp3)
        println("All notes:")
        while (result?.next() == true) {
            val noteId = result.getInt("NOTE_CONTENT_ID")
            val creator = result.getString("CREATOR")
            val repositoryPath = result.getString("REPOSITORY_PATH")
            val noteContent = result.getString("NOTE_CONTENT")
            println(noteId.toString() + "\t" + creator + "\t" + repositoryPath + "\t" + noteContent)
        }

        val temp4 = NoteContentEntity(category = "my cate")
        Connect.delete(conn, temp4)

		val temp5 = SettingEntity(
			creator = "IVAN", createTime = LocalDateTime.now(), updater = "BOB",
			updateTime = LocalDateTime.now(), name = "font", value = "Arial"
		)
		check = Connect.create(conn, temp5)
		println("create check: $check")
		result = Connect.find(conn, SettingEntity())
		println("All settings:")
		while (result?.next() == true) {
			val noteId = result.getInt("SETTING_ID")
			val name = result.getString("NAME")
			val value = result.getString("VALUE")
			println(noteId.toString() + "\t" + name + "\t" + value + "\t")
		}
		
		result = Connect.find(conn, dbName = "\"MAIN\".\"NOTE_CONTENT\"")
		println("All settings:")
		while (result?.next() == true) {
			val noteId = result.getInt("NOTE_CONTENT_ID")
			val creator = result.getString("CREATOR")
			val repositoryPath = result.getString("REPOSITORY_PATH")
			val noteContent = result.getString("NOTE_CONTENT")
			println(noteId.toString() + "\t" + creator + "\t" + repositoryPath + "\t" + noteContent)
		}
        Connect.reset(conn, NoteContentEntity())
        Connect.reset(conn, SettingEntity())
		Connect.close(conn)
	}
}
