package com.yyil.noteapp.entity

import java.sql.Timestamp

abstract class SQLEntity() {
    var delFlag: Int = 0;
    abstract fun getInsertBraStr(): String
    abstract fun getUpdateStr(): String
    abstract fun getConStr(): String
    abstract fun getInsertStr(): String
    abstract fun getId(): Int?
    abstract fun getIdColumn(): String
    abstract fun getFullColumn(): String
    abstract fun getDbName(): String
    abstract fun updateTime(updateTime: String? = Timestamp(System.currentTimeMillis()).toString())
    fun setDel() {
        delFlag = 1
    }
}
