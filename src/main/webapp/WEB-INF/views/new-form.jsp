<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<!-- 상대경로 사용, [현재 URL이 속한 계층 경로 + /save] -->
<!-- 만약 /save로 했다고 하면 URL에 localhost:8080/save로 들어가게 된다. 지금은 상대경로 사용-->
<!-- /를 안붙이고 save를 하게 된다면, ...:8080/servlet-mvc/members/save로 들어가게 된다. 보통은 절대 경로로 하는것이 좋다.-->
<form action="save" method="post">
    username: <input type="text" name="username" />
    age: <input type="text" name="age" />
    <button type="submit">전송</button>
</form>
</body>
</html>