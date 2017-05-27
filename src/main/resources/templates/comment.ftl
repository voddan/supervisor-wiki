<#--
data class Comment(
        val id: Int, val supervisor_id: Int,
        val supervisor_name: String, val supervisor_surname: String, val supervisor_fathersname: String,
        val basechair: String, val areas: String, val topic: String,
        val motivation: String, val timing: String, val school: String,
        val promotion: String, val networking: String, val other: String,
        val studname: String, val studsurname: String, val current_degree: String, val grade: String,
        val vk: String, val email: String, val other_contacts: String,
        val years: String, val bachelor: Int, val master: Int, val phd: Int)-->

<div class="container">
    <div class="well">
        <div class="panel panel-info">
            <div class=panel-heading>
                <h4 class=panel-title>${com.studname} ${com.studsurname}</h4>
            </div>
            <div class=panel-body>

                Учился на кафедре ${com.basechair}

            <#if com.years != "">
                в ${com.years} годах
            </#if>

            <#if com.degree != "">
                как ${com.degree}
            </#if>

                <br>

            <#if com.grade != "">
                Cредний балл зачетки ${com.grade}.<br>
            </#if>

            <#if com.current_degree != "">
                В настоящее время является ${com.current_degree}.<br>
            </#if>
                <br>

                <div class="container">
                    С комментатором можно связаться:<br>
                    <ul>
                    <#if com.vk != "">
                        <li>VK: ${com.vk}</li>
                    </#if>

                    <#if com.other_contacts != "">
                        <li>e-mail: ${com.email}</li>
                    </#if>

                    <#if com.other_contacts != "">
                        <li>other: ${com.other_contacts}</li>
                    </#if>
                    </ul>
                </div>
            </div>

            <div>

                <div class="panel panel-info">
                    <div class=panel-heading>
                        <h4 class=panel-title> Комментарий:</h4>
                    </div>
                    <div class=panel-body>


                    <#if com.areas != "">
                        <b>Области науки, в которых данный ученй специализируется:</b>
                        <p>${com.areas}</p>
                    </#if>

                    <#if com.topic != "">
                        <b>Тема Вашей работы с данным научным руководителем:</b>
                        <p>${com.topic}</p>
                    </#if>

                    <#if com.motivation != "">
                        <b>Почему Вы выбрали этого научного руководителя:</b>
                        <p>${com.motivation}</p>
                    </#if>

                    <#if com.timing != "">
                        <b>Сколько времени вам уделял ваш научный руководитель и как вы его использовали:</b>
                        <p>${com.timing}</p>
                    </#if>

                    <#if com.school != "">
                        <b>Как хорошо развита школа у руководителя (количество текущих студентов-бакалавров и магистров,
                            а так же аспирантов, взаимодействие между ними):</b>
                        <p>${com.school}</p>
                    </#if>

                    <#if com.promotion != "">
                        <b>Как Вам помогал этот научный руководитель попасть на школы, конференции, стажировки
                        (на сколько был заинтересован Вашим становлением как ученого):</b>
                        <p>${com.promotion}</p>
                    </#if>

                    <#if com.networking != "">
                        <b>На сколько хорошо развита сеть внешних контактов у данного ученого и как она вам помогла:</b>
                        <p>${com.networking}</p>
                    </#if>

                    <#if com.other != "">
                        <b>Ваши комментарии:</b>
                        <p>${com.other}</p>
                    </#if>
                    </div>
                </div>
            </div>
        </div>