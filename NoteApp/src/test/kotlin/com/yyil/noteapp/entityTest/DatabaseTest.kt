package com.yyil.noteapp.entityTest

import com.yyil.noteapp.mvc.model.Connect
import org.junit.jupiter.api.Test


internal class DatabaseTest {
	@Test
	fun testDatabase() {
		val conn = Connect.getConnection()
		Connect.createContent("create a new note from testDatabase by kotlin")
		Connect.updateContent("update a new note from testDatabase", id=2)
		Connect.query(conn)
		Connect.close(conn)
	}
}
