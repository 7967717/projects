<!DOCTYPE html>

<html>
<head>
    <title>Recover</title>
    <style type="text/css">
        .label {text-align: right}
        .error {color: red}
    </style>

</head>

<body>
<h2>Recover</h2>
<form method="post">
    <table>
        <tr>
            <td class="label">
                Please reenter your username
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
                New Password
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
                Verify New Password
            </td>
            <td>
                <input type="password" name="verify" value="">
            </td>
            <td class="error">
            ${verify_error!""}
            </td>
        </tr>
    </table>

    <input type="submit">
</form>
</body>

</html>
