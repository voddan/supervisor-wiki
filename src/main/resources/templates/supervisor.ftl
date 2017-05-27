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
    <h1><a href="${sup.wikimipt}">${sup.name}</a></h1>
<#else>
    <h1>${sup.name}</h1>
</#if>

<#if sup.photo??>
    <img src="${sup.photo}" alt="${sup.name}" width="266" height="332">
</#if>

    <p>Базовые кафедры:
    <ul>
    <#list sup.laboratories as lab>
        <#if lab.wikimipt??>
            <li><a href="${lab.wikimipt}">${lab.name}</a></li>
        <#else>
            <li>${lab.name}</li>
        </#if>
    </#list>
    </ul>
    </p>
</div>

<div class="container">
    <a href="/supervisors/${sup.webname}/comment-form"><h2>Оставить комментарий</h2></a>
</div>


<div class="container">
    <ul>
    <#list comments as com>
        <li>
            <div class="container">
                <hr>
                <#include "comment.ftl">
            </div>
        </li>
    </#list>
    </ul>
</div>


</body>
</html>