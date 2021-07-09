<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 7/9/2021
  Time: 12:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
    <title>Trang cá nhân</title>
    <link rel="stylesheet" href="css/style.css">
    <style>
body {
    background-image: url("https://i.pinimg.com/originals/65/55/7d/65557d48dbfc790243213a96195741a3.jpg");
    color: white;
}
    </style>
</head>
<body>
<nav>
    <ul> <li><a href="users?action=list"><i class="fa fa-truck" aria-hidden="true"></i>Home</a></li>
        <li><a href=""><i class="fa fa-home" aria-hidden="true"></i> Thanh xuân như 1 tách trà</a></li>
        <li><a href=""><i class="fa fa-info" aria-hidden="true"></i> Code đi code lại hết bà thành xuân ;(</a></li>

        <%--        <li><a href=""><i class="fa fa-telegram" aria-hidden="true"></i> Thông Tin</a></li>--%>
        <%--        <li><a href=""><i class="fa fa-location-arrow" aria-hidden="true"></i> Địa Chỉ</a></li>--%>
        <li><c:if test="${username !=null}"><a href="#">${username.fullName}</a></c:if></li>
        <li><c:if test="${username ==null}"><a href="users?action=login">Login</a></c:if></li>
        <li><a href="users?action=login">Logout</a></li>
    </ul>
</nav>
<!--Kết Thúc Navigation -->

<!-- Dùng Để Chèn Hình Ảnh-->
<section class="section1">
</section>
<div class="row">
<div class="text-center col-md-4" style="height: 300px; border-radius: 30px"><img width="290px" height="290px" src="${user.imgLink}" alt=""></div>
<div style="height: 300px; margin-top: 50px" class="col-md-4">
    ${user.fullName} <br>
    ${user.dateOfBirth} <br>
    ${user.address}
</div>
    <div class="col-md-4" style="margin-top: 50px">
        <b>Description :</b> <br>
        <p>${user.desc}</p>
    </div>
</div>
<div class="row row1 container">
    <div class="col-md-3 div1" style=" height: 500px"></div>
    <div class="col-md-7">
        <c:forEach var="p" items="${list}">
                <div class="post" style="padding-bottom: 30px">
                    <i class="time">${p.time}</i>
                    <h4 >${p.title} </h4>
                    ------------------ <br>
                        ${p.getContent()} <br>
                        ${p.likeQuantity} <img style="width: 20px;height: 20px"  src="/image/dislike.png" alt=""> | ${p.commentQuantity} lượt bình luận
                    <<a href="users?action=view&id=${p.id}">Xem thêm</a>
                </div>
        </c:forEach>

    </div>
    <div class="col-md-2"></div>
</div>
</body>
</html>
