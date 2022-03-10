package com.yyil.noteapp.entityTest

import com.yyil.noteapp.entity.NoteContentEntity
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class NoteContentEntityTest {
	@Test
	fun testcontentEntity() {
		var temp = NoteContentEntity(creator="IVAN", create_time= LocalDateTime.now(), updater="BOB",
			update_time=LocalDateTime.now(), repository_path="/desktop/", note_content="<html><\\html>",
				category="my cate", title= "my title")
		var str = temp.getInsertBraStr()
		var str2 = temp.getUpdateStr()
		var str3 = temp.getInsertStr()
		
		println("getInsertBraStr:\n$str\ngetUpdateStr:\n$str2\ngetInsertStr:\n$str3")
	}
}
