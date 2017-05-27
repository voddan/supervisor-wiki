package edu.phystech.vodopyan.hello

import com.zaxxer.hikari.*
import freemarker.cache.*
import org.jetbrains.ktor.application.*
import org.jetbrains.ktor.content.*
import org.jetbrains.ktor.features.*
import org.jetbrains.ktor.freemarker.*
import org.jetbrains.ktor.host.*
import org.jetbrains.ktor.http.*
import org.jetbrains.ktor.netty.*
import org.jetbrains.ktor.routing.*
import java.util.*
import kotlinx.html.stream.*
import org.jetbrains.ktor.response.*
import org.jetbrains.ktor.client.*
import java.sql.*
import java.util.concurrent.*

val DEBUG = if(true) System.currentTimeMillis().toString() else ""

val hikariConfig = HikariConfig().apply {
    jdbcUrl = System.getenv("JDBC_DATABASE_URL")
            ?: "jdbc:postgresql://ec2-54-247-166-129.eu-west-1.compute.amazonaws.com:5432/d96f3be7mjlgtt?user=hznkucudptrpco&password=323a4ff95e2cdcd2a401d882fc96ce1d4fb634f211770d3524b38b569d9f706d&sslmode=require"
}

val dataSource = if (hikariConfig.jdbcUrl != null) HikariDataSource(hikariConfig) else HikariDataSource()


val html_utf8: ContentType = ContentType.Text.Html.withCharset(Charsets.UTF_8)

fun Application.module() {
    install(DefaultHeaders)
    install(ConditionalHeaders)
    install(PartialContentSupport)

    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(environment.classLoader, "templates")
    }

