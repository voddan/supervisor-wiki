<!DOCTYPE html>
<html>
<head>
    <title>WIKI об научруках ФизТеха</title>

<#include "header-links.ftl">
</head>

<body>

<#include "nav.ftl">

<div class="container">
    <h1>Научные руководители</h1>
    <ul>
        <#list supervisorsList as sup>
            <li><a href="/supervisors/${sup.webname}">${sup.name}</a></li>
        </#list>
    </ul>
</div>




</body>
</html>
