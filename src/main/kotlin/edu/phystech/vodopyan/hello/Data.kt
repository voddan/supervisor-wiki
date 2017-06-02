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


//<editor-fold desc="transliteration mapping">
val transliteration = mapOf<Char, Char>(
        ' ' to '_',
        'а' to 'a',
        'б' to 'b',
        'в' to 'v',
        'г' to 'g',
        'д' to 'd',
        'е' to 'e',
        'ё' to 'e',
        'ж' to 'g',
        'з' to 'z',
        'и' to 'i',
        'й' to 'i',
        'к' to 'k',
        'л' to 'l',
        'м' to 'm',
        'н' to 'n',
        'о' to 'o',
        'п' to 'p',
        'р' to 'r',
        'с' to 's',
        'т' to 't',
        'у' to 'u',
        'ф' to 'f',
        'х' to 'h',
        'ц' to 'c',
        'ч' to 'c',
        'ш' to 's',
        'щ' to 's',
        'ъ' to '`',
        'ы' to 'y',
        'ь' to '`',
        'э' to 'e',
        'ю' to 'y',
        'я' to 'y'
)
//</editor-fold>


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
        val id: Int,
        val familyName: String,
        val givenName: String,
        val middleName: String,
        @NameOnly val laboratories: List<Laboratory>,
        val wikimipt: URL?,
        val photo: URL?) {

    val name = "$familyName $givenName $middleName"
    val webname: String = name.toLowerCase().map { ch -> transliteration[ch] ?: ch  }.joinToString(separator = "")
}

data class Comment(
        val id: Int, val supervisor_id: Int,
        val supervisor_name: String, val supervisor_surname: String, val supervisor_fathersname: String,
        val basechair: String, val areas: String, val topic: String,
        val motivation: String, val timing: String, val school: String,
        val promotion: String, val networking: String, val other: String,
        val studname: String, val studsurname: String, val current_degree: String, val grade: String,
        val vk: String, val email: String, val other_contacts: String,
        val years: String, val bachelor: Int, val master: Int, val phd: Int) {

    val degree = mapOf("бакалавр" to bachelor, "магистр" to master, "аспирант" to phd)
            .filterValues { it != 0 }.keys.joinToString()
}


object Data {
    val organisationsList = listOf(
            Organisation("ВЦ РАН", URL("http://wikimipt.org/wiki/%D0%92%D1%8B%D1%87%D0%B8%D1%81%D0%BB%D0%B8%D1%82%D0%B5%D0%BB%D1%8C%D0%BD%D1%8B%D0%B9_%D0%A6%D0%B5%D0%BD%D1%82%D1%80_%D0%B8%D0%BC._%D0%90._%D0%90._%D0%94%D0%BE%D1%80%D0%BE%D0%B4%D0%BD%D0%B8%D1%86%D1%8B%D0%BD%D0%B0_%D0%A0%D0%90%D0%9D")),
            Organisation("ИППИ", URL("http://iitp.ru/ru/about")),
            Organisation("Parallels", URL("https://mipt.ru/education/chairs/theor_app_informatics/")),
            Organisation("ФГУП «ГосНИИАС»", URL("https://www.gosniias.ru/"))
    )

    val organisations = organisationsList.associateBy { it.name }


    val laboratoriesList = listOf(
            Laboratory("Интеллектуальные системы",
                    listOf(Subject.math, Subject.info), listOf(Department.fupm), listOf(organisations["ВЦ РАН"]!!),
                    URL("http://wikimipt.org/wiki/%D0%9A%D0%B0%D1%84%D0%B5%D0%B4%D1%80%D0%B0_%C2%AB%D0%98%D0%BD%D1%82%D0%B5%D0%BB%D0%BB%D0%B5%D0%BA%D1%82%D1%83%D0%B0%D0%BB%D1%8C%D0%BD%D1%8B%D0%B5_%D1%81%D0%B8%D1%81%D1%82%D0%B5%D0%BC%D1%8B%C2%BB")),

            Laboratory("Кафедра проблем передачи информации и анализа данных",
                    listOf(Subject.math, Subject.info), listOf(Department.fupm), listOf(organisations["ИППИ"]!!),
                    URL("http://iitp.ru/ru/about/chair.mipt")),

            Laboratory("Теоретическая и прикладная информатика",
                    listOf(Subject.info), listOf(Department.fupm), listOf(organisations["Parallels"]!!),
                    URL("https://mipt.ru/dcam/basechairs/parallels.php")),

            Laboratory("Управляющих и операционных систем",
                    listOf(Subject.math, Subject.info), listOf(Department.fupm), listOf(organisations["ФГУП «ГосНИИАС»"]!!),
                    URL("https://mipt.ru/education/chairs/control_inf_systems/"))
    )

    val laboratories = laboratoriesList.associateBy { it.name }


