<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1250">
    <title>TPO Assignment 3</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/style.css" />
</head>
<body>
<center><h2>Add 2 numbers</h2></center>

<form action="add-servlet">
    <table>
        <tr>
            <td>number#1 :</td>
            <td><input type="text" name="number1"/></td>
        </tr>
        <tr>
            <td>number#2 :</td>
            <td><input type="text" name="number2"/></td>
        </tr>

    </table>
    <div>
        <input type="submit" value="POST" formmethod="post" name="find"/>
        <input type="submit" value="GET" formmethod="get" name="find"/>
        <input type="reset" value="Reset" name="reset" />
    </div>
    <h2>${result}</h2>
</form>
</body>
</html>
