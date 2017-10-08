<!DOCTYPE html>

<html>
<head>
    <title>Account recovery</title>
    <style type="text/css">
        .label {text-align: right}
        .error {color: red}
    </style>

</head>

<body>
Need to Create an account? <a href="/signup">Signup</a><p>
<h2>Account recovery</h2>
<form method="post">
    <table>
        <tr>
            <td class="label">
                Username
            </td>
            <td>
                <input type="text" name="username" value="${username}">
            </td>
            <td class="error">
            ${username_error!""}
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