    val supervisorsList = listOf(
            Supervisor(0,
                    "Воронцов", "Константин", "Вячеславович",
                    listOf(laboratories["Интеллектуальные системы"]!!),
                    URL("http://wikimipt.org/wiki/%D0%92%D0%BE%D1%80%D0%BE%D0%BD%D1%86%D0%BE%D0%B2_%D0%9A%D0%BE%D0%BD%D1%81%D1%82%D0%B0%D0%BD%D1%82%D0%B8%D0%BD_%D0%92%D1%8F%D1%87%D0%B5%D1%81%D0%BB%D0%B0%D0%B2%D0%BE%D0%B2%D0%B8%D1%87"),
                    URL("http://wikimipt.org/images/thumb/5/56/%D0%92%D0%BE%D1%80%D0%BE%D0%BD%D1%86%D0%BE%D0%B2_%D0%9A%D0%BE%D0%BD%D1%81%D1%82%D0%B0%D0%BD%D1%82%D0%B8%D0%BD_%D0%92%D1%8F%D1%87%D0%B5%D1%81%D0%BB%D0%B0%D0%B2%D0%BE%D0%B2%D0%B8%D1%87.jpeg/266px-%D0%92%D0%BE%D1%80%D0%BE%D0%BD%D1%86%D0%BE%D0%B2_%D0%9A%D0%BE%D0%BD%D1%81%D1%82%D0%B0%D0%BD%D1%82%D0%B8%D0%BD_%D0%92%D1%8F%D1%87%D0%B5%D1%81%D0%BB%D0%B0%D0%B2%D0%BE%D0%B2%D0%B8%D1%87.jpeg")),

            Supervisor(1,
                    "Спокойный", "Владимир", "Григорьевич",
                    listOf(laboratories["Кафедра проблем передачи информации и анализа данных"]!!),
                    URL("http://faculty.skoltech.ru/people/vladimirspokoiny"),
                    URL("http://www.skoltech.ru/app/data/uploads/sites/19/2016/07/vladimirspokoiny_avatar_1467806260.jpg")),

            Supervisor(2,
                    "Чехович", "Юрий", "Викторович",
                    listOf(laboratories["Интеллектуальные системы"]!!),
                    URL("http://www.machinelearning.ru/wiki/index.php?title=%D0%A3%D1%87%D0%B0%D1%81%D1%82%D0%BD%D0%B8%D0%BA:Yury_Chekhovich"),
                    URL("http://www.machinelearning.ru/wiki/images/thumb/8/80/Chehovich.JPG/484px-Chehovich.JPG")),

            Supervisor(3,
                    "Лунев", "Денис", "Владимирович",
                    listOf(laboratories["Теоретическая и прикладная информатика"]!!),
                    URL("http://wikimipt.org/wiki/%D0%9B%D1%83%D0%BD%D1%91%D0%B2_%D0%94%D0%B5%D0%BD%D0%B8%D1%81_%D0%92%D0%BB%D0%B0%D0%B4%D0%B8%D0%BC%D0%B8%D1%80%D0%BE%D0%B2%D0%B8%D1%87"),
                    URL("http://wikimipt.org/images/0/08/%D0%9B%D1%83%D0%BD%D1%91%D0%B2_%D0%94%D0%B5%D0%BD%D0%B8%D1%81_%D0%92%D0%BB%D0%B0%D0%B4%D0%B8%D0%BC%D0%B8%D1%80%D0%BE%D0%B2%D0%B8%D1%87.jpeg")),

            Supervisor(4,
                    "Вадим", "Викторович", "Стрижов",
                    listOf(laboratories["Интеллектуальные системы"]!!),
                    URL("http://www.machinelearning.ru/wiki/index.php?title=%D0%A3%D1%87%D0%B0%D1%81%D1%82%D0%BD%D0%B8%D0%BA:Strijov"),
                    URL("http://www.machinelearning.ru/wiki/images/c/c6/Strijov.jpg")),

            Supervisor(5,
                    "Максимов", "Юрий", "Владимирович",
                    listOf(laboratories["Кафедра проблем передачи информации и анализа данных"]!!),
                    URL("http://faculty.skoltech.ru/people/yurymaximov"),
                    URL("http://www.skoltech.ru/app/data/uploads/sites/19/2016/07/yurymaximov_avatar_1467559189.jpg")),

            Supervisor(6,
                    "Вишнякова", "Лариса", "Владимировна",
                    listOf(laboratories["Управляющих и операционных систем"]!!),
                    URL("https://professorrating.org/professor.php?id=65249"),
                    URL("https://delmarva.streetlightoutages.com/common/images/organizations/_default/unavailablePhoto.png")),

            Supervisor(7,
                    "Матвеев", "Иван", "Алексеевич",
                    listOf(laboratories["Интеллектуальные системы"]!!),
                    URL("http://www.machinelearning.ru/wiki/index.php?title=%D0%A3%D1%87%D0%B0%D1%81%D1%82%D0%BD%D0%B8%D0%BA:IvanMatveev"),
                    URL("https://delmarva.streetlightoutages.com/common/images/organizations/_default/unavailablePhoto.png"))
    )

    val supervisors = supervisorsList.associateBy { it.name }
}



fun main(args: Array<String>) {
    val json = ObjectMapper().registerModule(KotlinModule())
    val voron = Data.supervisors["Воронцов Константин Вячеславович"]!!
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



