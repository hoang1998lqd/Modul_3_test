<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Regalite
  Date: 8/4/2022
  Time: 9:33 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<table  border="1" style="text-align: center"  >
<a href="CreateProduct.jsp">Tạo mới sản phẩm</a>
<tr>
    <th style="width: 30px; text-align: center">Mã sản phẩm</th>
    <th style="width: 250px">Tên sản phẩm</th>
    <th style="width: 200px; text-align: center">Giá sản phẩm</th>
    <th style="width: 150px; text-align: center">Số lương</th>
    <th style="width: 100px; text-align: center">Màu sắc</th>
    <th style="width: 100px">Danh mục</th>
    <th colspan="2">Action</th>
</tr>
<c:forEach items="${products}" var="p">
    <tr>
        <td>${p.getId()}</td>
        <td>${p.getName()}</td>
        <td>${p.getPrice()}</td>
        <td>${p.getQuantity()}</td>
        <td>${p.getColor()}</td>
        <td>${p.getCategory().getName()}</td>
        <td><button><a href="/?action=edit&id=${p.getId()}">Edit</a></button></td>
        <td><button><a href="/?action=delete&id=${p.getId()}">Delete</a></button></td>
    </tr>
</c:forEach>
</table>
</body>
</html>
