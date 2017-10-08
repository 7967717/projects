<!DOCTYPE html>

<html>
<head>
    <title>User Settings</title>
    <style type="text/css">
        .label {text-align: right}
        .error {color: red}
    </style>

</head>

<body>
<#if username??>
Welcome ${username} <a href="/logout">Logout</a> | <a href="/">Tube Home</a>
<p>
</#if>

<h2>User Settings</h2>
<form method="post">
    <table>
        <tr>
            <td class="label">
                Username
            </td>
            <td >
                <input type="" name="username" value="${username}" readonly>
            </td>
        </tr>

        <tr>
            <td class="label">
                New Username
            </td>
            <td>
                <input type="text" name="newusername" value="${newusername}">
            </td>
            <td class="error">
            ${username_error!""}

            </td>
        </tr>

        <tr>
            <td class="label">
                Password
            </td>
            <td>
                <input type="password" name="password" value="">
            </td>
            <td class="error">
            ${password_error!""}

            </td>
        </tr>

        <tr>
            <td class="label">
                New Password
            </td>
            <td>
                <input type="password" name="newpassword" value="">
            </td>
            <td class="error">
            ${newpassword_error!""}

            </td>
        </tr>

        <tr>
            <td class="label">
                Verify New Password
            </td>
            <td>
                <input type="password" name="verify" value="">
            </td>
            <td class="error">
            ${verify_error!""}

            </td>
        </tr>

        <tr>
            <td class="label">
                Email
            </td>
            <td>
                <input type="text" name="email" value="${email}" readonly>
        </tr>

        <tr>
            <td class="label">
                New Email
            </td>
            <td>
                <input type="text" name="newemail" value="${newemail}">
            </td>
            <td class="error">
            ${email_error!""}
            </td>
        </tr>

        <tr>
            <td class="label">
                Question 1
            </td>
            <td>
                <input type="text" name="question1" value="${question1}">
            </td>
        </tr>

        <tr>
            <td class="label">
                Answer 1
            </td>
            <td>
                <input type="text" name="answer1" value="${answer1}">
            </td>
            <td class="error">
            ${answer1_error!""}
            </td>
        </tr>

        <tr>
            <td class="label">
                Question 2
            </td>
            <td>
                <input type="text" name="question2" value="${question2}">
            </td>
            <td class="error">
            ${question2_error!""}
            </td>
        </tr>

        <tr>
            <td class="label">
                Answer 2
            </td>
            <td>
                <input type="text" name="answer2" value="${answer2}">
            </td>
            <td class="error">
            ${answer2_error!""}
            </td>
        </tr>
    </table>

    <input type="submit">
</form>
</body>

</html>
