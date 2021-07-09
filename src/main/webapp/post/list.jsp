<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 7/8/2021
  Time: 9:46 AM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>

<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="css/style.css">
    <title>Tất cả bài viết</title>
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
        <li><a href="">Logout</a></li>
    </ul>
</nav>
<!--Kết Thúc Navigation -->

<!-- Dùng Để Chèn Hình Ảnh-->
<section class="section1">
</section>
<div class="text-center" aria-hidden="true" > <h1>Thanh xuân như một tách trà</h1>
    <p>
        Code đi code lại hết bà thanh xuân ...
    </p>
        <h2>Blog!!!! Mỗi ngày vài câu chuyện</h2>
    </div>
<nav class="navbar navbar-expand-lg navbar-light bg-light nav1">
    <a class="navbar-brand" href="users?action=list">Trang chủ</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="#">Bài viết nổi bật <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Bài viết mới</a>
            </li>
           <c:if test="${username!=null}"> <li class="nav-item">
               <a class="nav-link" href="users?action=create&id=${username.id}">Tạo blog mới</a>
           </li></c:if>
            <c:if test="${username!=null}"> <li class="nav-item">
                <a class="nav-link" href="users?action=viewuser&id=${username.id}">Trang cá nhân</a>
            </li></c:if>
        </ul>
        <form class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
    </div>
</nav>
<div class="row row1 container">
    <div class="col-md-3 div1" style=" height: 500px"> hihi</div>
    <div class="col-md-7">
        <c:forEach var="p" items="${list}">
         <div class="post" style="padding-bottom: 30px">
             <i class="time">${p.time}</i>
             <h4 >${p.title} </h4>
             ------------------ <br>
                 ${p.getContent()} <br>
                 ${p.likeQuantity} lượt thích | ${p.commentQuantity} lượt bình luận
             <a href="users?action=view&id=${p.id}">Xem thêm</a>
         </div>
             </c:forEach>

    </div>
    <div class="col-md-2"></div>
</div>

</body>
</html>
