package edu.phystech.vodopyan.hello

object DB {
    // For demonstration purposes
    val comments: MutableMap<String, MutableList<String>> = mutableMapOf()

    fun sendComment(supervisorWebName: String, msg: String) {
        println(msg)
        comments.getOrPut(supervisorWebName, { mutableListOf()}).add(msg)

        println(comments[supervisorWebName])
    }

    fun getComments(supervisorWebName: String): List<String> {
        return comments[supervisorWebName] ?: listOf()
    }
}