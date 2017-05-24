package edu.phystech.vodopyan.hello

import com.zaxxer.hikari.*
import org.jetbrains.ktor.application.*
import org.jetbrains.ktor.freemarker.*
import java.util.ArrayList
import java.util.HashMap



object DB {
    val hikariConfig = HikariConfig().apply {
        jdbcUrl = System.getenv("JDBC_DATABASE_URL")
    }

    val dataSource = if (hikariConfig.jdbcUrl != null) HikariDataSource(hikariConfig) else HikariDataSource()

    // For demonstration purposes
    val comments: MutableMap<String, MutableList<String>> = mutableMapOf()

    fun sendComment(supervisorWebName: String, msg: String) {
        println(msg)

        dataSource.connection.use { connection ->
            val rs = connection.createStatement().run {
                executeUpdate("CREATE TABLE IF NOT EXISTS comments (tick timestamp)")
                executeUpdate("INSERT INTO comments VALUES (now())")
                executeQuery("SELECT com FROM comments")
            }

            val output = ArrayList<String>()
            while (rs.next()) {
                output.add("Read from DB: " + rs.getTimestamp("tick"))
            }
            comments.put("results", output)
        }

        val etag = comments.toString().hashCode().toString()
//        call.respond(FreeMarkerContent("example/db.ftl", model, etag, html_utf8))

        comments.getOrPut(supervisorWebName, { mutableListOf()}).add(msg)

        println(comments[supervisorWebName])
    }

    fun getComments(supervisorWebName: String): List<String> {
        return comments[supervisorWebName] ?: listOf()
    }

    override fun hashCode() = comments.hashCode()
}

