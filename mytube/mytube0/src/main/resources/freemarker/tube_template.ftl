<!DOCTYPE html>
<html>
<head>
    <title>My Tube</title>
</head>
<body>

<#if username??>
Welcome ${username} <a href="/logout">Logout</a> | <a href="/newvideo">New Video</a> | <a href="/usersettings">User Settings</a>

<p>
</#if>

<h1>My Videos</h1>

<#list myvideos as videoInfo>
<h2><a href="/video/${videoInfo["permalink"]}">${videoInfo["title"]}</a></h2>
Uploaded ${videoInfo["date"]?datetime} <i>By ${videoInfo["author"]}</i><br>
Comments:
    <#if videoInfo["comments"]??>
        <#assign numComments = videoInfo["comments"]?size>
    <#else>
        <#assign numComments = 0>
    </#if>

<a href="/video/${videoInfo["permalink"]}">${numComments}</a>

<p>
    <em>Filed Under</em>:
    <#if videoInfo["tags"]??>
        <#list videoInfo["tags"] as tag>
        ${tag}
        </#list>
    </#if>

<p>
</#list>
</body>
</html>

