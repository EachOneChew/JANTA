package com.yyil.noteapp.entity

import java.time.LocalDateTime

abstract class SQLEntity() {
	abstract fun getInsertBraStr(): String
	abstract fun getUpdateStr(): String
	abstract fun getConStr(): String
	abstract fun getInsertStr(): String
	abstract fun getId(): Int?
	abstract fun getIdColumn(): String
	abstract fun getFullColumn(): String
	abstract fun getDbName(): String
}
