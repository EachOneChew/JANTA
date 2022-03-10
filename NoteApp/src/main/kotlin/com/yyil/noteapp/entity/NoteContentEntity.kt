package com.yyil.noteapp.entity

import java.time.LocalDateTime


/**
 * note_content_id: Int?
 *      system created id (primary key)
 *      Don't assign this field while creating a new note
 *
 * create_time: LocalDateTime?
 *      system default current time
 *
 * update_time: LocalDateTime?
 *      system default current time
 *
 * note_content: String?
 *      the main html field
 *
 * ALL FUNCTIONS ARE FOR SQL
 */
data class NoteContentEntity (
	var noteContentId: Int? = null,
	var creator: String? = null,
	var createTime: LocalDateTime? = null,
	var updater: String? = null,
	var updateTime: LocalDateTime? = null,
	var repositoryPath: String? = null,
	var noteContent: String? = null,
	var category: String? = null,
	var title: String? = null
) {
	fun getInsertBraStr():String {
		var braStr:String
		braStr = if (title != null)  "TITLE" else ""
		if (category != null) {
			if (braStr != "") {
				braStr = ", $braStr"
			}
			braStr = "CATEGORY$braStr"
		}
		if (noteContent != null) {
			if (braStr != "") {
				braStr = ", $braStr"
			}
			braStr = "NOTE_CONTENT$braStr"
		}
		if (repositoryPath != null) {
			if (braStr != "") {
				braStr = ", $braStr"
			}
			braStr = "REPOSITORY_PATH$braStr"
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
	
	fun getUpdateStr():String {
		var braStr:String
		braStr = if (title != null)  "TITLE = \"$title\"" else ""
		if (category != null) {
			if (braStr != "") {
				braStr = ", $braStr"
			}
			braStr = "CATEGORY = \"$category\"$braStr"
		}
		if (noteContent != null) {
			if (braStr != "") {
				braStr = ", $braStr"
			}
			braStr = "NOTE_CONTENT = \"$noteContent\"$braStr"
		}
		if (repositoryPath != null) {
			if (braStr != "") {
				braStr = ", $braStr"
			}
			braStr = "REPOSITORY_PATH = \"$repositoryPath\"$braStr"
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
	
	fun getConStr():String {
		var braStr:String
		braStr = if (title != null)  "TITLE = \"$title\"" else ""
		if (category != null) {
			if (braStr != "") {
				braStr = "AND $braStr"
			}
			braStr = "CATEGORY = \"$category\"$braStr"
		}
		if (noteContent != null) {
			if (braStr != "") {
				braStr = "AND $braStr"
			}
			braStr = "NOTE_CONTENT = \"$noteContent\"$braStr"
		}
		if (repositoryPath != null) {
			if (braStr != "") {
				braStr = "AND $braStr"
			}
			braStr = "REPOSITORY_PATH = \"$repositoryPath\"$braStr"
		}
		if (updateTime != null) {
			if (braStr != "") {
				braStr = "AND $braStr"
			}
			braStr = "UPDATE_TIME = \"$updateTime\"$braStr"
		}
		if (updater != null) {
			if (braStr != "") {
				braStr = "AND $braStr"
			}
			braStr = "UPDATER = \"$updater\"$braStr"
		}
		if (createTime != null) {
			if (braStr != "") {
				braStr = "AND $braStr"
			}
			braStr = "CREATE_TIME = \"$createTime\"$braStr"
		}
		if (creator != null) {
			if (braStr != "") {
				braStr = "AND $braStr"
			}
			braStr = "CREATOR = \"$creator\"$braStr"
		}
		
		return braStr
	}
	
	fun getInsertStr():String {
		var braStr:String
		braStr = if (title != null)  "\"$title\"" else ""
		if (category != null) {
			if (braStr != "") {
				braStr = ", $braStr"
			}
			braStr = "\"$category\"" + braStr
		}
		if (noteContent != null) {
			if (braStr != "") {
				braStr = ", $braStr"
			}
			braStr = "\"$noteContent\"" + braStr
		}
		if (repositoryPath != null) {
			if (braStr != "") {
				braStr = ", $braStr"
			}
			braStr = "\"$repositoryPath\"" + braStr
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
}

/**
 * FOR SQL
 */
fun getNoteContentEntityFullCol():String {
	var str:String
	str = "NOTE_CONTENT_ID, CREATOR, CREATE_TIME, UPDATER, UPDATE_TIME, REPOSITORY_PATH, NOTE_CONTENT, CATEGORY, "
	str += "TITLE"
	return str
}
