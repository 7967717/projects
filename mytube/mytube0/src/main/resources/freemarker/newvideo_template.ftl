<!doctype HTML>
<html>
<head>
    <title>Upload a new video</title>
</head>
<body>
<#if username??>
Welcome ${username} <a href="/logout">Logout</a> | <a href="/">Tube Home</a>
<p>
</#if>
    <form action="/newvideo" method="POST" enctype='multipart/form-data'>
    ${errors!""}
        <h2>Title</h2>
        <input type="text" name="subject" size="120" value="${subject!""}"><br>

        <h2>Video Entry</h2>
        <input type="file" name="video" accept="video/*"><br>

        <h2>Tags</h2>
        Comma separated, please<br>
        <input type="text" name="tags" size="120" value="${tags!""}"><br>

<p>
    <input type="submit" value="Submit">

</body>
</html>

