package com.yyil.noteapp.entity

import java.time.LocalDateTime

/**
 * setting_id: Int?
 *      system created id (primary key)
 *      Don't assign this field while creating a new note
 *
 * create_time: LocalDateTime?
 *      system default current time
 *
 * update_time: LocalDateTime?
 *      system default current time
 *
 * name: String?
 *      the setting name
 *
 * value: String?
 *      the setting value
 *
 * ALL FUNCTIONS ARE FOR SQL
 */
data class SettingEntity(
	var settingId: Int? = null,
	var creator: String? = null,
	var createTime: LocalDateTime? = null,
	var updater: String? = null,
	var updateTime: LocalDateTime? = null,
	var name: String? = null,
	var value: String? = null
) : SQLEntity() {
	override fun getInsertBraStr(): String {
		var braStr: String
		braStr = if (value != null) "VALUE" else ""
		if (name != null) {
			if (braStr != "") {
				braStr = ", $braStr"
			}
			braStr = "NAME$braStr"
		}
		if (updateTime != null) {
			if (braStr != "") {
				braStr = ", $braStr"
			}
			braStr = "UPDATE_TIME$braStr"
		}
		if (updater != null) {
			if (braStr != "") {
				braStr = ", $braStr"
			}
			braStr = "UPDATER$braStr"
		}
		if (createTime != null) {
			if (braStr != "") {
				braStr = ", $braStr"
			}
			braStr = "CREATE_TIME$braStr"
		}
		if (creator != null) {
			if (braStr != "") {
				braStr = ", $braStr"
			}
			braStr = "CREATOR$braStr"
		}
		
		return braStr
	}
	
	override fun getUpdateStr(): String {
		var braStr: String
		braStr = if (value != null) "VALUE = \"$value\"" else ""
		if (name != null) {
			if (braStr != "") {
				braStr = ", $braStr"
			}
			braStr = "NAME = \"$name\"$braStr"
		}
		if (updateTime != null) {
			if (braStr != "") {
				braStr = ", $braStr"
			}
			braStr = "UPDATE_TIME = \"$updateTime\"$braStr"
		}
		if (updater != null) {
			if (braStr != "") {
				braStr = ", $braStr"
			}
			braStr = "UPDATER = \"$updater\"$braStr"
		}
		if (createTime != null) {
			if (braStr != "") {
				braStr = ", $braStr"
			}
			braStr = "CREATE_TIME = \"$createTime\"$braStr"
		}
		if (creator != null) {
			if (braStr != "") {
				braStr = ", $braStr"
			}
			braStr = "CREATOR = \"$creator\"$braStr"
		}
		
		return braStr
	}
	
	override fun getConStr(): String {
		var braStr: String
		braStr = if (value != null) "VALUE = \"$value\"" else ""
		if (name != null) {
			if (braStr != "") {
				braStr = " AND $braStr"
			}
			braStr = "NAME = \"$name\"$braStr"
		}
		if (updateTime != null) {
			if (braStr != "") {
				braStr = " AND $braStr"
			}
			braStr = "UPDATE_TIME = \"$updateTime\"$braStr"
		}
		if (updater != null) {
			if (braStr != "") {
				braStr = " AND $braStr"
			}
			braStr = "UPDATER = \"$updater\"$braStr"
		}
		if (createTime != null) {
			if (braStr != "") {
				braStr = " AND $braStr"
			}
			braStr = "CREATE_TIME = \"$createTime\"$braStr"
		}
		if (creator != null) {
			if (braStr != "") {
				braStr = " AND $braStr"
			}
			braStr = "CREATOR = \"$creator\"$braStr"
		}
		
		return braStr
	}
	
	override fun getInsertStr(): String {
		var braStr: String
		braStr = if (value != null) "\"$value\"" else ""
		if (name != null) {
			if (braStr != "") {
				braStr = ", $braStr"
			}
			braStr = "\"$name\"" + braStr
		}
		if (updateTime != null) {
			if (braStr != "") {
				braStr = ", $braStr"
			}
			braStr = "\"$updateTime\"" + braStr
		}
		if (updater != null) {
			if (braStr != "") {
				braStr = ", $braStr"
			}
			braStr = "\"$updater\"" + braStr
		}
		if (createTime != null) {
			if (braStr != "") {
				braStr = ", $braStr"
			}
			braStr = "\"$createTime\"" + braStr
		}
		if (creator != null) {
			if (braStr != "") {
				braStr = ", $braStr"
			}
			braStr = "\"$creator\"" + braStr
		}
		
		return braStr
	}
	
	override fun getId(): Int? {
		return settingId
	}
	
	override fun getIdColumn(): String {
		return "SETTING_ID"
	}
	
	override fun getFullColumn(): String {
		return "SETTING_ID, CREATOR, CREATE_TIME, UPDATER, UPDATE_TIME, NAME, VALUE"
	}
	
	override fun getDbName(): String {
		return "\"MAIN\".\"NOTE_SETTING\""
	}
}
