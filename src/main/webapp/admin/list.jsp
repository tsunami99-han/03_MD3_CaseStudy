<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 8/7/2021
  Time: 2:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <h1>Phạm Tuấn Thảo</h1>
    <a href="/Admin?action=create">Create User</a>
    <c:forEach items="${phamtuanthao}" var="tuanthao">
        <h3><a href="/Admin?action=view&id=${tuanthao.id}">${tuanthao.username}</a> | ${tuanthao.password}| ${tuanthao.fullName}| ${tuanthao.role}|${tuanthao.dateOfBirth}|${tuanthao.address}|${tuanthao.desc}|${tuanthao.imgLink}</h3>
    </c:forEach>
</div>
</body>
</html>
