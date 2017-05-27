<#--
* supervisor_id,
* supervisor_name, supervisor_surname, supervisor_fathersname,
* basechair, areas, topic,
* motivation, timing, school,
* promotion, networking, other,
* studname, studsurname, current_degree, grade,
* vk, email,
* other_contacts, years, bachelor, master, phd
-->

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

<#--
<#if lab.wikimipt??>
            <li><a href="${lab.wikimipt}">${lab.name}</a></li>
<#else>
    <li>${lab.name}</li>
</#if>
        -->

<div class="container">
    Комментатор: ${com.studname} ${com.studsurname}<br>

    Учился на кафедре ${com.basechair}

    <#if com.years != "">
        в ${com.years} годах
    </#if>

    <#if com.degree != "">
        как ${com.degree}
    </#if>

    . <br>

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

    <h4>Комментарий:</h4><br>

    <#if com.areas != "">
    Области науки, в которых данный ученй специализируется:<br>
    ${com.areas}<br>
    </#if>

    <#if com.topic != "">
    Тема Вашей работы с данным научным руководителем:<br>
    ${com.topic}<br>
    </#if>

    <#if com.motivation != "">
    Почему Вы выбрали этого научного руководителя:<br>
    ${com.motivation}<br>
    </#if>

    <#if com.timing != "">
    Сколько времени вам уделял ваш научный руководитель и как вы его использовали:<br>
    ${com.timing}<br>
    </#if>

    <#if com.school != "">
    Как хорошо развита школа у руководителя (количество текущих студентов-бакалавров и магистров, а так же аспирантов, взаимодействие между ними):<br>
    ${com.school}<br>
    </#if>

    <#if com.promotion != "">
    Как Вам помогал этот научный руководитель попасть на школы, конференции, стажировки (на сколько был заинтересован Вашим становлением как ученого):<br>
    ${com.promotion}<br>
    </#if>

    <#if com.networking != "">
    На сколько хорошо развита сеть внешних контактов у данного ученого и как она вам помогла:<br>
    ${com.networking}<br>
    </#if>

    <#if com.other != "">
        Ваши комментарии:<br>
        ${com.other}
    </#if>

</div>