/*    install(StatusPages) {
        exception<Exception> { exception ->
            call.respond(FreeMarkerContent("example/error.ftl", exception, "", html_utf8))
        }
    }*/

    install(Routing) {
        serveClasspathResources("public")

        get("/") {
            call.respondRedirect("/supervisors")
        }


        get("/laboratories") {
            call.respondFreeMarker("laboratories.ftl")
        }

        route("/supervisors") {
            get("/") {
                val model = Data
                call.respondFreeMarker("supervisors.ftl", model)
            }

            for(sup in Data.supervisorsList) {
                route("/${sup.webname}") {

                    get {
                        dataSource.connection.use { connection ->
                            val rs = connection.createStatement().run {
                                executeQuery("SELECT * FROM comments WHERE supervisor_id = ${sup.id};")
                            }

                            val output = mutableListOf<Comment>()

                            while (rs.next()) {

                                val com = Comment(rs.getInt("id"), rs.getInt("supervisor_id"),
                                        rs.getStr("supervisor_name"), rs.getStr("supervisor_surname"), rs.getStr("supervisor_fathersname"),
                                        rs.getStr("basechair"), rs.getStr("areas"), rs.getStr("topic"),
                                        rs.getStr("motivation"), rs.getStr("timing"), rs.getStr("school"),
                                        rs.getStr("promotion"), rs.getStr("networking"), rs.getStr("other"),
                                        rs.getStr("studname"), rs.getStr("studsurname"), rs.getStr("current_degree"), rs.getStr("grade"),
                                        rs.getStr("vk"), rs.getStr("email"), rs.getStr("other_contacts"),
                                        rs.getStr("years"), rs.getInt("bachelor"), rs.getInt("master"), rs.getInt("phd")
                                )

                                output.add(com)
                            }


                            data class Model(val sup: Supervisor, val comments: List<Comment>)

                            val model = Model(sup, output)

                            call.respondFreeMarker("supervisor.ftl", model)
                        }
                    }

//                    post("/comment") {
//                        println(call.request)
//                        println(call.attributes)
//                        println(call.parameters)
//                    }

                    get("/comment-form") {
                        call.respondFreeMarker("comment-form.ftl", model = sup)
                    }

                    get("/send-comment") {
//                        call.respondFreeMarker("after-comment.ftl", model = sup)

                        val form = call.parameters
//                        val comment = call.parameters["comment"]!!

                        println(form)


                        /*
                        * id, supervisor_id,
                        * supervisor_name, supervisor_surname, supervisor_fathersname,
                        * basechair, areas, topic,
                        * motivation, timing, school,
                        * promotion, networking, other,
                        * studname, studsurname, current_degree, grade,
                        * vk, email,
                        * other_contacts, years, bachelor, master, phd*/


                        /*
                        * [studsurname=[1], studname=[1], current_degree=[Введите год обучения],
                        * basechair=[1], grade=[1], vk=[1], email=[1], other_contacts=[1],
                        * supervisor_surname=[1], supervisor_name=[],
                        * supervisor_fathersname=[], bachelor=[Бакалавриат], phd=[Аспирантура],
                        * keep_contact=[yes,sure], years=[1], areas=[1], topic=[1], motivation=[1],
                        * timing=[1], school=[1], promotion=[1], networking=[1], other=[1]]
                        */

                        val current_degree = form["current_degree"].let { if(it == "Введите год обучения") "" else it }

                        dataSource.connection.use { connection ->
                            val rs = connection.createStatement().run {
                                executeUpdate("""   | INSERT INTO
                                                    |   comments(supervisor_id, supervisor_name, supervisor_surname, supervisor_fathersname, basechair, areas, topic, motivation, timing, school, promotion, networking, other, studname, studsurname, current_degree, grade, vk, email, other_contacts, years, bachelor, master, phd)
                                                    | VALUES(
                                                    |   ${sup.id},
                                                    |   '${sup.givenName}', '${sup.familyName}', '${sup.middleName}',
                                                    |   '${form["basechair"]}', '${form["areas"]}', '${form["topic"]}',
                                                    |   '${form["motivation"]}', '${form["timing"]}', '${form["school"]}',
                                                    |   '${form["promotion"]}', '${form["networking"]}', '${form["other"]}',
                                                    |   '${form["studname"]}', '${form["studsurname"]}', '${current_degree}', '${form["grade"]}',
                                                    |   '${form["vk"]}', '${form["email"]}',
                                                    |   '${form["other_contacts"]}', '${form["years"]}', 0, 0, 0
                                                    | )""".trimMargin())

                                executeQuery("SELECT * FROM comments order by id DESC limit 1;")
                            }


                            val output = mutableListOf<Comment>()

                            while (rs.next()) {

                                val com = Comment(rs.getInt("id"), rs.getInt("supervisor_id"),
                                        rs.getStr("supervisor_name"), rs.getStr("supervisor_surname"), rs.getStr("supervisor_fathersname"),
                                        rs.getStr("basechair"), rs.getStr("areas"), rs.getStr("topic"),
                                        rs.getStr("motivation"), rs.getStr("timing"), rs.getStr("school"),
                                        rs.getStr("promotion"), rs.getStr("networking"), rs.getStr("other"),
                                        rs.getStr("studname"), rs.getStr("studsurname"), rs.getStr("current_degree"), rs.getStr("grade"),
                                        rs.getStr("vk"), rs.getStr("email"), rs.getStr("other_contacts"),
                                        rs.getStr("years"), rs.getInt("bachelor"), rs.getInt("master"), rs.getInt("phd")
                                )

                                output.add(com)
                            }

                            println("SQL OUTPUT")
                            println(output.joinToString(separator = "\n"))

                            data class Model(val sup: Supervisor, val com: Comment)

                            val model = Model(sup, output.first())

                            call.respondFreeMarker("after-comment.ftl", model)
                        }

                    }
                }
            }
        }

        route("/like") {
            get("/kotlinorg") {
                call.respondFreeMarker("example/kotlinorg.ftl")
            }

            get("flexbox") {
                call.respondFreeMarker("example/flexbox.ftl")
            }

            get("/heroku-example") {
                call.respondFreeMarker("example/herokuexample.ftl")
            }
        }

        route("/example") {
            get("hello") {
                call.respond("Hello World?")
            }

            get("error") {
                throw IllegalStateException("An invalid place to be …")
            }

            get("/index") {
                call.respondFreeMarker("example/index.ftl")
            }

            get("/db") {
                val model = HashMap<String, Any>()
                dataSource.connection.use { connection ->
                    val rs = connection.createStatement().run {
                        executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)")
                        executeUpdate("INSERT INTO ticks VALUES (now())")
                        executeQuery("SELECT tick FROM ticks")
                    }

                    val output = ArrayList<String>()
                    while (rs.next()) {
                        output.add("Read from DB: " + rs.getTimestamp("tick"))
                    }
                    model.put("results", output)
                }

                val etag = model.toString().hashCode().toString()
                call.respond(FreeMarkerContent("example/db.ftl", model, etag, html_utf8))
            }
        }
    }
}

suspend fun ApplicationCall.respondFreeMarker(ftl: String, model: Any = mapOf<String, Any>()) {
    val hash =  (ftl + model.toString() + DB.hashCode() + DEBUG).hashCode().toString()
    respond(FreeMarkerContent(ftl, model, hash, html_utf8))

    println(hash)
}

fun ResultSet.getStr(column: String) = getString(column).trim()


fun main(args: Array<String>) {
    val port = System.getenv("PORT")?.toIntOrNull() ?: 5000
    val server = embeddedServer(Netty, port, reloadPackages = listOf("supervisor-wiki"), module = Application::module)
    server.start()
}


