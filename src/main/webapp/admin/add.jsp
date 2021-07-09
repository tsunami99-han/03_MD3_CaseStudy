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
    <title>Đăng ký</title>
</head>
<body>
<form method="post" onkeyup="checkPass()">
    <input type="hidden" name="action" value="create">
    <table>
        <tr>
            <td>Tài khoản</td>
            <td><input  type="text" name="username"></td>
        </tr>
        <tr>
            <td>Mật khẩu</td>
            <td> <input  type="password" name="password" id="pw"></td>
        </tr>
        <tr>
            <td>Nhập lại mật khẩu:</td>
            <td> <input  type="password" name="password" id="pw1"></td>
        </tr>
        <tr>
            <td>Họ và tên</td>
            <td><input type="text" name="fullname"></td>
        </tr>
        <tr>
            <td>Role</td>
            <td><select name="role" id="">
                <option value="user">User</option>
            </select></td>
        </tr>
        <tr>
            <td>Ngày sinh</td>
            <td><input type="text" name="dateofbirth" placeholder="yyyy-mm-dd"></td>
        </tr>
        <tr>
            <td>Địa chỉ</td>
            <td><input type="text" name="address"></td>
        </tr>
        <tr>
            <td>Desc</td>
            <td><input type="text" name="desc"></td>
        </tr>
        <tr>
            <td>Image</td>
            <td><input name="image" type="file" class="form-control-file" id="exampleFormControlFile1"></td>
        </tr>
        <tr><td><button id="btn" disabled>Create</button></td>
        <td><button><a href="users?action=login">Trở về trang chủ</a></button></td></tr>
    </table>
    <script>
        function checkPass(){
            let pw=document.getElementById("pw").value;
            let pw1=document.getElementById("pw1").value;
            if (pw==pw1){
                document.getElementById("btn").disabled = false
            }else {
                document.getElementById("btn").disabled = true
            }
        }
    </script>
</form>
</body>
</html>
