<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 8/7/2021
  Time: 2:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form  method="post">
    <input type="hidden" name="action" value="edit">
    <input type="hidden" name="id" value="${phamtuanthao.id}">
    <input type="text" name="username" value="${phamtuanthao.username}"><br>
    <input type="text" name="password" value="${phamtuanthao.password}"><br>
    <input type="text" name="fullname" value="${phamtuanthao.fullName}"><br>
    <input type="text" name="role" value="${phamtuanthao.role}"><br>
    <input type="text" name="dateofbirth" value="${phamtuanthao.dateOfBirth}"><br>
    <input type="text" name="address" value="${phamtuanthao.address}"><br>
    <input type="text" name="desc" value="${phamtuanthao.desc}"><br>
    <input type="text" name="imgLink" value="${phamtuanthao.imgLink}"><br>
    <button>Edit</button>

</form>
<form method="post" action="/Admin">
    <input type="hidden" name="action" value="delete">
    <input  type="hidden" name="id" value="${phamtuanthao.id}">
    <button>Delete</button>
</form>
</body>
</html>
