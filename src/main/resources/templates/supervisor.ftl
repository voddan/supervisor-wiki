<!DOCTYPE html>
<html>
<head>
    <title>WIKI об научруках ФизТеха</title>

<#include "header-links.ftl">
</head>

<body>

<#include "nav.ftl">


<div class="container">
    <#if wikimipt??>
        <h1><a href="${wikimipt}">${name}</a></h1>
    <#else>
        <h1>${name}</h1>
    </#if>

    <#if photo??>
        <img src="${photo}" alt="${name}" width="266" height="332">
    </#if>

    <p>Базовые кафедры:
        <ul>
            <#list laboratories as lab>
                <#if lab.wikimipt??>
                    <li><a href="${lab.wikimipt}">${lab.name}</a></li>
                <#else>
                    <li>${lab.name}</li>
                </#if>
            </#list>
        </ul>
    </p>
</div>

<#--<div class="container">
    <div>
        <h4>Оставить комментарий</h4>
        <form action="/supervisors/${webname}/comment" method="get">
            <textarea name="comment" cols="80" rows="10"></textarea>
            <input type="submit" value="Отправить">
        </form>
    </div>
</div>-->

<div class="container">
    <a href="/supervisors/${webname}/comment-form"><h2>Оставить комментарий</h2></a>
</div>


<div class="container">
    <ul>
        <#list comments as comment>
            <li>
                <div class="container">
                    ${comment}
                </div>
            </li>
        </#list>
    </ul>
</div>


</body>
</html>
