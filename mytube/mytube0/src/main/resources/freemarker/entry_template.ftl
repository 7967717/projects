<!doctype HTML>
<html
<head>
    <title>
        My Video
    </title>
</head>
<body>
<#if username??>
Welcome ${username} <a href="/logout">Logout</a> | <a href="/newvideo">New Video</a>

<p>
</#if>

    <a href="/">Video Home</a><br><br>

<h2>${videoInfo["title"]}</h2>
Uploaded ${videoInfo["date"]?datetime}<i> By ${videoInfo["author"]}</i><br>
<hr>
<video width="320" height="240" controls>
    <source src=${path} type="video/mp4">
    Your browser does not support the video tag.
</video>

<p>
    <em>Filed Under</em>:
<#if videoInfo["tags"]??>
    <#list videoInfo["tags"] as tag>
    ${tag}
    </#list>
</#if>
<p>
    Comments:
<ul>
<#if videoInfo["comments"]??>
    <#assign numComments = videoInfo["comments"]?size>
<#else>
    <#assign numComments = 0>
</#if>
<#if (numComments > 0)>
    <#list 0 .. (numComments -1) as i>

        Author: ${videoInfo["comments"][i]["author"]}<br>
        <br>
    ${videoInfo["comments"][i]["body"]}<br>
        <hr>
    </#list>
</#if>
    <h3>Add a comment</h3>

    <form action="/newcomment" method="POST">
        <input type="hidden" name="permalink", value="${videoInfo["permalink"]}">
    ${errors!""}<br>
        <b>Name</b> (required)<br>
        <input type="text" name="commentName" size="60" value="${comments["name"]}"><br>
        <b>Email</b> (optional)<br>
        <input type="text" name="commentEmail" size="60" value="${comments["email"]}"><br>
        <b>Comment</b><br>
        <textarea name="commentBody" cols="60" rows="10">${comments["body"]}</textarea><br>
        <input type="submit" value="Submit">
    </form>
</ul>
</body>
</html>


