<!DOCTYPE html>
<html>
<head>
  <title>WIKI о научруках ФизТеха</title>

  <#include "header-links.ftl">
</head>

<body>

<#include "nav.ftl">


  <div class="container">
    <form action="/supervisors/${webname}/send-comment" method="get">




      <fieldset>
        <legend>Информация о студенте</legend>
        <div>
        Фамилия:<br>
        <input name="studsurname" type="text" class="form-control" placeholder="Фамилия">
        </div>
          Имя:<br>
        <input name="studname" type="text" class="form-control" placeholder="Имя">
        Текущий курс обучения:<br>
        <!--<div class="btn-group">-->
          <!--<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">-->
            <!--Введите год обучения <span class="caret"></span>-->
          <!--</button>-->
          <!--<ul class="dropdown-menu">-->
            <!--<li><a href="#">1 год бакалавр</a></li>-->
            <!--<li><a href="#">2 год бакалавр</a></li>-->
            <!--<li><a href="#">3 год бакалавр</a></li>-->
            <!--<li><a href="#">4 год бакалавр</a></li>-->
            <!--<li role="separator" class="divider"></li>-->
            <!--<li><a href="#">Выпускник бакалавриата</a></li>-->
            <!--<li><a href="#">1 год магистр</a></li>-->
            <!--<li><a href="#">2 год магистр</a></li>-->
            <!--<li role="separator" class="divider"></li>-->
            <!--<li><a href="#">Выпускник магистратуры</a></li>-->
            <!--<li><a href="#">1 год аспирант</a></li>-->
            <!--<li><a href="#">2 год аспирант</a></li>-->
            <!--<li><a href="#">3 год аспирант</a></li>-->
            <!--<li role="separator" class="divider"></li>-->
            <!--<li><a href="#">Выпускник аспирантуры</a></li>-->
          <!--</ul>-->
        <!--</div>-->
        <select name="current_degree">
          <option value="Введите год обучения">Введите год обучения</option>
          <option value="1 год бакалавр">1 год бакалавр</option>
          <option value="2 год бакалавр">2 год бакалавр</option>
          <option value="3 год бакалавр">3 год бакалавр</option>
          <option value="4 год бакалавр">4 год бакалавр</option>
          <option value="Выпускник бакалавриата">Выпускник бакалавриата</option>
          <option value="1 год магистр">1 год магистр</option>
          <option value="2 год магистр">2 год магистр</option>
          <option value="Выпускник магистратуры">Выпускник магистратуры</option>
          <option value="1 год аспирант">1 год аспирант</option>
          <option value="2 год аспирант">2 год аспирант</option>
          <option value="3 год аспирант">3 год аспирант</option>
          <option value="Выпускник аспирантуры">Выпускник аспирантуры</option>
        </select>
        <br>
        Ваша базовая кафедра (специализация) на момент работы с научным руководителем:<br>
        <input name="basechair" type="text" class="form-control" placeholder="ChairName">
        Средний балл зачетки/диплома (из 10):<br>
        <input name="grade" type="number" value="" step=0.1><br>
        VK:<br>
        <input name="vk" type="text" class="form-control" placeholder="vk.com/...">
        e-mail:<br>
        <input name="email" type="text" class="form-control" placeholder="name@domain.com">
        Альтернативный способ связи:<br>
        <input name="other_contacts" type="text" class="form-control" placeholder="smth">
      </fieldset>


      <fieldset>
          <legend>Информация о преподавателе</legend>
          <h3>${name}</h3><br><br>
          <#--Фамилия:<br>-->
          <#--<input name="supervisor_surname" type="text" class="form-control" placeholder="${familyName}">-->
          <#--Имя:<br>-->
          <#--<input name="supervisor_name" type="text" class="form-control" placeholder="${givenName}">-->
          <#--Отчество:<br>-->
          <#--<input name="supervisor_fathersname"type="text" class="form-control" placeholder="${middleName}">-->
          Период времени, который данный преподаватель был Вашим научным руководителем:<br>
          <input name="bachelor" type="checkbox" value="Бакалавриат"> Бакалавриат<br>
          <input name="master" type="checkbox" value="Магистратура"> Магистратура<br>
          <input name="phd" type="checkbox"value="Аспирантура"> Аспирантура<br>
          Общаетесь ли вы сейчас с научным руководителем:<br>
          <input type="radio" name="keep_contact" value="yes"> Да, он и сейчас мой научный руководитель<br>
          <input type="radio" name="keep_contact" value="yes,sure"> Да, мы продолжаем научную деятельность даже после моего выпуска<br>
          <input type="radio" name="keep_contact" value="no"> Нет<br>
          Годы вашего сотруднечества:<br>
          <input name="years" type="text" class="form-control" placeholder="XXXX-XXXX">
      </fieldset>


      <fieldset>
        <legend>Отзыв</legend>
        Области науки, в которых данный ученй специализируется:<br>
        <input name="areas" type="text" class="form-control" placeholder="Math&Physics">
        Тема Вашей работы с данным научным руководителем:<br>
        <input name="topic" type="text" class="form-control" placeholder="Topic">
        Почему Вы выбрали этого научного руководителя:<br>
        <textarea name="motivation" rows="5" cols="80">

	</textarea><br>
        Сколько времени вам уделял ваш научный руководитель и как вы его использовали:<br>
        <textarea name="timing" rows="5" cols="80">

	</textarea><br>
        Как хорошо развита школа у руководителя (количество текущих студентов-бакалавров и магистров, а так же аспирантов, взаимодействие между ними):<br>
        <textarea name="school" rows="5" cols="80">

	</textarea><br>

        Как Вам помогал этот научный руководитель попасть на школы, конференции, стажировки (на сколько был заинтересован Вашим становлением как ученого):<br>
        <textarea name="promotion" rows="5" cols="80">

	</textarea><br>
        На сколько хорошо развита сеть внешних контактов у данного ученого и как она вам помогла:<br>
        <textarea name="networking" rows="5" cols="80">

	</textarea><br>
        Ваши комментарии:<br>
        <textarea name="other" rows="5" cols="80">

	</textarea>
      </fieldset>
      <div class="container">
        <button type="submit" class="btn btn-default">Submit</button>
      </div>
    </form>
  </div>

</body>
</html>