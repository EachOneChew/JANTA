package com.yyil.noteapp.mvc.model
import java.sql.*


object Connect {
    var conn: Connection? = null
    var dbName = "NOTE_CONTENT"
    
//    var driver = "dm.jdbc.driver.DmDriver"
//    var databaseUserName = "SYSDBA"
//    var databasePassword = "1234567890"
//    var serverip = "5236"
//    //instanceName=SQLExpress;
//    var url = "jdbc:dm://IP:$serverip;databaseName=$dbName"

//    var driver = "dm.jdbc.driver.DmDriver"
//    var databaseUserName = "SYSDBA"
//    var databasePassword = "1234567890"
    var serverip = "noteApp"
    //instanceName=SQLExpress;
    var url = "jdbc:sqlite:$serverip.db"
    
    var stmt: Statement? = null
    var result: ResultSet? = null


    fun getConnection(): Connection? {
//        var conn: Connection? = null
        try {
//            Class.forName(driver)
//            conn = DriverManager.getConnection(url, databaseUserName, databasePassword)
            conn = DriverManager.getConnection(url)
            stmt = conn!!.createStatement()
            println("Connection to SQLite has been established.")
        } catch (e: SQLException) {
            println(e.message)
        }
        return conn
    }

    fun query(conn:Connection?) {
        try {
            if (conn != null) {
                val sql = "SELECT * FROM $dbName"
                val results = stmt!!.executeQuery(sql)
                println("All notes:")
                while (results.next()) {
//                    println("HERE!!!!!!!!!!!!!!!!!!!!!!")
                    val note_id = results.getInt("NOTE_CONTENT_ID")
                    val repository_path = results.getString("REPOSITORY_PATH")
                    val note_content = results.getString("NOTE_CONTENT")
                    println(note_id.toString() + "\t" + repository_path + "\t" + note_content)
                }
            }
        } catch (ex: SQLException) {
//            println("ERROR!!!!!!!!!!!!!!!!!!!!!!")
            println(ex.message)
        }
    }
    
    // insert
    fun createContent(line: String) {
        val sql = "INSERT INTO \"main\".\"$dbName\"(\"NOTE_CONTENT\") VALUES(\"$line\")"
        stmt!!.execute(sql)
    }
    
    // change
    fun updateContent(line: String, id: Int) {
        val sql = "UPDATE \"main\".\"$dbName\" SET \"NOTE_CONTENT\" = \"$line\" where \"NOTE_CONTENT_ID\" = $id"
        stmt!!.execute(sql)
    }
    

    fun close(conn:Connection?) {
        try {
            if (conn != null) {
                conn.close()
                println("Connection closed.")
            }
        } catch (ex: SQLException) {
            println(ex.message)
        }
    }
}
