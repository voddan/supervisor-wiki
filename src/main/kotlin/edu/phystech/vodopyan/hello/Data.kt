package edu.phystech.vodopyan.hello

import com.fasterxml.jackson.annotation.*
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.dataformat.yaml.*
import com.fasterxml.jackson.module.kotlin.*
import java.net.*


//@JacksonAnnotationsInside
@Target(AnnotationTarget.FIELD)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "name")
@JsonIdentityReference(alwaysAsId = true)
annotation class NameOnly


enum class Subject(@JsonProperty("name") val str: String) {
    math("Математика"),
    info("Информатика"),
    phys("Физика"),
    chem("Химия"),
    econ("Экономика")
}

enum class Department(@JsonProperty("name") val str: String) {
    frtk("ФРТК"),
    fopf("ФОПФ"),
    faki("ФАКИ"),
    fmhf("ФМХФ"),
    ffke("ФФКЭ"),
    falt("ФАЛТ"),
    fupm("ФУПМ"),
    fpfe("ФПФЭ"),
    fivt("ФИВТ"),
    fbma("ФБМФ"),
    fnbk("ФНБИК")
}

data class Organisation(val name: String, val wikimipt: URL?)

data class Laboratory(
        @NameOnly val name: String,
        @NameOnly val subjects: List<Subject>,
        @NameOnly val departments: List<Department>,
        @NameOnly val organisations: List<Organisation>,
        val wikimipt: URL?)

data class Supervisor(
        val name: String,
        @NameOnly val laboratories: List<Laboratory>,
        val wikimipt: URL?,
        val photo: URL?)


val organisations: Map<String, Organisation> = listOf(
        Organisation("ВЦ РАН", URL("http://wikimipt.org/wiki/%D0%92%D1%8B%D1%87%D0%B8%D1%81%D0%BB%D0%B8%D1%82%D0%B5%D0%BB%D1%8C%D0%BD%D1%8B%D0%B9_%D0%A6%D0%B5%D0%BD%D1%82%D1%80_%D0%B8%D0%BC._%D0%90._%D0%90._%D0%94%D0%BE%D1%80%D0%BE%D0%B4%D0%BD%D0%B8%D1%86%D1%8B%D0%BD%D0%B0_%D0%A0%D0%90%D0%9D"))
).associateBy { it.name }


val laboratories: Map<String, Laboratory> = listOf(
        Laboratory("Интеллектуальные системы",
                listOf(Subject.math, Subject.info), listOf(Department.fupm), listOf(organisations["ВЦ РАН"]!!),
                URL("http://wikimipt.org/wiki/%D0%9A%D0%B0%D1%84%D0%B5%D0%B4%D1%80%D0%B0_%C2%AB%D0%98%D0%BD%D1%82%D0%B5%D0%BB%D0%BB%D0%B5%D0%BA%D1%82%D1%83%D0%B0%D0%BB%D1%8C%D0%BD%D1%8B%D0%B5_%D1%81%D0%B8%D1%81%D1%82%D0%B5%D0%BC%D1%8B%C2%BB"))
).associateBy { it.name }


val supervisors: Map<String, Supervisor> = listOf(
        Supervisor("Воронцов Константин Вячеславович",
                listOf(laboratories["Интеллектуальные системы"]!!),
                URL("http://wikimipt.org/wiki/%D0%92%D0%BE%D1%80%D0%BE%D0%BD%D1%86%D0%BE%D0%B2_%D0%9A%D0%BE%D0%BD%D1%81%D1%82%D0%B0%D0%BD%D1%82%D0%B8%D0%BD_%D0%92%D1%8F%D1%87%D0%B5%D1%81%D0%BB%D0%B0%D0%B2%D0%BE%D0%B2%D0%B8%D1%87"),
                URL("http://wikimipt.org/images/thumb/5/56/%D0%92%D0%BE%D1%80%D0%BE%D0%BD%D1%86%D0%BE%D0%B2_%D0%9A%D0%BE%D0%BD%D1%81%D1%82%D0%B0%D0%BD%D1%82%D0%B8%D0%BD_%D0%92%D1%8F%D1%87%D0%B5%D1%81%D0%BB%D0%B0%D0%B2%D0%BE%D0%B2%D0%B8%D1%87.jpeg/266px-%D0%92%D0%BE%D1%80%D0%BE%D0%BD%D1%86%D0%BE%D0%B2_%D0%9A%D0%BE%D0%BD%D1%81%D1%82%D0%B0%D0%BD%D1%82%D0%B8%D0%BD_%D0%92%D1%8F%D1%87%D0%B5%D1%81%D0%BB%D0%B0%D0%B2%D0%BE%D0%B2%D0%B8%D1%87.jpeg"))
).associateBy { it.name }




fun main(args: Array<String>) {
    val json = ObjectMapper().registerModule(KotlinModule())
    val voron = supervisors["Воронцов Константин Вячеславович"]!!
    val st = json.writerWithDefaultPrettyPrinter().writeValueAsString(voron)
    println(st)
    val lj = json.readValue(st, Supervisor::class.java)
    println(lj)


    println("------------")

    val yaml = YAMLMapper()
            .configure(YAMLGenerator.Feature.WRITE_DOC_START_MARKER, false)
            .configure(YAMLGenerator.Feature.SPLIT_LINES, false)
            .registerModule(KotlinModule())

    val str = yaml.writeValueAsString(voron).also {println(it)}

//    val lab = yaml.readValue(str, Supervisor::class.java)
//    println(lab)
}



